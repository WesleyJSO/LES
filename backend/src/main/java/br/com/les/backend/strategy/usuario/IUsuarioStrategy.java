package br.com.les.backend.strategy.usuario;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Result;

public interface IUsuarioStrategy {

	Result execute( EntidadeDominio entidade, String callerMethod );
}
