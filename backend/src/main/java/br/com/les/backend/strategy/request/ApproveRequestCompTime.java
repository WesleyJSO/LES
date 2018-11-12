package br.com.les.backend.strategy.request;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RequestRepository;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Util;

@Configuration
public class ApproveRequestCompTime implements IStrategy<Request> {
	
	@Autowired private RequestRepository requestRepository;
	
	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null && aEntity.getId() != 0 && !Strings.isNullOrEmpty(String.valueOf(aEntity.getId()))) {
			Request r = requestRepository.findActiveById(aEntity.getId()).get();
				if ( r.getType() == RequestType.COMP_TIME && aEntity.getStatus() != r.getStatus() ) {
					
					if (RequestStatus.APPROVED.getValue() == aEntity.getStatus() ) {
						aEntity.setStatus(RequestStatus.APPROVED.getValue());
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

}
