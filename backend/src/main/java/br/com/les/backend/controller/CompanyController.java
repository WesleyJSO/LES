package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class CompanyController extends AbstractController {
	
	@GetMapping(value = "/company" )
	public Result findAll() {
		return run(Actions.FIND_ALL.getValue()).execute(new Company( true ), Actions.FIND_ALL.getValue());
		// return facade.findAll( new Company( true ), getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/company" )
	public Result save( @RequestBody Company company ) {
		return run(Actions.SAVE.getValue()).execute(company, Actions.SAVE.getValue());
		// return facade.execute( ( DomainEntity ) company, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/company" )
	public Result update( @RequestBody Company company ) {
		return run(Actions.UPDATE.getValue()).execute(company, Actions.UPDATE.getValue());
		// return facade.update( company, getMethodName( new Object() {} ) );
	}
	
	@DeleteMapping( value="/company/{id:[\\d]+}")
	public Result delete( @PathVariable Long id ) {
		return run(Actions.DELETE.getValue()).execute(new Company( id ), Actions.DELETE.getValue());
		// return facade.delete( new Company( id ), getMethodName( new Object() {} ) );
	}
}
