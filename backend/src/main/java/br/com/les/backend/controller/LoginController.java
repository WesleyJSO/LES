package br.com.les.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Login;

@CrossOrigin
@RestController
public class LoginController extends AbstractController {

	@Autowired
	Login login;
	
	@GetMapping( value="/login" )
	public List< EntidadeDominio > findAll() {
		
		return fachada.findAll( login ).getListaResultado();
	}

	@PostMapping( value="/login" )
	public EntidadeDominio findByNomeAndSenha( @RequestBody Login login ) {
		
	    resultado = fachada.findByEntidade( ( EntidadeDominio ) login );
	    for ( EntidadeDominio e : resultado.getListaResultado() ) {
	    	( ( Login ) e ).getUsuario().setLogin( null );
	    }
	    return resultado.getListaResultado().get( 0 );
	    
	}
	
	@PutMapping( value="/login" )
	public void update( @RequestBody Login login ) {
	    
		fachada.update( ( EntidadeDominio) login );
	}
	
	@DeleteMapping( value="/login/{id}" )
	public void delete( @PathVariable( "id" ) Long idLogin ) {
		
		login.setIdLogin( idLogin );
		fachada.delete( (EntidadeDominio ) login );
	}
}
