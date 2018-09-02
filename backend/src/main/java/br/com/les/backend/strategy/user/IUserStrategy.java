package br.com.les.backend.strategy.user;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface IUserStrategy {

	Result execute( DomainEntity entity, String callerMethod );
}
