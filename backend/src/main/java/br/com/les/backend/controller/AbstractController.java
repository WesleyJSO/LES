package br.com.les.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.command.ICommand;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

public abstract class AbstractController<T extends DomainEntity> extends BaseController {

	protected Class<? extends T> clazz;
    
	@Autowired
    List < ICommand<T> > command;
   
    Result<T> result;
    
    
    public AbstractController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}
    
	@PatchMapping
	public @ResponseBody Result<T> find(@RequestBody T entity) throws InstantiationException, IllegalAccessException {
		return run(Actions.FIND.getValue()).execute(entity);
	}
    
    @PostMapping
    public @ResponseBody Result<T> save(@RequestBody T entity) throws InstantiationException, IllegalAccessException {
    	return run(Actions.SAVE.getValue()).execute(entity);
    }
    
    @PutMapping
    public @ResponseBody Result<T> update(@RequestBody T entity) throws InstantiationException, IllegalAccessException {
    	return run(Actions.UPDATE.getValue()).execute(entity);
    }
    
    @DeleteMapping
    public @ResponseBody Result<T> delete(@RequestBody T entity) throws InstantiationException, IllegalAccessException {
    	return run(Actions.DELETE.getValue()).execute(entity);
    }
    
    protected ICommand<T> run ( String action) {
    	for (ICommand<T> iCommand : command) 
			if ( iCommand.getClass().getName().contains( action ) )
				return iCommand;
    	return null;
    }
}
