package br.com.les.backend.strategy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.User;
import br.com.les.backend.service.GenericService;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.EmailServiceImpl;
import br.com.les.backend.utils.Result;

@Component
public class FindUserStrategy extends AbstractStrategy<User> {

	@Autowired GenericService<User> userService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Override
	public Result<User> execute( User user) {
		
		Result<User> result = new Result<>();
		
		return result;
	}
}
