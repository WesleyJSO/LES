package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;


/**
 *
 * @author  José Wesley
 *
 *  this class contais base facade methods that makes most commons system needed funcitions
 */
public interface IFacade {

    Result save( DomainEntity entity, String callerMethod );

    Result update( DomainEntity entity, String callerMethod );

    Result delete( DomainEntity entity, String callerMethod );

    Result findAll( DomainEntity entity, String callerMethod );

    Result find( DomainEntity entity, String callerMethod );
}
