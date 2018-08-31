package br.com.les.backend.strategy;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;
/**
 * 
 * @author José Wesley
 *
 * interface to generate map of entities that have implemented strategies
 */
public interface IStrategy {

    Result execute(EntidadeDominio entidade, String action, String callerMethod );
}
