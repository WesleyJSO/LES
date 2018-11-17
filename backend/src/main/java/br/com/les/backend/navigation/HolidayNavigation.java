package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Holiday;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class HolidayNavigation {
	
	@Bean("FIND_HOLIDAY")
	public Navigation<Holiday> findHolidayValidator() {
		return new NavigationBuilder<Holiday>().build();
	}

	@Bean("SAVE_HOLIDAY")
	public Navigation<Holiday> saveHolidayValidator() {
		return new NavigationBuilder<Holiday>()
				.build();
	}

	@Bean("UPDATE_HOLIDAY")
	public Navigation<Holiday> updateHolidayValidator() {
		return new NavigationBuilder<Holiday>()
				.build();
	}

	@Bean("DELETE_HOLIDAY")
	public Navigation<Holiday> deleteHolidayValidator() {
		return new NavigationBuilder<Holiday>().build();
	}

}
