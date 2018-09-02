package br.com.les.backend.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.employee.IEmployeeStrategy;
import br.com.les.backend.strategy.employee.SearchEmployeeStrategy;
import br.com.les.backend.utils.Result;

@Component( "Employee" )
public class EmployeeStrategy implements IStrategy {

	@Autowired
	List< IEmployeeStrategy > actionStrategies = new ArrayList<>();
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {
		
		IEmployeeStrategy strategyToBeExecuted = null;
		
		for ( IEmployeeStrategy s : actionStrategies )
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
		//Resultado resultado = new Resultado();
		//resultado.setSuccess("");
		//return resultado;
		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}
