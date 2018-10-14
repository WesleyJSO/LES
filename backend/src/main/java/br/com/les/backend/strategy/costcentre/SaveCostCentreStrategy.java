package br.com.les.backend.strategy.costcentre;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;


@Component
public class SaveCostCentreStrategy implements IApplicationStrategy<CostCentre> {

	@Override
	public Result<CostCentre> execute(CostCentre entity, String callerMethod) {
		Result<CostCentre> result = new Result<>();
		
		switch ( callerMethod ) {
		case "Save":
			CostCentre costCentre = entity;
			costCentre.setActive( true );
			if ( null == costCentre.getName() || costCentre.getName().isEmpty() || costCentre.getName().trim().equals("") ) {
				result.setError( Util.COST_INVALID_NAME );
			}
			if ( null == costCentre.getCode() || costCentre.getCode().isEmpty() || costCentre.getCode().trim().equals("") ) {
				result.setError( Util.COST_INVALID_CODE );
			}
			break;
		default:
			break;
		}
		if ( result.isSuccess() ) {
			result.setSuccess( Util.SAVE_SUCCESSFUL_COST_CENTRE );
		}
		return result;
	}

}
