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
import br.com.les.backend.entity.Funcionario;
import br.com.les.backend.entity.Usuario;
import br.com.les.backend.service.UsuarioService;


@CrossOrigin
@RestController
public class UsuarioController extends AbstractController {

	@Autowired
	Usuario usuario;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping( value="/usuario" )
	public List< EntidadeDominio > findAll() {
		
		resultado = fachada.findAll( usuario );
		return resultado.getListaResultado();
	}
	
	@PostMapping( value="/usuarioConsulta" )
	public List< EntidadeDominio > findByUsuario( @RequestBody Funcionario usuario ) {
		
		resultado = fachada.findByEntidadeDominio( usuario );		
		return resultado.getListaResultado();
	}
	
	@PostMapping( value="/usuario" )
	public String[] save( @RequestBody Funcionario usuario ) {
		return fachada.save( ( EntidadeDominio ) usuario ).getMensagem();
	}
	
	@PutMapping( value="/usuario" )
	public void update( @RequestBody Funcionario usuario ) {
		usuarioService.save( usuario );
	}
	
	@DeleteMapping( value="/usuario/{id}" )
	public void delete( @PathVariable( "id" ) Long idUsuario ) {
		usuarioService.deleteById( idUsuario );
	}
}
