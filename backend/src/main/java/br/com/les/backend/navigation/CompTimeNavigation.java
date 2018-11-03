package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.CompTime;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class CompTimeNavigation {
	@Bean("FIND_COMPTIME")
	public Navigation<CompTime> findCompTimeValidator() {
		return new NavigationBuilder<CompTime>().build();
	}

	@Bean("SAVE_COMPTIME")
	public Navigation<CompTime> saveCompTimeValidator() {
		return new NavigationBuilder<CompTime>().build();
	}

	@Bean("UPDATE_COMPTIME")
	public Navigation<CompTime> updateCompTimeValidator() {
		return new NavigationBuilder<CompTime>().build();
	}

	@Bean("DELETE_COMPTIME")
	public Navigation<CompTime> deleteCompTimeValidator() {
		return new NavigationBuilder<CompTime>().build();
	}

}
