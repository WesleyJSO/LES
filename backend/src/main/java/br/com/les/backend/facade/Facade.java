package br.com.les.backend.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.service.AbstractService;
import br.com.les.backend.strategy.GenericStrategy;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;


@Component
public class Facade<T extends DomainEntity> implements IFacade<T> {

    private Result<T> result;
    
    private AbstractService<T> service;
    
	@Autowired
	protected List< AbstractService<T> > listServices;
	
    @Autowired
    protected GenericStrategy<T> strategy;
    
    @Override
    public void validate(T clazz, String action, String callerMethod ) {
    	
    	for (AbstractService<T> s : listServices )
			if (s.getClass().getSimpleName().contains(clazz.getClass().getSimpleName())) {
				service = s;
				break;
			}
    	result = strategy.execute( clazz, action, callerMethod );
    }

    @Override
    public Result<T> save(T clazz, String callerMethod ) {

    	validate( clazz, Actions.SAVE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		result.getResultList().add( service.save( clazz ) );
    	return result;
    }

    @Override
    public Result<T> update(T clazz, String callerMethod ) {

    	validate( clazz, Actions.UPDATE.getValue(), callerMethod  );
    	if ( result.isSuccess() )
    		result.getResultList().add( service.update( clazz ) );
    	return result;
    }

    @Override
    public Result<T> delete(T clazz, String callerMethod ) {
    	validate( clazz, Actions.DELETE.getValue(), callerMethod );
    	
    	if ( result.getMessage() == null ) {
        	if(service.setInactiveById(clazz))
        		result.setSuccess("Removido com sucesso.");
        	else
        		result.setError("Código inválido!");
    	}
    	return result;
    }

    @Override
    public Result<T> findAll(T clazz, String callerMethod ) {
    		
    	validate( clazz, Actions.SEARCH.getValue(), callerMethod );
    	result.setResultList(service.findByActive(clazz));
    	return result;
    }

    @Override
    public Result<T> find(T clazz, String callerMethod ) {
        
    	result = new Result<T>();
 
    	validate( clazz, Actions.SEARCH.getValue(), callerMethod  );
    	
    	if ( result.getMessage() == null ) {
    		result.getResultList().addAll( service.findByParameters( clazz ) );
    		return result;
    	} else 
    		return result;
    	
    }

	@Override
	public Result<T> findById(Long id, Class<? extends T> clazz) {
		Result<T> result = new Result<>();
		result.getResultList().add(service.findById(id, clazz));
		return result;
	}
}
