package br.com.les.backend.strategy.request;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Request;
import br.com.les.backend.utils.Result;

public class SearchRequestStrategy extends AbstractRequestStrategy {

	@Override
	public Result run(DomainEntity entity, String callerMethod) {
		result = new Result();
		Request request = ( Request ) entity;
		
		switch ( callerMethod ) {
		case "FindAll":
			result.setSuccess("Nenhuma validação necessária");
			break;
		default:
			break;
		}
		return result;
	}

}
