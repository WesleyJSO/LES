package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Holiday;

@Controller
@RequestMapping("${server.controller.prefix}holiday")
public class HolidayController extends AbstractController<Holiday> {

	public HolidayController() {
		super(Holiday.class);
	}
}
