package br.com.les.backend.strategy.appointment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.annotation.TimeIgnore;
import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.AppointmentRequest;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.AppointmentRequestRepository;
import br.com.les.backend.repository.ParameterRepository;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Configuration
public class ChangeSpecificAppointment implements IStrategy<Appointment> {

	@Autowired ParameterRepository parameterRepository;
	@Autowired AppointmentRepository appointmentRepository;
	@Autowired AppointmentRequestRepository appointmentRequestRepository;
	
	@Override
	public void process(Appointment aEntity, INavigationCase<Appointment> aCase) {

		if (aEntity != null) {
			Result<Appointment> result = new Result<>();
			
			// find the active parameter in the system, it need's to have only one in this state
			List<Parameter> parameterList = parameterRepository.findAllActive();
			if(!parameterList.isEmpty()) {
			
				Parameter parameter = parameterList.get(0);
				
				// find the appointment that need to be changed,
				// the list needs to show only one
				Optional<Appointment> dbAppointment = appointmentRepository.findById(aEntity.getId());
				
				// find which field need to be updated 
				Method methodToUpdate = findFieldAndValueToChange(dbAppointment.get(), aEntity);
				Method methodToCompare;
				int timeDiference = 0;
				LocalTime dateToUpdate = null;
				LocalTime dateToCompare = null;
				LocalDateTime now = LocalDateTime.now();
				try {
					methodToCompare = dbAppointment.get().getClass().getDeclaredMethod(methodToUpdate.getName());
					// verify if the value can be updated with the parameter object 
					dateToUpdate = (LocalTime) methodToCompare.invoke(aEntity);
					dateToCompare = (LocalTime) methodToCompare.invoke(dbAppointment.get());
					timeDiference = (int) ChronoUnit.MINUTES.between( dateToUpdate, now.toLocalTime());
				} catch (Exception e) {
					aCase.suspendExecution();
					aCase.getResult().setError("Erro durante o processamento de datas!");
					return;
				}
				
				// validate with the list of requests if it's a change to this column was already requested
				List<AppointmentRequest> appointmentRequestList = appointmentRequestRepository.findByAppointmentId(aEntity.getId());
				for (AppointmentRequest appointmentRequest : appointmentRequestList) {
					if(!appointmentRequest.getIsAproved() && appointmentRequest.getFieldToChange().equals(methodToUpdate.getName())) {
						aCase.suspendExecution();
						aCase.getResult().setError("Já existe uma solicitação em andamento para o apontamento informado!");
						return;
					}
				}
				
				LocalTime retroactiveLimit = parameter.getRetroactiveAppointmentLimitTime();
				int retroactiveLimitInMinutes = (retroactiveLimit.getHour() * 60) + retroactiveLimit.getMinute();
				
				if(timeDiference > retroactiveLimitInMinutes || timeDiference < retroactiveLimitInMinutes * - 1
						|| !aEntity.getDate().toLocalDate().equals(now.toLocalDate())) {

					//save a new AppointmentRequest and send a message back to the user
					if(result.isSuccess()) {
						AppointmentRequest appointmentRequest = new AppointmentRequest();
						appointmentRequest.setAppointment(dbAppointment.get());
						appointmentRequest.setFieldToChange(methodToUpdate.getName());
						appointmentRequest.setReplacement(dateToUpdate);
						appointmentRequest.setPreviousValue(dateToCompare);
						appointmentRequest.setDescription(MessageFormat.format(Util.CHANGE_SPECIFIC_APPOINTMENT_MESSAGE, retroactiveLimit.toString()));
						appointmentRequest.setIsAproved(false);
						appointmentRequest.setType(RequestType.CHANGE_APPOINTMENT);
						appointmentRequest.setEmployee( (Employee) SecurityService.getAuthenticatedUser() );
						appointmentRequest.setStatus(RequestStatus.SENT.getValue());
						// Set the same value to start and end date because this request
						// refers to the appointment of the same day
						appointmentRequest.setStartDate(dbAppointment.get().getDate().toLocalDate());
						appointmentRequest.setEndDate(dbAppointment.get().getDate().toLocalDate());
						appointmentRequestRepository.save(appointmentRequest);
						// Do not send a "Successful update appointment" message, 
						// cause we'are going to  ask user confirmation about this request
						aCase.suspendExecution();
						// aCase.getResult().setSuccess(Util.UPDATE_APPOINTMENT_REQUESTED);
						return;
					}
				}
			} else {
				aCase.suspendExecution();
				aCase.getResult().setError(Util.ERROR_NOT_VALID_PARAMETER);
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento inexistente!");
		return;
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
							&& !method.invoke(appointment).equals(dbMethod.invoke(dbAppointment))
							&& null == method.getAnnotation(TimeIgnore.class)) {
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