package br.com.les.backend.strategy.parameter;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface IParameterStrategy {

	Result execute( DomainEntity entity, String callerMethod );

}
