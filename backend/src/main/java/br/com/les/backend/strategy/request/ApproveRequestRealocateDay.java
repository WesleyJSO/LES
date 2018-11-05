package br.com.les.backend.strategy.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Request;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RequestRepository;
import br.com.les.backend.utils.RequestType;

@Configuration
public class ApproveRequestRealocateDay implements IStrategy<Request> {
	
	@Autowired RequestRepository requestRepository;
	
	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null && aEntity.getId() != 0 && !Strings.isNullOrEmpty(String.valueOf(aEntity.getId()))) {
			requestRepository.findActiveById(aEntity.getId()).ifPresent(r -> {
				if (aEntity.getType() == RequestType.REALOCATE_DAY && aEntity.getType() != r.getType()) {
					if (RequestStatus.APPROVED.getValue() == aEntity.getStatus() ) {
						aEntity.setStatus(RequestStatus.APPROVED.getValue());
					} else if ( aEntity.getStatus() == RequestStatus.DENIED.getValue() ) {
						aEntity.setStatus(RequestStatus.DENIED.getValue());
					} else if (  aEntity.getStatus() == RequestStatus.SENT.getValue() ) {
						aEntity.setStatus(RequestStatus.SENT.getValue());
					}
				}
				return;
			});
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}

}
