package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.filter.ChartFilter;
import br.com.les.backend.navigator.BusinessCase;
import br.com.les.backend.navigator.BusinessCaseBuilder;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@Controller
@RequestMapping("${server.controller.prefix}chartfilter")
public class ChartFilterController extends AbstractController<ChartFilter>{
	
	public ChartFilterController() {
		super(ChartFilter.class);
	}
	
	@Override
	@PatchMapping
	public @ResponseBody Result<ChartFilter> find(@RequestBody ChartFilter entity) throws InstantiationException, IllegalAccessException {
		
		BusinessCase<ChartFilter> bCase = new BusinessCaseBuilder<ChartFilter>()
				.withName(existingNavigation("GET_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
		
		return run(Actions.GET.getValue()).execute(entity, bCase);
	} 
}
