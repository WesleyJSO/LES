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
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class CompanyController extends AbstractController {
	
	@GetMapping(value = "/company" )
	public Result findAll() {
		return facade.findAll( new Company(), getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/company" )
	public Result save( @RequestBody Company company ) {
		return facade.save( ( DomainEntity ) company, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/company" )
	public Result update( @RequestBody Company company ) {
		return facade.update( company, getMethodName( new Object() {} ) );
	}
	
	@DeleteMapping( value="/company")
	public Result delete( @PathVariable( "id" ) Long companyId ) {
		return facade.delete( new Company( companyId ), getMethodName( new Object() {} ) );
	}
}
