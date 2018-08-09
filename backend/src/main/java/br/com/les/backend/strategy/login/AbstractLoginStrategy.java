package br.com.les.backend.strategy.login;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.les.backend.Utils.Resultado;

public abstract class AbstractLoginStrategy implements ILoginStrategy {

	@Autowired
	Resultado resultado;
}
