package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.role.IRoleStrategy;
import br.com.les.backend.utils.Result;

@Component( "Role" )
public class RoleStrategy implements IStrategy {

	@Autowired
	List<IRoleStrategy> actionStrategies;
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {
		
		IRoleStrategy strategyToBeExecuted = null;
		
		for (IRoleStrategy s : actionStrategies)
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;

		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}
