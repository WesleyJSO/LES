package br.com.les.backend.strategy.Company;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface ICompanyStrategy {

	Result execute( DomainEntity entity, String callerMethod );
}
