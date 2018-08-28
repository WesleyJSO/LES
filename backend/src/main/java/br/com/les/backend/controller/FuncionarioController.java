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
import br.com.les.backend.entity.Funcionario;
import br.com.les.backend.utils.Resultado;


@CrossOrigin
@RestController
public class FuncionarioController extends AbstractController {
		
	@GetMapping( value="/funcionario" )
	public Resultado findAll() {
		
		return fachada.findAll( new Funcionario(), getMethodName( new Object() {} ) );
	}
	
	@GetMapping( value="/usuarioFuncionario" )
	public Resultado findByUsuario( @RequestBody Funcionario funcionario ) {
		
		return fachada.find( funcionario, getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/funcionario" )
	public Resultado save( @RequestBody Funcionario funcionario ) {
		
		return fachada.save( ( EntidadeDominio ) funcionario, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/funcionario" )
	public Resultado update( @RequestBody Funcionario funcionario ) {
		
		return fachada.update( funcionario, getMethodName( new Object() {} ) );
	}
	
	
	@DeleteMapping( value="/funcionario/{id}" )
	public Resultado delete( @PathVariable( "id" ) Long idFuncionario ) {
		
		return fachada.delete( new Funcionario( idFuncionario ), getMethodName( new Object() {} ) );
	}
}
