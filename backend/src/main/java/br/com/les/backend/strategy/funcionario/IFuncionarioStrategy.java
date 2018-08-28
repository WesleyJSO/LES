package br.com.les.backend.strategy.funcionario;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Resultado;

public interface IFuncionarioStrategy {

	Resultado execute( EntidadeDominio entidade, String callerMethod );
}
