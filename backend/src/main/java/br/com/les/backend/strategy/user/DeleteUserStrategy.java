package br.com.les.backend.strategy.user;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class DeleteUserStrategy extends AbstractUserStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod ) {

		return result;
	}

}
