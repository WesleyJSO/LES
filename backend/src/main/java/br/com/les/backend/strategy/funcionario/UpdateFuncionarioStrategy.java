package br.com.les.backend.strategy.funcionario;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.usuario.AbstractUsuarioStrategy;
import br.com.les.backend.utils.Resultado;

public class UpdateFuncionarioStrategy extends AbstractFuncionarioStrategy {

	@Override
	public Resultado execute(DomainEntity entidade, String callerMethod ) {

		return resultado;
	}

}
