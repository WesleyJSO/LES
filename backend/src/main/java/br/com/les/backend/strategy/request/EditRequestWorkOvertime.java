package br.com.les.backend.strategy.request;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RequestRepository;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Util;

@Configuration
public class EditRequestWorkOvertime implements IStrategy<Request> {

	@Autowired private RequestRepository requestRepository;

	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null && aEntity.getId() != 0 && !Strings.isNullOrEmpty(String.valueOf(aEntity.getId()))) {
			Request r = requestRepository.findActiveById(aEntity.getId()).get();
			
				if ( aEntity.getType() == RequestType.WORK_OVERTIME && aEntity.getStatus() == r.getStatus() ) {
					if (null == aEntity.getStartDate()) {
						aCase.getResult().setError(Util.ERROR_ENTRY_DATE);
					} else if (r.getStartDate().compareTo(aEntity.getStartDate()) != 0
							&& aEntity.getStartDate().compareTo(LocalDate.now()) <= 0) {
						aCase.getResult().setError(Util.INVALID_ENTRY_DATE);
					}
					
					if (null != aEntity.getEndDate() && aEntity.getEndDate().compareTo(aEntity.getStartDate()) <= 0) 
						aCase.getResult().setError(Util.INVALID_END_DATE);
					
					if (null == aEntity.getDescription())
						aCase.getResult().setError(Util.ERROR_DESCRIPTION);
					
					else if (r.getDescription() != aEntity.getDescription()
							&& (aEntity.getDescription().trim().equals("") || aEntity.getDescription().length() < 10))
						aCase.getResult().setError(Util.INVALID_DESCRIPTION);

					if (aCase.getResult().isSuccess()) {
						aCase.getResult().setSuccess(Util.UPDATE_SUCCESSFUL_REQUEST);
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
