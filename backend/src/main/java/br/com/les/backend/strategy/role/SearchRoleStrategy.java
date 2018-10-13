package br.com.les.backend.strategy.role;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class SearchRoleStrategy extends AbstractRoleStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		result = new Result();
		switch (callerMethod) {
		case "find":
			break;

		default:
			break;
		}
		return result;
	}

}
