package br.com.les.backend.strategy.usuario;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Resultado;

public interface IUsuarioStrategy {

	Resultado execute( DomainEntity entidade, String callerMethod );
}
