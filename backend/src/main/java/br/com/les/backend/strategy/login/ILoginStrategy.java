package br.com.les.backend.strategy.login;

import br.com.les.backend.Utils.Resultado;
import br.com.les.backend.entity.EntidadeDominio;

/**
 * 
 * @author José Wesley
 *
 * Interface to execute selected strategy with business rules,
 * the strategy will be of type @Login and will correlate to a @action
 */
public interface ILoginStrategy {

	Resultado execute( EntidadeDominio entidade );
}
