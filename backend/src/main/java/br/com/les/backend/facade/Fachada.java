package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.service.IService;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Resultado;

import org.springframework.stereotype.Component;


@Component
public class Fachada extends AbstractFachada {

    private Resultado resultado;
    
    private IService service;

    @Override
    protected void validar( DomainEntity entidade, String action, String callerMethod ) {

    	String nomeEntidade = entidade.getClass().getSimpleName();
    	
    	for ( IService s : listServices )
			if ( s.getClass().getName().contains( nomeEntidade ) ) {
				service = s;
				break;
			}

    	resultado = mapStrategies.get( nomeEntidade ).execute( entidade, action, callerMethod );
    }

    @Override
    public Resultado save( DomainEntity entidade, String callerMethod ) {

    	validar( entidade, Actions.SAVE.getValue(), callerMethod  );
    	if ( resultado.isSucesso() )
    		resultado.getListaResultado().add( service.save( entidade ) );
    	return resultado;
    }

    @Override
    public Resultado update(DomainEntity entidade, String callerMethod ) {
        return null;
    }

    @Override
    public Resultado delete( DomainEntity entidade, String callerMethod ) {
    	return null;
    }

    @Override
    public Resultado findAll( DomainEntity entidade, String callerMethod ) {
    		
    	validar( entidade, Actions.SEARCH.getValue(), callerMethod );
    	resultado.setListaResultado( service.findAll() );
    	return resultado;    	
    }

    @Override
    public Resultado find( DomainEntity entidade, String callerMethod ) {
        
    	resultado = new Resultado();
 
    	validar( entidade, Actions.SEARCH.getValue(), callerMethod  );
    	
    	if ( resultado.getMensagem() == null ) {
    		resultado.getListaResultado().addAll( service.findByParameters( entidade ) );
    		return resultado;
    	} else 
    		return resultado;
    	
    }
}
