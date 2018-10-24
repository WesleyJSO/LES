package br.com.les.backend.strategy.request;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Request;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteRequestStrategy extends AbstractStrategy<Request> {
	
	@Override
	public Result<Request> execute(Request request) {

		Result<Request> result = new Result<>();
		
		return result;
	}
}
