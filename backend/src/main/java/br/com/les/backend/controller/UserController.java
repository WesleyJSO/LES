package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Result;


@CrossOrigin
@RestController
public class UserController extends AbstractController {
	
	@GetMapping( value="/Usuario" )
	public Result findAll() {
		
		/* TODO:
		 *  Think in better solutions
		 * to the 'dynamic' inject the 
		 * corresponding command instead take it from
		 * a list of commands (run method) and
		 * use literal strings ('search').
		 */
		return run("FindAll").execute(new User(), "find");
	}
	
	@GetMapping( value="/UsuarioConsulta" )
	public Result findByUser( @RequestBody User user ) {
		
		return run("Search").execute(user, "find");
	}
	
	@GetMapping( value="/UsuarioEmail" )
	public Result sentChangePasswordEmail( @RequestParam( "email" ) String email ) {
		
		User user = new User();
		user.setEmail(email);
		return run("Search").execute(user, "find");
		//return command.find( user, getMethodName( new Object() {} ) );
	}
	
	// Expected a user login filled by nomeLogin and senha
	@PostMapping( value="/UsuarioLogin" )
	public Result findByLogin( @RequestBody User user ) {
		
		return run("Search").execute(user, "find");
	}
	
	@PostMapping( value="/Usuario" )
	public Result save( @RequestBody User user ) {
		
		return run("Save").execute(user, "save");
	}
	
	@PutMapping( value="/Usuario" )
	public Result update( @RequestBody User user ) {
		
		return run("Update").execute(user, "update");
	}
	
	@DeleteMapping( value="/Usuario/{id}" )
	public Result delete( @PathVariable( "id" ) Long userId ) {
		
		return run("Delete").execute(new User(userId), "delete");
	}
}