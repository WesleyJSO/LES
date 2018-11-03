package br.com.les.backend.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.GenericStrategy;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;


@Component
public class Facade<T extends DomainEntity> implements IFacade<T> {

    private Result<T> result;
    
    @Autowired
    private GenericDAO<T> genericDAO;
	
    @Autowired
    protected GenericStrategy<T> strategy;
    
    @Override
    public void validate(T clazz, String action) {
    	result = strategy.execute( clazz, action);
    }

    @Override
    public Result<T> save(T clazz) {

    	validate(clazz, Actions.SAVE.getValue());
    	if ( result.isSuccess() )
    		result.getResultList().add( genericDAO.save( clazz ) );
    	return result;
    }

    @Override
    public Result<T> update(T clazz) {

    	validate( clazz, Actions.UPDATE.getValue());
    	if ( result.isSuccess() )
    		result.getResultList().add( genericDAO.update( clazz ) );
    	return result;
    }

    @Override
    public Result<T> delete(T clazz) {
    	validate( clazz, Actions.DELETE.getValue());
    	if ( result.isSuccess() )
    		genericDAO.delete( clazz );
    	return result;
    }
    
    @Override
    public Result<T> find(T clazz) {
    	validate( clazz, Actions.FIND.getValue());
    	if ( result.isSuccess() )
			result.getResultList().addAll( genericDAO.find( clazz ) );	
    	return result;
    	
    }
}
