package br.com.les.backend.strategy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.strategy.funcionario.IFuncionarioStrategy;
import br.com.les.backend.strategy.funcionario.SearchFuncionarioStrategy;
import br.com.les.backend.utils.Resultado;

@Component( "Funcionario" )
public class FuncionarioStrategy implements IStrategy {

	@Autowired
	List< IFuncionarioStrategy > actionStrategies = new ArrayList<>();
	
	@Override
	public Resultado execute( EntidadeDominio entidade, String action, String callerMethod ) {
		
		IFuncionarioStrategy strategyToBeExecuted = null;
		
		for ( IFuncionarioStrategy s : actionStrategies )
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
		//Resultado resultado = new Resultado();
		//resultado.setSucesso("");
		//return resultado;
		return strategyToBeExecuted.execute( entidade, callerMethod );
	}

}
