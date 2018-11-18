package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.les.backend.entity.AppointmentRequest;

@Controller
@RequestMapping("${server.controller.prefix}appointmentRequest")
public class AppointmentRequestController extends AbstractController<AppointmentRequest> {
	
	public AppointmentRequestController() {
		super(AppointmentRequest.class);
	}
}
