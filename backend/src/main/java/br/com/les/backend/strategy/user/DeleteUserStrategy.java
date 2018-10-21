package br.com.les.backend.strategy.user;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.User;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteUserStrategy implements IApplicationStrategy<User> {

	@Override
	public Result<User> execute(User entity) {

		return new Result<User>();
	}

}
