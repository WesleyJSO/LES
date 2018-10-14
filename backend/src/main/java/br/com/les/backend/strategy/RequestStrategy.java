package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.Request.IRequestStrategy;
import br.com.les.backend.strategy.parameter.IParameterStrategy;
import br.com.les.backend.utils.Result;

@Component
public class RequestStrategy implements IStrategy {

	@Autowired
	List< IRequestStrategy > actionStrategies;
	
	@Override
	public Result run( DomainEntity entity, String action, String callerMethod ) {

		IRequestStrategy strategyToBeExecuted = null;
		
		for ( IRequestStrategy s : actionStrategies ) 
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
				
		return strategyToBeExecuted.run( entity, callerMethod );
	}

	
}
