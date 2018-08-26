package br.com.les.backend.strategy.usuario;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Resultado;

public interface IUsuarioStrategy {

	Resultado execute( EntidadeDominio entidade, String callerMethod );
}
