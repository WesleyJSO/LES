package br.com.les.backend.strategy.request;

import java.time.LocalDate;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Util;

@Configuration
public class RequestRealocateDay implements IStrategy<Request> {

	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null) {
			if (aEntity.getType() == RequestType.REALOCATE_DAY) {
				if( null == aEntity.getStartDate() ) {
					aCase.getResult().setError(Util.ERROR_ENTRY_DATE);
				} else if( aEntity.getStartDate().compareTo(LocalDate.now()) <= 0) { 
					aCase.getResult().setError(Util.INVALID_ENTRY_DATE);
				}
				if ( null == aEntity.getEndDate()) {
					aCase.getResult().setError(Util.ERROR_END_DATE);
				} else if( aEntity.getEndDate().compareTo(aEntity.getStartDate()) <= 0 ) {
					aCase.getResult().setError(Util.INVALID_END_DATE);
				}

				if(Strings.isNullOrEmpty(aEntity.getDescription()))
					aCase.getResult().setError(Util.INVALID_DESCRIPTION);
				if (aCase.getResult().isSuccess()) {
					aEntity.setStatus(RequestStatus.SENT.getValue());
					aEntity.setEmployee( (Employee) SecurityService.getAuthenticatedUser());
					aCase.getResult().setSuccess(Util.SAVE_SUCCESSFUL_REQUEST);
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}
}
