package br.com.les.backend.facade;

import br.com.les.backend.Utils.Resultado;
import br.com.les.backend.entity.EntidadeDominio;


/**
 *
 * @author  José Wesley
 *
 *  this class contais base facade methods that makes most commons system needed funcitions
 */
public interface IFachada{

    Resultado save(EntidadeDominio entidade );

    Resultado update(EntidadeDominio entidade);

    void delete(EntidadeDominio entidade);

    Resultado findAll( EntidadeDominio entidade );

    Resultado findByEntidade(EntidadeDominio entidade);
}
