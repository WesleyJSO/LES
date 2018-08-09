package br.com.les.backend.strategy.login;

import org.springframework.stereotype.Component;

import br.com.les.backend.Utils.Resultado;
import br.com.les.backend.entity.EntidadeDominio;

/**
 * 
 * @author José Wesley
 *
 * This class execute the business logic, for his name type class ( @Login ), 
 * each type of command will have a chasse to execute their own rules ( @SEARCH rules )
 */
@Component
public class SearchLoginStrategy extends AbstractLoginStrategy {

	@Override
	public Resultado execute( EntidadeDominio entidade ) {
		
		return resultado;
	}

}
