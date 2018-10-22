package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Appointment;

@Controller
@RequestMapping("${server.controller.prefix}appointment")
public class AppointmentController extends AbstractController<Appointment> {
	
	public AppointmentController() {
		super(Appointment.class);
	}
}