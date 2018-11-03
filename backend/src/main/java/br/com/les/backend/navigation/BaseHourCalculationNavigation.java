package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.BaseHourCalculation;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class BaseHourCalculationNavigation {

	@Bean("FIND_BASEHOURCALCULATION")
	public Navigation<BaseHourCalculation> findBaseHourCalculationValidator() {
		return new NavigationBuilder<BaseHourCalculation>().build();
	}

	@Bean("SAVE_BASEHOURCALCULATION")
	public Navigation<BaseHourCalculation> saveBaseHourCalculationValidator() {
		return new NavigationBuilder<BaseHourCalculation>().build();
	}

	@Bean("UPDATE_BASEHOURCALCULATION")
	public Navigation<BaseHourCalculation> updateBaseHourCalculationValidator() {
		return new NavigationBuilder<BaseHourCalculation>().build();
	}

	@Bean("DELETE_BASEHOURCALCULATION")
	public Navigation<BaseHourCalculation> deleteBaseHourCalculationValidator() {
		return new NavigationBuilder<BaseHourCalculation>().build();
	}

}
