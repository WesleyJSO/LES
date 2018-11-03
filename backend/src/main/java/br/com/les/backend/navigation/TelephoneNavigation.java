package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Telephone;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class TelephoneNavigation {

	@Bean("FIND_TELEPHONE")
	public Navigation<Telephone> findTelephoneValidator() {
		return new NavigationBuilder<Telephone>().build();
	}

	@Bean("SAVE_TELEPHONE")
	public Navigation<Telephone> saveTelephoneValidator() {
		return new NavigationBuilder<Telephone>().build();
	}

	@Bean("UPDATE_TELEPHONE")
	public Navigation<Telephone> updateTelephoneValidator() {
		return new NavigationBuilder<Telephone>().build();
	}

	@Bean("DELETE_TELEPHONE")
	public Navigation<Telephone> deleteTelephoneValidator() {
		return new NavigationBuilder<Telephone>().build();
	}

}
