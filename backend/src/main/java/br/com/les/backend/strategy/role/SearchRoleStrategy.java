package br.com.les.backend.strategy.role;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Role;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class SearchRoleStrategy implements IApplicationStrategy<Role> {

	@Override
	public Result<Role> execute(Role entity, String callerMethod) {
		Result<Role> result = new Result<>();
		switch (callerMethod) {
		case "find":
			break;

		default:
			break;
		}
		return result;
	}

}
