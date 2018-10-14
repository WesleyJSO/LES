package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Request;

@Controller
@RequestMapping("${server.controller.prefix}request")
public class RequestController extends AbstractController<Request> {
	
	public RequestController() {
		super(Request.class);
	}
}
