package br.com.les.backend.strategy.costcentre;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteCostCentreStrategy implements IApplicationStrategy<CostCentre> {

	@Override
	public Result<CostCentre> execute(CostCentre entity, String callerMethod) {
		Result<CostCentre> result = new Result<>();
		
		switch ( callerMethod ) {
		case "Delete":
			// result.setSuccess( Util.DELETE_COST_SUCCESSFUL );
			break;
		}
		return result;
	}

}
