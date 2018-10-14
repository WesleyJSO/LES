package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.CostCentre;

@Controller
@RequestMapping("${server.controller.prefix}costcentre")
public class CostCentreController extends AbstractController<CostCentre> {
	
	public CostCentreController() {
		super(CostCentre.class);
	}
}