package br.com.les.backend.strategy.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.AppointmentRequest;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.AppointmentRequestRepository;
import br.com.les.backend.utils.Util;

@Configuration
public class UpdateChangeSpecifcAppointment implements IStrategy<AppointmentRequest> {
	
	@Autowired AppointmentRequestRepository requestRepository;
	
	@Override
	public void process(AppointmentRequest aEntity, INavigationCase<AppointmentRequest> aCase) {
		if (aEntity != null && aEntity.getId() != 0 && !Strings.isNullOrEmpty(String.valueOf(aEntity.getId()))) {
			AppointmentRequest request = requestRepository.findActiveById(aEntity.getId()).get();
			
			if ( null == request ) {
				aCase.getResult().setError( Util.REQUEST_NOT_FOUNDED );
			}
			
			if ( Strings.isNullOrEmpty( aEntity.getDescription() ) ) {
				aCase.getResult().setError(Util.INVALID_DESCRIPTION);
			}
			
			if ( Strings.isNullOrEmpty( aEntity.getFieldToChange() ) ) {
				aCase.getResult().setError(Util.ERROR_FIELD_TO_CHANGE);
			}
			
			if (RequestStatus.APPROVED.getValue() == aEntity.getStatus() ) {
				aEntity.setStatus(RequestStatus.APPROVED.getValue());
			} else if ( aEntity.getStatus() == RequestStatus.DENIED.getValue() ) {
				aEntity.setStatus(RequestStatus.DENIED.getValue());
			}
			
			if ( aCase.getResult().isSuccess() && aEntity.getStatus() == RequestStatus.SENT.getValue() ) {
				aCase.getResult().setSuccess( Util.SAVE_SUCCESSFUL_REQUEST );
			} else if ( aCase.getResult().isSuccess() && aEntity.getStatus() == RequestStatus.APPROVED.getValue() ) {
				aCase.getResult().setSuccess( Util.APPROVE_SUCCESSFUL_REQUEST );
			} else if ( aCase.getResult().isSuccess() && aEntity.getStatus() == RequestStatus.DENIED.getValue() ) {
				aCase.getResult().setSuccess( Util.DENY_SUCCESSFUL_REQUEST );
			} else if ( !request.getFieldToChange().equals( aEntity.getFieldToChange() )
					  || !request.getReplacement().equals( aEntity.getReplacement() ) ) {
				aCase.getResult().setSuccess( Util.UPDATE_SUCCESSFUL_REQUEST );
			}
			return;
		}		
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento inexistente!");
		return;
	}
	
}
