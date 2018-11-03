package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class ParameterNavigation {

	@Bean("FIND_PARAMETER")
	public Navigation<Parameter> findParameterValidator() {
		return new NavigationBuilder<Parameter>().build();
	}

	@Bean("SAVE_PARAMETER")
	public Navigation<Parameter> saveParameterValidator() {
		return new NavigationBuilder<Parameter>().build();
	}

	@Bean("UPDATE_PARAMETER")
	public Navigation<Parameter> updateParameterValidator() {
		return new NavigationBuilder<Parameter>().build();
	}

	@Bean("DELETE_PARAMETER")
	public Navigation<Parameter> deleteParameterValidator() {
		return new NavigationBuilder<Parameter>().build();
	}

}
