package br.com.les.backend.strategy.costcentre;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface ICostCentreStrategy {
	
	Result execute( DomainEntity entity, String callerMethod );

}
