package br.com.les.backend.strategy.Request;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface IRequestStrategy {
	
	Result execute( DomainEntity entity, String callerMethod );

}
