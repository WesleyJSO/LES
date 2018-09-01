package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.user.IUserStrategy;
import br.com.les.backend.utils.Result;

@Component( "User" )
public class UserStrategy implements IStrategy {

	@Autowired
	List< IUserStrategy > actionStrategies;
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {
		
		IUserStrategy strategyToBeExecuted = null;
		
		for ( IUserStrategy s : actionStrategies )
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
		
		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}
