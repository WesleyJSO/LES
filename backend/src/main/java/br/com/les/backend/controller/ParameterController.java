package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Parameter;

@Controller
@RequestMapping("${server.controller.prefix}parameter")
public class ParameterController extends AbstractController<Parameter> {
	
	public ParameterController() {
		super(Parameter.class);
	}
}