package br.com.les.backend.strategy.Request;

import br.com.les.backend.entity.AbsenceRequest;
import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public class SearchRequestStrategy extends AbstractRequestStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		result = new Result();
		AbsenceRequest absenceRequest = ( AbsenceRequest ) entity;
		
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
