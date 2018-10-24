package br.com.les.backend.strategy.costcentre;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteCostCentreStrategy extends AbstractStrategy<CostCentre> {

	@Override
	public Result<CostCentre> execute(CostCentre entity) {
		Result<CostCentre> result = new Result<>();
		
		return result;
	}

}
