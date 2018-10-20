package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@Controller
@RequestMapping("${server.controller.prefix}user")
public class UserController extends AbstractController<User> {
	
	public UserController() {
		super(User.class);
	}
	
	@PatchMapping
	public @ResponseBody Result<User> newPassword(User entity) {
		return run(Actions.NEW_PASSWORD.getValue()).execute(entity, Actions.NEW_PASSWORD.getValue());
	}
}
