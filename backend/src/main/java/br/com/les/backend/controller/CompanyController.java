package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Company;

@Controller
@RequestMapping("${server.controller.prefix}company")
public class CompanyController extends AbstractController<Company> {
	
	public CompanyController() {
		super(Company.class);
	}
}