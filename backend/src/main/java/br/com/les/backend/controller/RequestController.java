package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.entity.Request;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class RequestController extends AbstractController<Request> {
	
	public RequestController() {
		super(Request.class);
	}
}
