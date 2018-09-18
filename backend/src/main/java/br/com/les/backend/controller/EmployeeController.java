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
import br.com.les.backend.utils.Result;


@CrossOrigin
@RestController
public class EmployeeController extends AbstractController {
		
	@GetMapping( value="/funcionario" )
	public Result findAll() {
		return run("FindAll").execute(new Employee(), "findAll");
		// return facade.facade( new Employee(), getMethodName( new Object() {} ) );
	}
	
	@GetMapping( value="/usuarioFuncionario" )
	public Result findByUsuario( @RequestBody Employee employee ) {
		return run("Search").execute(employee, "find");
		// return facade.find( employee, getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/funcionario" )
	public Result save( @RequestBody Employee employee ) {
		return run("Save").execute(employee, "save");
		// return facade.save( ( DomainEntity ) employee, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/funcionario" )
	public Result update( @RequestBody Employee employee ) {
		return run("Update").execute(employee, "update");
		// return facade.update( employee, getMethodName( new Object() {} ) );
	}
	
	
	@DeleteMapping( value="/funcionario/{id}" )
	public Result delete( @PathVariable( "id" ) Long employeeId ) {
		return run("Delete").execute(new Employee( employeeId ), "delete");
		// return facade.delete( new Employee( employeeId ), getMethodName( new Object() {} ) );
	}
}
