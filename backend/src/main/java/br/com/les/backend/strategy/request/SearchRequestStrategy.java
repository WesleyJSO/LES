package br.com.les.backend.strategy.request;

import java.util.Calendar;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Request;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.RequestStatus;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

public class SearchRequestStrategy implements IApplicationStrategy<Request> {

	@Override
	public Result<Request> execute(Request entity, String callerMethod) {

		Result<Request> result = new Result<>();

		Request request = (Request) entity;

		// Take a look more carefully in how to get the request type
		RequestType type = Enum.valueOf(RequestType.class, String.valueOf(request.getType()));

		switch (type) {
		case CHANGE_APPOINTMENT:
			break;
		case WORK_OVERTIME:
			break;
		case COMP_TIME:
			break;
		case REALOCATION_DAYS:
			break;
		default:
			break;
		}
		return result;
	}

}
