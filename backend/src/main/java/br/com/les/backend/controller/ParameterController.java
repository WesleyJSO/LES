package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;


@CrossOrigin
@RestController
public class ParameterController extends AbstractController {
	
	@GetMapping( value="/parameter" )
	public Result findActive() {
		return run(Actions.SEARCH.getValue()).execute(new Parameter( true ), Actions.SEARCH.getValue());
		// return facade.find( new Parameter( true ), getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/parameter" )
	public Result save( @RequestBody Parameter parameter ) {
		return run(Actions.SAVE.getValue()).execute(parameter, Actions.SAVE.getValue());
		// return facade.save( ( DomainEntity ) parameter, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/parameter" )
	public Result update( @RequestBody Parameter parameter ) {
		return run(Actions.UPDATE.getValue()).execute(parameter, Actions.UPDATE.getValue());
		// return facade.update( parameter, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/parameterSaveUpdate" )
	public Result updateSave( @RequestBody Parameter parameter ) {
		return run(Actions.UPDATE.getValue()).execute(parameter, Actions.SAVE_UPDATE.getValue());
		// return facade.update( parameter, getMethodName( new Object() {} ) );
	}
}
