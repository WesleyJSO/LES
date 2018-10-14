package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.Request;

@CrossOrigin
@RestController
public class RequestController extends AbstractController<Request> {
	
	public RequestController() {
		super(Request.class);
	}
}
