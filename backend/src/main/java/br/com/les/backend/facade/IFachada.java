package br.com.les.backend.facade;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Resultado;


/**
 *
 * @author  José Wesley
 *
 *  this class contais base facade methods that makes most commons system needed funcitions
 */
public interface IFachada{

    Resultado save( EntidadeDominio entidade, String callerMethod );

    Resultado update( EntidadeDominio entidade, String callerMethod );

    Resultado delete( EntidadeDominio entidade, String callerMethod );

    Resultado findAll( EntidadeDominio entidade, String callerMethod );

    Resultado find( EntidadeDominio entidade, String callerMethod );
}
