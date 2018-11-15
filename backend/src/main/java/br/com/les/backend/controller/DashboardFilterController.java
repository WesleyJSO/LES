package br.com.les.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.dao.DashboardFilterDAO;
import br.com.les.backend.filter.DashboardFilter;
import br.com.les.backend.navigator.BusinessCase;
import br.com.les.backend.navigator.BusinessCaseBuilder;
import br.com.les.backend.navigator.INavigator;
import br.com.les.backend.utils.Result;

@Controller
@RequestMapping("${server.controller.prefix}dashboard")
public class DashboardFilterController extends AbstractController<DashboardFilter> {

	@Autowired DashboardFilterDAO dashboardDAO;
	@Autowired INavigator<DashboardFilter> navigator;
	
	public DashboardFilterController() {
		super(DashboardFilter.class);
	}
	
	@Override
	@PatchMapping
	public @ResponseBody Result<DashboardFilter> find(@RequestBody DashboardFilter entity) throws InstantiationException, IllegalAccessException {
		
		// List<DashboardFilter> pureList = dashboardDAO.findDashboardData();
		
		BusinessCase<DashboardFilter> bCase = new BusinessCaseBuilder<DashboardFilter>()
				.withName(existingNavigation("FIND_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
		
		return null;
	}
}
