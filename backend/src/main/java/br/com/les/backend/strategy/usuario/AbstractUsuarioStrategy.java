package br.com.les.backend.strategy.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.les.backend.Utils.Resultado;

public abstract class AbstractUsuarioStrategy implements IUsuarioStrategy {

	@Autowired
	protected Resultado resultado;
}
