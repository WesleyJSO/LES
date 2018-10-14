package br.com.les.backend.strategy.user;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteUserStrategy<T extends DomainEntity> implements IApplicationStrategy<T> {

	@Override
	public Result<T> execute(DomainEntity entity, String callerMethod ) {

		return new Result<T>();
	}

}
