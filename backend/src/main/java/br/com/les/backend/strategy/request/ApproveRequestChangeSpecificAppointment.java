package br.com.les.backend.strategy.request;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.AppointmentRequest;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.AppointmentRequestRepository;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Util;

@Configuration
public class ApproveRequestChangeSpecificAppointment implements IStrategy<AppointmentRequest> {

	@Autowired AppointmentRequestRepository requestRepository;
	@Autowired AppointmentRepository appointmentRepository;
	
	@Override
	public void process(AppointmentRequest aEntity, INavigationCase<AppointmentRequest> aCase) {
		if (aEntity != null && aEntity.getId() != 0 && !Strings.isNullOrEmpty(String.valueOf(aEntity.getId()))) {
			AppointmentRequest r = requestRepository.findActiveById(aEntity.getId()).get();
				if ( r.getType() == RequestType.CHANGE_APPOINTMENT && aEntity.getStatus() != r.getStatus() ) {
					
					if (RequestStatus.APPROVED.getValue() == aEntity.getStatus() ) {
						aEntity.setStatus(RequestStatus.APPROVED.getValue());
						// Update Appointment
						String fieldToUpdate = aEntity.getFieldToChange();
						fieldToUpdate = fieldToUpdate.replace("get", "");
						fieldToUpdate = fieldToUpdate.replace( fieldToUpdate.substring(0, 1), fieldToUpdate.substring(0, 1).toLowerCase());
						this.updateAppointment(aEntity.getAppointment(), fieldToUpdate, aEntity.getReplacement());
					} else if ( aEntity.getStatus() == RequestStatus.DENIED.getValue() ) {
						aEntity.setStatus(RequestStatus.DENIED.getValue());
					} else if (  aEntity.getStatus() == RequestStatus.SENT.getValue() ) {
						aEntity.setStatus(RequestStatus.SENT.getValue());
					}
					
					if (aCase.getResult().isSuccess()) {
						aCase.getResult().setSuccess(aEntity.getStatus() == RequestStatus.APPROVED.getValue() ? 
								Util.APPROVE_SUCCESSFUL_REQUEST : 
								Util.DENY_SUCCESSFUL_REQUEST);
						Employee manager = (Employee) SecurityService.getAuthenticatedUser();
						aEntity.setManagerName(manager.getName() + " " + manager.getLastName());
						aEntity.setUpdatedDate(LocalDate.now());
					}
				}
				return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}
	
	public void updateAppointment (Appointment appointment, String fieldToUpdate, LocalTime value) {
		try {
			Field field = appointment.getClass().getDeclaredField(fieldToUpdate);
			field.setAccessible(true);
			field.set(appointment, value);
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
