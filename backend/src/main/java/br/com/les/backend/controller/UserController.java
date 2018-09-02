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
import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Result;


@CrossOrigin
@RestController
public class UserController extends AbstractController {
	
	@GetMapping( value="/Usuario" )
	public Result findAll() {
		
		return facade.find( new User(), getMethodName( new Object() {} ) );
	}
	
	@GetMapping( value="/UsuarioConsulta" )
	public Result findByUser( @RequestBody User user ) {
		
		return facade.find( user, getMethodName( new Object() {} ) );
	}
	
	// waiting for a user with a login filled by nomeLogin and senha
	@PostMapping( value="/UsuarioLogin" )
	public Result findByLogin( @RequestBody User user ) {
		
		return facade.find( user, getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/Usuario" )
	public Result save( @RequestBody User user ) {
		
		return facade.save( ( DomainEntity ) user, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/Usuario" )
	public Result update( @RequestBody User user ) {
		
		return facade.update( user, getMethodName( new Object() {} ) );
	}
	
	@DeleteMapping( value="/user/{id}" )
	public Result delete( @PathVariable( "id" ) Long userId ) {
		
		return facade.delete( new User( userId ), getMethodName( new Object() {} ) );
	}
}