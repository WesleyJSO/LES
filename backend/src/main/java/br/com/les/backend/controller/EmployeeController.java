package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.Employee;

@Controller
@RequestMapping("${server.controller.prefix}employee")
public class EmployeeController extends AbstractController<Employee> {
	
	public EmployeeController() {
		super(Employee.class);
	}
}