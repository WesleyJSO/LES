package br.com.les.backend.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.costcentre.ICostCentreStrategy;
import br.com.les.backend.utils.Result;

@Component( "CostCentre" )
public class CostCentreStrategy implements IStrategy {

	@Autowired
	List< ICostCentreStrategy > actionStrategies;
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {
		
		ICostCentreStrategy strategyToBeExecuted = null;
		
		for ( ICostCentreStrategy s : actionStrategies )
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
		
		return strategyToBeExecuted.execute( entity, callerMethod );
	}


}
