package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.service.IService;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

import org.springframework.stereotype.Component;


@Component
public class Facade extends AbstractFacade {

    private Result result;
    
    private IService service;

    @Override
    protected void validate( DomainEntity entity, String action, String callerMethod ) {

    	String entityName = entity.getClass().getSimpleName();
    	
    	for ( IService s : listServices )
			if ( s.getClass().getName().contains( entityName ) ) {
				service = s;
				break;
			}

    	result = mapStrategies.get( entityName ).execute( entity, action, callerMethod );
    }

    @Override
    public Result save( DomainEntity entity, String callerMethod ) {

    	validate( entity, Actions.SAVE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		result.getResultList().add( service.save( entity ) );
    	return result;
    }

    @Override
    public Result update(DomainEntity entity, String callerMethod ) {
    	validate( entity, Actions.SAVE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		service.save( entity );
    	return result;
    }

    @Override
    public Result delete( DomainEntity entity, String callerMethod ) {
    	validate( entity, Actions.SAVE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		service.delete( entity );
    	return result;
    }

    @Override
    public Result findAll( DomainEntity entity, String callerMethod ) {
    		
    	validate( entity, Actions.SEARCH.getValue(), callerMethod );
    	result.setResultList( service.findAll() );
    	return result;    	
    }

    @Override
    public Result find( DomainEntity entity, String callerMethod ) {
        
    	result = new Result();
 
    	validate( entity, Actions.SEARCH.getValue(), callerMethod  );
    	
    	if ( result.getMessage() == null ) {
    		result.getResultList().addAll( service.findByParameters( entity ) );
    		return result;
    	} else 
    		return result;
    	
    }
}
