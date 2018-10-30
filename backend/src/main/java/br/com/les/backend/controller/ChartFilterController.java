package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.les.backend.filter.ChartFilter;

@Controller
@RequestMapping("${server.controller.prefix}chartfilter")
public class ChartFilterController extends AbstractController<ChartFilter>{
	public ChartFilterController() {
		super(ChartFilter.class);
	}
}
