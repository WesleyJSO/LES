package br.com.les.backend.strategy.funcionario;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Resultado;

public interface IFuncionarioStrategy {

	Resultado execute( DomainEntity entidade, String callerMethod );
}
