package br.com.les.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.command.ICommand;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.facade.IFacade;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

public abstract class AbstractController<T extends DomainEntity> extends BaseController {

	protected Class<? extends T> clazz;
	
	protected IFacade<T> facade;
    
	@Autowired
    List < ICommand<T> > command;
   
    Result<T> result;
    
    
    public AbstractController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}
    
	@GetMapping
	public @ResponseBody Result<T> findAll(T entity) throws InstantiationException, IllegalAccessException {
		return run(Actions.FIND_ALL.getValue()).execute(clazz.newInstance(), Actions.FIND_ALL.getValue());
	}
    
    @PostMapping
    public @ResponseBody Result<T> save(T entity) throws InstantiationException, IllegalAccessException {
    	return run(Actions.SAVE.getValue()).execute(clazz.newInstance(), Actions.SAVE.getValue());
    }
    
    @PutMapping
    public @ResponseBody Result<T> update(T entity) throws InstantiationException, IllegalAccessException {
    	return run(Actions.UPDATE.getValue()).execute(clazz.newInstance(), Actions.UPDATE.getValue());
    }
    
    @PostMapping(value="/{id}")
	public @ResponseBody Result<T> findById(T entity) throws InstantiationException, IllegalAccessException {
		return run(Actions.FIND_BY_ID.getValue()).execute(clazz.newInstance(), Actions.FIND_BY_ID.getValue());
	}
    
    @DeleteMapping( value="/{id}" ) 
    public @ResponseBody Result<T> delete( @PathVariable( "id" ) Long id) throws InstantiationException, IllegalAccessException {
    	Result<T> entity = facade.findById(id, clazz);
    	return run(Actions.DELETE.getValue()).execute(entity.getResultList().get(0), Actions.DELETE.getValue());
    }
	
    protected String getMethodName( Object o ) {
		return o.getClass().getEnclosingMethod().getName();
	}
    
    protected ICommand<T> run ( String action) {
    	for (ICommand<T> iCommand : command) {
			if ( iCommand.getClass().getName().contains( action ) )
				return iCommand;
		}
    	return null;
    }
}
