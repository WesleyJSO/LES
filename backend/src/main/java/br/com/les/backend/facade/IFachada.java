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

    Resultado save( EntidadeDominio entidade );

    Resultado update( EntidadeDominio entidade );

    Resultado delete( EntidadeDominio entidade );

    Resultado findAll( EntidadeDominio entidade );

    Resultado findByEntidadeDominio( EntidadeDominio entidade );
}
