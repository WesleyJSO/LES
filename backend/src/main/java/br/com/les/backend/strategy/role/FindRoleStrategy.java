package br.com.les.backend.strategy.role;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Role;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class FindRoleStrategy implements IApplicationStrategy<Role> {

	@Override
	public Result<Role> execute(Role entity) {
		Result<Role> result = new Result<>();
		return result;
	}

}
