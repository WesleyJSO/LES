package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Role;

@Controller
@RequestMapping("${server.controller.prefix}role")
public class RoleController extends AbstractController<Role> {
	
	public RoleController() {
		super(Role.class);
	}
}
