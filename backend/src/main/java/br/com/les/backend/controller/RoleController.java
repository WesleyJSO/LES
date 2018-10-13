package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.Role;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;


@CrossOrigin
@RestController
public class RoleController extends AbstractController {
		
	@GetMapping( value="/role" )
	public Result findAll() {
		return run(Actions.FIND_ALL.getValue()).execute(new Role(), Actions.FIND_ALL.getValue());
	}
}
