package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.filter.DashboardFilter;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.dashboard.CreateDashboardData;

@Configuration
public class DashboardFilterNavigation {

	@Autowired private CreateDashboardData createDashboardData;
	
	@Bean("FIND_DASHBOARDFILTER")
	public Navigation<DashboardFilter> findCostCentreValidator() {
		return new NavigationBuilder<DashboardFilter>()
				.next(createDashboardData)
				.build();
	}
}
