package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.strategy.usuario.IUsuarioStrategy;
import br.com.les.backend.utils.Resultado;

@Component( "Usuario" )
public class UsuarioStrategy implements IStrategy {

	@Autowired
	List< IUsuarioStrategy > actionStrategies;
	
	@Override
	public Resultado execute(EntidadeDominio entidade, String action ) {
		
		IUsuarioStrategy strategyToBeExecuted = null;
		
		for ( IUsuarioStrategy s : actionStrategies )
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
		
		return strategyToBeExecuted.execute( entidade );
	}

}
