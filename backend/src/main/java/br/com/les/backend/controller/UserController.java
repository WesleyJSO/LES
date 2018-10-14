package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.User;

@Controller
@RequestMapping("${server.controller.prefix}user")
public class UserController extends AbstractController<User> {
	
	public UserController() {
		super(User.class);
	}
}
