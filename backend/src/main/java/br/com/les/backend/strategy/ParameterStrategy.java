package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.Company.ICompanyStrategy;
import br.com.les.backend.strategy.parameter.IParameterStrategy;
import br.com.les.backend.utils.Result;

@Component( "Parameter" )
public class ParameterStrategy implements IStrategy {

	@Autowired
	List< IParameterStrategy > actionStrategies;
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {

		IParameterStrategy strategyToBeExecuted = null;
		
		for ( IParameterStrategy s : actionStrategies ) 
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
				
		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}
