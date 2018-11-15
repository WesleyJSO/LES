package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.filter.DashboardFilter;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class DashboardFilterNavigation {

	@Bean("FIND_DASHBOARDFILTER")
	public Navigation<DashboardFilter> findCostCentreValidator() {
		return new NavigationBuilder<DashboardFilter>().build();
	}
}
