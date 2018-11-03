package br.com.les.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.command.ICommand;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.BusinessCase;
import br.com.les.backend.navigator.BusinessCaseBuilder;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

public abstract class AbstractController<T extends DomainEntity> extends BaseController {

	Result<T> result;
	protected Class<? extends T> clazz;
	@Autowired List < ICommand<T> > command;
	@Autowired private Map<String, Navigation<T>> navigatorList;
    
    
    public AbstractController(Class<? extends T> clazz) {
		this.clazz = clazz;
	}
    
	@PatchMapping
	public @ResponseBody Result<T> find(@RequestBody T entity) throws InstantiationException, IllegalAccessException {
		
		BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
				.withName(existingNavigation("FIND_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
		
		return run(Actions.FIND.getValue()).execute(entity, bCase);
	}
    
    @PostMapping
    public @ResponseBody Result<T> save(@RequestBody T entity) throws InstantiationException, IllegalAccessException {

    	BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
				.withName(existingNavigation("SAVE_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
    	
		return run(Actions.SAVE.getValue()).execute(entity, bCase);
    }
    
    @PutMapping
    public @ResponseBody Result<T> update(@RequestBody T entity) throws InstantiationException, IllegalAccessException {

    	BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
				.withName(existingNavigation("UPDATE_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
    	
		return run(Actions.UPDATE.getValue()).execute(entity, bCase);
    }
    
    @DeleteMapping
    public @ResponseBody Result<T> delete(@RequestBody T entity) throws InstantiationException, IllegalAccessException {

    	BusinessCase<T> bCase = new BusinessCaseBuilder<T>()
				.withName(existingNavigation("DELETE_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
    	
		return run(Actions.DELETE.getValue()).execute(entity, bCase);
    }
    
    protected ICommand<T> run ( String action) {
    	for (ICommand<T> iCommand : command) 
			if ( iCommand.getClass().getName().contains( action ) )
				return iCommand;
    	return null;
    }
    
    protected String existingNavigation(String name) {
		return navigatorList.containsKey(name) ? name : BusinessCase.DEFAULT_CONTEXT_NAME;
	}
}
