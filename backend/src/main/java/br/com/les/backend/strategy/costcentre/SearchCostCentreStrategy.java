package br.com.les.backend.strategy.costcentre;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.employee.AbstractEmployeeStrategy;
import br.com.les.backend.utils.Result;

@Component
public class SearchCostCentreStrategy extends AbstractCostCentreStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		result = new Result();
		CostCentre costCentre = ( CostCentre ) entity;
		
		switch ( callerMethod ) {
		case "FindAll":
			break;
		default:
			break;
		}
		return result;
	}

}
