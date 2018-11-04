package br.com.les.backend.validator.request;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RequestRepository;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Util;

public class RequestToSaveAppointment implements IStrategy<Request> {

	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null) {
			aEntity.setEmployee( (Employee) authenticatedUser());
			if (aEntity.getType() == RequestType.CHANGE_APPOINTMENT) {
				if (Strings.isNullOrEmpty(String.valueOf(aEntity.getStartDate())))
					aCase.getResult().setError(Util.ERROR_ENTRY_DATE);

				if (aEntity.getStartDate().compareTo(LocalDate.now()) <= 0)
					aCase.getResult().setError(Util.INVALID_ENTRY_DATE);

				if (Strings.isNullOrEmpty(aEntity.getDescription()))
					aCase.getResult().setError(Util.INVALID_DESCRIPTION);

				aEntity.setStatus(RequestStatus.SENT.getValue());
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}
}
