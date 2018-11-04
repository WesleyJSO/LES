package br.com.les.backend.validator.request;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Request;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RequestRepository;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Util;

public class RequestToWorkOvertime implements IStrategy<Request> {

	@Autowired private RequestRepository requestRepository;

	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null && aEntity.getId() != 0 && !Strings.isNullOrEmpty(String.valueOf(aEntity.getId()))) {
			requestRepository.findActiveById(aEntity.getId()).ifPresent(r -> {
				if (aEntity.getType() == RequestType.WORK_OVERTIME) {
					if (null == aEntity.getStartDate())
						aCase.getResult().setError(Util.ERROR_ENTRY_DATE);
					
					else if (r.getStartDate().compareTo(aEntity.getStartDate()) != 0
							&& aEntity.getStartDate().compareTo(LocalDate.now()) <= 0)
						aCase.getResult().setError(Util.INVALID_ENTRY_DATE);
					
					if (null != aEntity.getEndDate() && aEntity.getStartDate().compareTo(aEntity.getEndDate()) <= 0) 
						aCase.getResult().setError(Util.INVALID_END_DATE);
					
					if (null == aEntity.getDescription())
						aCase.getResult().setError(Util.ERROR_DESCRIPTION);
					
					else if (r.getDescription() != aEntity.getDescription()
							&& (aEntity.getDescription().trim().equals("") || aEntity.getDescription().length() < 10))
						aCase.getResult().setError(Util.INVALID_DESCRIPTION);
				}
				return;
			});
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}

}
