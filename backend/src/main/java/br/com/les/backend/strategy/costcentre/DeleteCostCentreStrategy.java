package br.com.les.backend.strategy.costcentre;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class DeleteCostCentreStrategy extends AbstractCostCentreStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		result = new Result();
		
		switch ( callerMethod ) {
		case "delete":
			// result.setSuccess( Util.DELETE_COST_SUCCESSFUL );
			break;
		}
		return result;
	}

}
