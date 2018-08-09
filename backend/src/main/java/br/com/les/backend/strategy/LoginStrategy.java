package br.com.les.backend.strategy;

import br.com.les.backend.Utils.Resultado;
import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.strategy.login.ILoginStrategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component( "Login" )
public class LoginStrategy implements IStrategy {

	@Autowired
	List< ILoginStrategy > actionStrategies;
	
    @Override
    public Resultado execute(EntidadeDominio entidade, String action ) {

    	ILoginStrategy strategyToBeExecuted = null;
    	
    	for (ILoginStrategy s : actionStrategies )
    		if ( s.getClass().getSimpleName().contains( action ) )
    			strategyToBeExecuted = s;
		
    	return strategyToBeExecuted.execute( entidade );
    }
}
