package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.HourType;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class HourTypeNavigation {

	@Bean("FIND_HOURTYPE")
	public Navigation<HourType> findHourTypeValidator() {
		return new NavigationBuilder<HourType>().build();
	}

	@Bean("SAVE_HOURTYPE")
	public Navigation<HourType> saveHourTypeValidator() {
		return new NavigationBuilder<HourType>().build();
	}

	@Bean("UPDATE_HOURTYPE")
	public Navigation<HourType> updateHourTypeValidator() {
		return new NavigationBuilder<HourType>().build();
	}

	@Bean("DELETE_HOURTYPE")
	public Navigation<HourType> deleteHourTypeValidator() {
		return new NavigationBuilder<HourType>().build();
	}

}
