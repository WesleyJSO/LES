package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Usuario;
import br.com.les.backend.utils.Resultado;


@CrossOrigin
@RestController
public class UsuarioController extends AbstractController {
	
	@GetMapping( value="/usuario" )
	public Resultado findAll() {
		
		return fachada.find( new Usuario(), getMethodName( new Object() {} ) );
	}
	
	@GetMapping( value="/usuarioConsulta" )
	public Resultado findByUsuario( @RequestBody Usuario usuario ) {
		
		return fachada.find( usuario, getMethodName( new Object() {} ) );
	}
	
	// waiting for a user with a login filled by nomeLogin and senha
	@PostMapping( value="/usuarioLogin" )
	public Resultado findByLogin( @RequestBody Usuario usuario ) {
		
		return fachada.find( usuario, getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/usuario" )
	public Resultado save( @RequestBody Usuario usuario ) {
		
		return fachada.save( ( EntidadeDominio ) usuario, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/usuario" )
	public Resultado update( @RequestBody Usuario usuario ) {
		
		return fachada.update( usuario, getMethodName( new Object() {} ) );
	}
	
	
	@DeleteMapping( value="/usuario/{id}" )
	public Resultado delete( @PathVariable( "id" ) Long idUsuario ) {
		
		return fachada.delete( new Usuario( idUsuario ), getMethodName( new Object() {} ) );
	}
}
