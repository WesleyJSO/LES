package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.les.backend.entity.Request;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class RequestController extends AbstractController {
	
	@PostMapping( value="/request" )
	public Result saveRequest (@RequestBody Request resquest) {
		return run(Actions.SAVE.getValue()).execute(resquest, Actions.SAVE.getValue());
	}
	
	@GetMapping( value="/request" )
	public Result findAll () {
		return run ( Actions.FIND_ALL.getValue() ).execute(new Request() , Actions.FIND_ALL.getValue() );
	}
}
