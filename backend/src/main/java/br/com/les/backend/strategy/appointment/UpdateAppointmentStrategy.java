package br.com.les.backend.strategy.appointment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.HistMonthlyBalanceChange;
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.AppointmentRequestRepository;
import br.com.les.backend.repository.HistMonthlyBalanceChangeRepository;
import br.com.les.backend.repository.MonthlyBalanceRepository;
import br.com.les.backend.repository.ParameterRepository;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
@Component
public class UpdateAppointmentStrategy implements IApplicationStrategy<Appointment> {

	@Autowired ParameterRepository parameterRepository;
	@Autowired AppointmentRepository appointmentRepository;
	@Autowired AppointmentRequestRepository appointmentRequestRepository;
	@Autowired GenericDAO<Appointment> appointmentDAO;
	@Autowired HistMonthlyBalanceChangeRepository histMonthlyBalanceChangeRepository;
	@Autowired MonthlyBalanceRepository monthlyBalanceRepository;
	
	
	@Override
	public Result<Appointment> execute(Appointment appointment) {

		Result<Appointment> result = new Result<>();
		
//		// find the active parameter in the system, it need's to have only one in this state
//		List<Parameter> parameterList = parameterRepository.findByActive();
//		if(!parameterList.isEmpty()) {
//		
//			Parameter parameter = parameterList.get(0);
//			
//			// find the appointment that need to be changed the list needs to show only one
//			Optional<Appointment> dbAppointment = appointmentRepository.findById(appointment.getId());
//			
//			// find which field need to be updated 
//			Method methodToUpdate = findFieldAndValueToChange(dbAppointment.get(), appointment);
//			Method methodToCompare;
//			int timeDiference = 0;
//			LocalTime dateToUpdate = null;
//			LocalTime dateToCompare = null;
//			try {
//				methodToCompare = dbAppointment.get().getClass().getDeclaredMethod(methodToUpdate.getName());
//				// verify if the value can be updated with the parameter object 
//				dateToUpdate = (LocalTime) methodToUpdate.invoke(dbAppointment.get());
//				dateToCompare = (LocalTime) methodToCompare.invoke(appointment);
//				timeDiference = (int) ChronoUnit.HOURS.between(dateToUpdate, dateToCompare);
//			} catch (Exception e) {
//				e.printStackTrace();
//				result.setError("Erro durante o processamento!");
//				return result;
//			}
//			
//			// validate with the list of requests if it's a change to this column was already requested
//			List<AppointmentRequest> appointmentRequestList = appointmentRequestRepository.findByIdAppointment(appointment.getId());
//			for (AppointmentRequest appointmentRequest : appointmentRequestList) {
//				if(!appointmentRequest.getIsAproved() && appointmentRequest.getFieldToChange().equals(methodToUpdate.getName())) {
//					result.setError("Já existe uma solicitação em andamento para o apontamento informado!");
//					return result;
//				}
//			}
//			
//			if(timeDiference > parameter.getRetroactiveAppointmentLimitTime()
//					|| timeDiference < parameter.getRetroactiveAppointmentLimitTime() * - 1) {
//				//save a new AppointmentRequest and send a message back to the user
//				if(result.isSuccess()) {
//					AppointmentRequest appointmentRequest = new AppointmentRequest();
//					appointmentRequest.setAppointment(appointment);
//					appointmentRequest.setFieldToChange(methodToUpdate.getName());
//					appointmentRequest.setReplacement(dateToUpdate);
//					appointmentRequest.setIsAproved(false);
//					appointmentRequestRepository.save(appointmentRequest);
//					result.setError(Util.UPDATE_APPOINTMENT_REQUESTED);
//				}
//			} else {
//				result.setSuccess("Apontamento atualizado com sucesso!");
//			}
//			// else update the requested appointment
//		} else {
//			result.setError(Util.ERROR_NOT_VALID_PARAMETER);
//		}
		SaveAppointmentStrategy strategy = new SaveAppointmentStrategy();
		result = strategy.execute(appointment);
		
		if ( compensateBank(appointment) ) {
			result.setSuccess("Sucesso");
		} else {
			result.setError("Erro");
		}
				
		return result;
	}
	
