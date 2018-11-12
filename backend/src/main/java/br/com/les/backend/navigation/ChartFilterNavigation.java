package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.filter.ChartFilter;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class ChartFilterNavigation {

	@Bean("GET_CHARTFILTER")
	public Navigation<ChartFilter> getChartFilterValidator() {
		return new NavigationBuilder<ChartFilter>().build();
	}
	
	@Bean("FIND_CHARTFILTER")
	public Navigation<ChartFilter> findChartFilterValidator() {
		return new NavigationBuilder<ChartFilter>().build();
	}

	@Bean("SAVE_CHARTFILTER")
	public Navigation<ChartFilter> saveChartFilterValidator() {
		return new NavigationBuilder<ChartFilter>().build();
	}

	@Bean("UPDATE_CHARTFILTER")
	public Navigation<ChartFilter> updateChartFilterValidator() {
		return new NavigationBuilder<ChartFilter>().build();
	}

	@Bean("DELETE_CHARTFILTER")
	public Navigation<ChartFilter> deleteChartFilterValidator() {
		return new NavigationBuilder<ChartFilter>().build();
	}
}
