package br.com.les.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.dao.FilterDAO;
import br.com.les.backend.filter.ChartFilter;
import br.com.les.backend.utils.Result;

@Controller
@RequestMapping("${server.controller.prefix}chartfilter")
public class ChartFilterController extends AbstractController<ChartFilter>{
	
	@Autowired private FilterDAO filterDAO;
	
	public ChartFilterController() {
		super(ChartFilter.class);
	}
	
	@Override
	@PatchMapping
	public @ResponseBody Result<ChartFilter> find(@RequestBody ChartFilter entity) throws InstantiationException, IllegalAccessException {
		
		Result<ChartFilter> result = new Result<>();
		result.setResultList(filterDAO.find(entity));
		return result;
	} 
}
