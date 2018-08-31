package br.com.les.backend.facade;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.service.IService;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

import org.springframework.stereotype.Component;


@Component
public class Facade extends AbstractFacade {

    private Result result;
    
    private IService service;

    @Override
    protected void validar( EntidadeDominio entity, String action, String callerMethod ) {

    	String entityName = entity.getClass().getSimpleName();
    	
    	for ( IService s : listServices )
			if ( s.getClass().getName().contains( entityName ) ) {
				service = s;
				break;
			}

    	result = mapStrategies.get( entityName ).execute( entity, action, callerMethod );
    }

    @Override
    public Result save( EntidadeDominio entity, String callerMethod ) {

    	validar( entity, Actions.SAVE.getValue(), callerMethod  );
    	if ( result.isSucesso() )
    		result.getListaResultado().add( service.save( entity ) );
    	return result;
    }

    @Override
    public Result update(EntidadeDominio entity, String callerMethod ) {
        return null;
    }

    @Override
    public Result delete( EntidadeDominio entity, String callerMethod ) {
    	return null;
    }

    @Override
    public Result findAll( EntidadeDominio entity, String callerMethod ) {
    		
    	validar( entity, Actions.SEARCH.getValue(), callerMethod );
    	result.setListaResultado( service.findAll() );
    	return result;    	
    }

    @Override
    public Result find( EntidadeDominio entity, String callerMethod ) {
        
    	result = new Result();
 
    	validar( entity, Actions.SEARCH.getValue(), callerMethod  );
    	
    	if ( result.getMensagem() == null ) {
    		result.getListaResultado().addAll( service.findByParameters( entity ) );
    		return result;
    	} else 
    		return result;
    	
    }
}
