package br.com.les.backend.facade;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Result;


/**
 *
 * @author  José Wesley
 *
 *  this class contais base facade methods that makes most commons system needed funcitions
 */
public interface IFacade {

    Result save( EntidadeDominio entity, String callerMethod );

    Result update( EntidadeDominio entity, String callerMethod );

    Result delete( EntidadeDominio entity, String callerMethod );

    Result findAll( EntidadeDominio entity, String callerMethod );

    Result find( EntidadeDominio entity, String callerMethod );
}
