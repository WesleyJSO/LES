package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.TimeTracking;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.timetracking.GenerateReport;

@Configuration
public class TimeTrackingNavigation {
	
	@Autowired GenerateReport generateReport;

	@Bean("FIND_TIMETRACKING")
	public Navigation<TimeTracking> findRoleValidator() {
		return new NavigationBuilder<TimeTracking>()
				.next(generateReport)
				.build();
	}

	@Bean("SAVE_TIMETRACKING")
	public Navigation<TimeTracking> saveRoleValidator() {
		return new NavigationBuilder<TimeTracking>().build();
	}

	@Bean("UPDATE_TIMETRACKING")
	public Navigation<TimeTracking> updateRoleValidator() {
		return new NavigationBuilder<TimeTracking>().build();
	}

	@Bean("DELETE_TIMETRACKING")
	public Navigation<TimeTracking> deleteRoleValidator() {
		return new NavigationBuilder<TimeTracking>().build();
	}

}
