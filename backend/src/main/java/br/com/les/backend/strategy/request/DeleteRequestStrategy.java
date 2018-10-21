package br.com.les.backend.strategy.request;

import br.com.les.backend.entity.Request;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

public class DeleteRequestStrategy implements IApplicationStrategy<Request> {
	
	@Override
	public Result<Request> execute(Request request) {

		Result<Request> result = new Result<>();
		
		return result;
	}
}
