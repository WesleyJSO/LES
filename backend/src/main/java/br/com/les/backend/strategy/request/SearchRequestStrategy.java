package br.com.les.backend.strategy.request;

import br.com.les.backend.entity.Request;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;

public class SearchRequestStrategy implements IApplicationStrategy<Request> {

	@Override
	public Result<Request> execute(Request request) {

		Result<Request> result = new Result<>();

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
