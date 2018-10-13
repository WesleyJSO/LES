package br.com.les.backend.strategy.role;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface IRoleStrategy {
	
	Result execute(DomainEntity entity, String callerMethod );
	
}
