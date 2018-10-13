package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;


@CrossOrigin
@RestController
public class EmployeeController extends AbstractController {
		
	@GetMapping( value="/employee" )
	public Result findAll() {
		return run(Actions.FIND_ALL.getValue()).execute(new Employee(), Actions.FIND_ALL.getValue());
		// return facade.findAll( new Employee(), getMethodName( new Object() {} ) );
	}
	
	@GetMapping( value="/userEmployee" )
	public Result findByUsuario( @RequestBody Employee employee ) {
		return run(Actions.SEARCH.getValue()).execute(employee, Actions.SEARCH.getValue());
		// return facade.find( employee, getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/employee" )
	public Result save( @RequestBody Employee employee ) {
		return run(Actions.SAVE.getValue()).execute(employee, Actions.SAVE.getValue());
		// return facade.save( ( DomainEntity ) employee, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/employee" )
	public Result update( @RequestBody Employee employee ) {
		return run(Actions.UPDATE.getValue()).execute(employee, Actions.UPDATE.getValue());
		// return facade.update( employee, getMethodName( new Object() {} ) );
	}
	
	
	@DeleteMapping( value="/employee/{id}" )
	public Result delete( @PathVariable( "id" ) Long employeeId ) {
		return run(Actions.DELETE.getValue()).execute(new Employee( employeeId ), Actions.DELETE.getValue());
		// return facade.delete( new Employee( employeeId ), getMethodName( new Object() {} ) );
	}
}
