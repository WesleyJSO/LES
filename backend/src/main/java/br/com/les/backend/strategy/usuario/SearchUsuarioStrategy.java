package br.com.les.backend.strategy.usuario;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Resultado;

@Component
public class SearchUsuarioStrategy extends AbstractUsuarioStrategy {

	@Override
	public Resultado execute( EntidadeDominio entidade ) {
		
		return resultado;
	}	
}