	public Boolean compensateBank(Appointment appointment) {
		
		try {
			List<MonthlyBalance> monthlyBalanceList = new ArrayList<>();
			
			// get all the appointments created after the edited appointment
			List< Appointment > list = appointmentRepository.findByDate(appointment.getDate().toLocalDate().atStartOfDay());
			
			// using the history of 
			for (Appointment ap : list){
				for (HistMonthlyBalanceChange hist : ap.getHistChangeMonthlyBalanceList()) {
					if ( !monthlyBalanceList.contains(hist.getMonthlyBalance()) ) {
						monthlyBalanceList.add(hist.getMonthlyBalance());
					}
					for ( MonthlyBalance mb : monthlyBalanceList ) {
						if ( mb.getMonth().equals(hist.getMonthlyBalance().getMonth()) ) {
							mb.setBalance( mb.getBalance() + (hist.getAmount() * (-1d)) );
							hist.setAmount(0d);
						}
					}
				}
			}
			
			if ( monthlyBalanceList.isEmpty() ) {
				monthlyBalanceList = monthlyBalanceRepository.findWithPendency();
			}
			
			Double amount = null;
			Stream<HistMonthlyBalanceChange> stream = null;
			Optional<HistMonthlyBalanceChange> optional = null;
			
			for ( Appointment ap : list ) {
				stream = ap.getHistChangeMonthlyBalanceList().stream();
				if ( ap.getDayOvertime().isAfter(LocalTime.MIN) ) {
					amount = getDoubleTime(ap.getDayOvertime());
					for (MonthlyBalance mb : monthlyBalanceList) {
						if (mb.getBalance() > 0 ) {
							optional = stream.filter(history -> mb.equals(history.getMonthlyBalance()))
									.findFirst();
							if (mb.getBalance() < amount) {
								amount -= mb.getBalance();
								createUpdateHistory(mb, amount, optional, ap);
								mb.setBalance(0d); 
							} else {
								mb.setBalance(mb.getBalance() - amount);
								createUpdateHistory(mb, amount, optional, ap);
								break;
							}
						}
					}
				} else if ( ap.getHoursLeft().isAfter(LocalTime.MIN) ) {
					amount = getDoubleTime(ap.getHoursLeft());
					for (MonthlyBalance mb : monthlyBalanceList) {
						if (mb.getBalance() < 0 ) {
							optional = stream.filter(history -> mb.equals(history.getMonthlyBalance()))
									.findFirst();
							if (mb.getBalance() * (-1) < amount) {
								amount += mb.getBalance();
								createUpdateHistory(mb, amount, optional, ap);
								mb.setBalance(0d);
							} else {
								mb.setBalance(mb.getBalance() + amount);
								createUpdateHistory(mb, amount, optional, ap);
								break;
							}
						}
					}
				}
			}
			monthlyBalanceRepository.saveAll(monthlyBalanceList);
			appointmentRepository.saveAll(list);
			histMonthlyBalanceChangeRepository.deleteDeprecated();
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void createUpdateHistory(MonthlyBalance mb, Double amount, Optional<HistMonthlyBalanceChange> optional, Appointment ap) {
		
		if ( optional.isPresent() ) {
			optional.get().setAmount( - amount );
		} else {
			HistMonthlyBalanceChange newHist = new HistMonthlyBalanceChange();
			newHist.setAmount( - amount );
			newHist.setAppointment(ap);
			newHist.setMonthlyBalance(mb);
			ap.getHistChangeMonthlyBalanceList().add(newHist);
		}
	}
	
	public Double getDoubleTime(LocalTime time) {

		Double min = Double.valueOf( time.getMinute() / 60);
		Double hor = time.getHour() + min;
		return hor;
	}

	private Method findFieldAndValueToChange(Appointment dbAppointment, Appointment appointment) {
		Method[] dbMethods = dbAppointment.getClass().getDeclaredMethods();
		Method[] methods = appointment.getClass().getDeclaredMethods();
		Method valueToChange = null;
		for (Method method : methods) {
			for (Method dbMethod : dbMethods) {
				try {
					if(method.getReturnType() == LocalTime.class && dbMethod.getReturnType() == LocalTime.class 
							&& dbMethod.getName().equals(method.getName()) && method.invoke(appointment) != null
							&& !dbMethod.invoke(dbAppointment).equals(method.invoke(appointment))) {
							valueToChange = method;
							break;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
			}
			if(valueToChange != null) 
				break;
		}
		return valueToChange;
	}
}
