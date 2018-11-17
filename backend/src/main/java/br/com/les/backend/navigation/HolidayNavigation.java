package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Holiday;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.holiday.UpdateName;

@Configuration
public class HolidayNavigation {
	
	@Autowired UpdateName updateName;
	
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
