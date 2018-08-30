package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Resultado;


/**
 *
 * @author  José Wesley
 *
 *  this class contais base facade methods that makes most commons system needed funcitions
 */
public interface IFachada{

    Resultado save( DomainEntity entidade, String callerMethod );

    Resultado update( DomainEntity entidade, String callerMethod );

    Resultado delete( DomainEntity entidade, String callerMethod );

    Resultado findAll( DomainEntity entidade, String callerMethod );

    Resultado find( DomainEntity entidade, String callerMethod );
}
