package br.com.les.backend.strategy.appointment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
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
			List< Appointment > appointmentList = appointmentRepository.findByDate(appointment.getDate().toLocalDate().atStartOfDay());
			
			// using the history to return the previous values subtracted to the monthly balance
			for (Appointment ap : appointmentList){
				for (HistMonthlyBalanceChange history : ap.getHistChangeMonthlyBalanceList()) {
					if ( !monthlyBalanceList.contains(history.getMonthlyBalance()) ) {
						monthlyBalanceList.add(history.getMonthlyBalance());
					}
					for ( MonthlyBalance mb : monthlyBalanceList ) {
						if ( mb.getMonth().equals(history.getMonthlyBalance().getMonth()) ) {
							mb.setBalance( mb.getBalance() + (history.getAmount() * (-1d)) );
							history.setAmount(0d);
						}
					}
				}
			}
			
			// if empty, the history wasn't used, so the system get the list of balances with pendencies
			// if not, the list get in the history already have the balances to compensate
			if ( monthlyBalanceList.isEmpty() ) {
				monthlyBalanceList = monthlyBalanceRepository.findWithPendency(YearMonth.now().toString());
			}
			// monthlyBalanceList = monthlyBalanceRepository.findAll();
			
			Double amount = null;
			Stream<HistMonthlyBalanceChange> stream = null;
			Optional<HistMonthlyBalanceChange> optional = null;			
			Method method = null;
			String appointmentMonth;
			
			for ( Appointment ap : appointmentList ) {
				stream = ap.getHistChangeMonthlyBalanceList().stream();
				method = getMethod(ap);
				// null means that the appointment have no active neither passive values to discount
				if ( method == null )
					continue;
				method.invoke(ap);
				amount = getDoubleTime((LocalTime) method.invoke(ap), method.getName());
				appointmentMonth = getMonth(ap.getDate());
				for (MonthlyBalance mb : monthlyBalanceList) {
					// if balance and the amount are positive and it is not the current, go to the next balance
					if ( mb.getBalance() > 0d && method.getName().equals("getDayOvertime")
							|| mb.getBalance() < 0d && method.getName().equals("getHoursLeft")) {
						if ( !mb.getMonth().equals(appointmentMonth) )
							continue;
					}
					if (Math.abs(mb.getBalance()) > 0d || mb.getMonth().equals(appointmentMonth) ) {
						//get the specific element on the list to change
						optional = stream.filter(history -> mb.equals(history.getMonthlyBalance()))
								.findFirst();
						if (Math.abs(mb.getBalance()) < Math.abs(amount) && !mb.getMonth().equals(appointmentMonth)) {
							// save the rest of the amount to discount on the next balance of the list
							// if the balance is negative, it will be subtracted from the amount
							amount -= mb.getBalance() * (-1d);
							createUpdateHistory(mb, mb.getBalance() * (-1d), optional, ap);
							mb.setBalance(0d); 
						} else {
							mb.setBalance(mb.getBalance() - amount);
							createUpdateHistory(mb, amount, optional, ap);
							break;
						}
					}
				}
			}
			monthlyBalanceRepository.saveAll(monthlyBalanceList);
			appointmentRepository.saveAll(appointmentList);
			histMonthlyBalanceChangeRepository.deleteDeprecated();
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String getMonth(LocalDateTime date) {

		YearMonth appointmentMonth = YearMonth.of(date.getYear(), date.getMonthValue());
		return appointmentMonth.toString();
	}

	public Method getMethod(Appointment appointment) {
		Class<?>[] methodParameters = new Class[0];
        String methodName = "";
        if ( appointment.getDayOvertime().isAfter(LocalTime.MIN) ) {
        	methodName = "getDayOvertime";
        } else if ( appointment.getHoursLeft().isAfter(LocalTime.MIN) ) {
        	methodName = "getHoursLeft";
        }
		Class<?> clazz = appointment.getClass();
		try {
			return clazz.getMethod(methodName, methodParameters);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createUpdateHistory(MonthlyBalance monthlyBalance, Double amount, 
			Optional<HistMonthlyBalanceChange> optional, Appointment appointment) {
		
		if ( optional.isPresent() ) {
			optional.get().setAmount( amount );
		} else {
			HistMonthlyBalanceChange newHistory = new HistMonthlyBalanceChange();
			newHistory.setAmount( amount );
			newHistory.setAppointment(appointment);
			newHistory.setMonthlyBalance(monthlyBalance);
			appointment.getHistChangeMonthlyBalanceList().add(newHistory);
		}
	}
	
	public Double getDoubleTime(LocalTime time, String name) {

		Double minuteValue = Double.valueOf( time.getMinute() / 60d);
		Double hourValue = time.getHour() + minuteValue;
		// day overtime means that the value will be subtracted from a negative value
		// setting the value as negative will guarantee that the obtained value will be nearer from zero 
		if (name == "getDayOvertime") {
			hourValue *= (-1d);
		}
		return hourValue;
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
