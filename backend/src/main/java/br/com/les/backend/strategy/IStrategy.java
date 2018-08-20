package br.com.les.backend.strategy;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Resultado;
/**
 * 
 * @author José Wesley
 *
 * interface to generate map of entities that have implemented strategies
 */
public interface IStrategy {

    Resultado execute(EntidadeDominio entidade, String action );
}
