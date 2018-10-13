package br.com.les.backend.facade;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.service.IService;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;


@Component
public class Facade extends AbstractFacade {

    private Result result;
    
    private IService service;

    @Override
    protected void validate( DomainEntity clazz, String action, String callerMethod ) {
    	
    	for (IService s : listServices )
			if ( s.getClass().getName().contains(clazz.getClass().getSimpleName())) {
				service = s;
				break;
			}

    	result = mapStrategies.get(clazz.getClass().getSimpleName()).execute( clazz, action, callerMethod );
    }

    @Override
    public Result save( DomainEntity clazz, String callerMethod ) {

    	validate( clazz, Actions.SAVE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		result.getResultList().add( service.save( clazz ) );
    	
    	return result;
    }

    @Override
    public Result update(DomainEntity clazz, String callerMethod ) {

    	validate( clazz, Actions.UPDATE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		result.getResultList().add( service.save( clazz ) );
    	return result;
    }

    @Override
    public Result delete( DomainEntity clazz, String callerMethod ) {
    	validate( clazz, Actions.DELETE.getValue(), callerMethod );
    	
    	if ( result.getMessage() == null ) {
        	result.softDeleteStatus( service.softDelete( clazz ) );
    	}
    	
    	return result;
    }

    @Override
    public Result findAll( DomainEntity clazz, String callerMethod ) {
    		
    	validate( clazz, Actions.SEARCH.getValue(), callerMethod );
    	result.setResultList( service.findAll() );
    	return result;    	
    }

    @Override
    public Result find( DomainEntity clazz, String callerMethod ) {
        
    	result = new Result();
 
    	validate( clazz, Actions.SEARCH.getValue(), callerMethod  );
    	
    	if ( result.getMessage() == null ) {
    		result.getResultList().addAll( service.findByParameters( clazz ) );
    		return result;
    	} else 
    		return result;
    	
    }
}
