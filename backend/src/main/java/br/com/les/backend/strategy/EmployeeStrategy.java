package br.com.les.backend.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.strategy.funcionario.IEmployeeStrategy;
import br.com.les.backend.strategy.funcionario.SearchFuncionarioStrategy;
import br.com.les.backend.utils.Result;

@Component( "Funcionario" )
public class EmployeeStrategy implements IStrategy {

	@Autowired
	List< IEmployeeStrategy > actionStrategies = new ArrayList<>();
	
	@Override
	public Result execute( EntidadeDominio entity, String action, String callerMethod ) {
		
		IEmployeeStrategy strategyToBeExecuted = null;
		
		for ( IEmployeeStrategy s : actionStrategies )
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
		//Resultado resultado = new Resultado();
		//resultado.setSucesso("");
		//return resultado;
		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}
