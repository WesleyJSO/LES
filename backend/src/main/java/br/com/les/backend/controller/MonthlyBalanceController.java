package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.entity.MonthlyBalance;

@Controller
@RequestMapping("${server.controller.prefix}monthlybalance")
public class MonthlyBalanceController extends AbstractController<MonthlyBalance> {
	
	public MonthlyBalanceController() {
		super(MonthlyBalance.class);
	}
}
