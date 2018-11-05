package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.HourType;

@Controller
@RequestMapping("${server.controller.prefix}hourtype")
public class HourTypeController extends AbstractController<HourType> {
	
	public HourTypeController() {
		super(HourType.class);
	}
}