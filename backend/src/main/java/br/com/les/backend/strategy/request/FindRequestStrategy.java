package br.com.les.backend.strategy.request;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;

@Component
public class FindRequestStrategy extends AbstractStrategy<Request> {

	@Override
	public Result<Request> execute(Request request) {

		Result<Request> result = new Result<>();

		request.setEmployee((Employee) authenticatedUser());

		switch (request.getType()) {
		case RequestType.CHANGE_APPOINTMENT:
			break;
		case RequestType.WORK_OVERTIME:
			break;
		case RequestType.COMP_TIME:
			break;
		case RequestType.REALOCATION_DAYS:
			break;
		default:
			break;
		}
		return result;
	}

}
