package br.com.les.backend.strategy.usuario;

import br.com.les.backend.Utils.Resultado;
import br.com.les.backend.entity.EntidadeDominio;

public interface IUsuarioStrategy {

	Resultado execute( EntidadeDominio entidade );
}
