package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.BankedHours;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class BankedHoursNavigation {

	@Bean("FIND_BANKEDHOURS")
	public Navigation<BankedHours> findBankedHoursValidator() {
		return new NavigationBuilder<BankedHours>().build();
	}

	@Bean("SAVE_BANKEDHOURS")
	public Navigation<BankedHours> saveBankedHoursValidator() {
		return new NavigationBuilder<BankedHours>().build();
	}

	@Bean("UPDATE_BANKEDHOURS")
	public Navigation<BankedHours> updateBankedHoursValidator() {
		return new NavigationBuilder<BankedHours>().build();
	}

	@Bean("DELETE_BANKEDHOURS")
	public Navigation<BankedHours> deleteBankedHoursValidator() {
		return new NavigationBuilder<BankedHours>().build();
	}

}
