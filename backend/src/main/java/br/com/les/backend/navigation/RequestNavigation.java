package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Request;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class RequestNavigation {

	@Bean("FIND_REQUEST")
	public Navigation<Request> findRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

	@Bean("SAVE_REQUEST")
	public Navigation<Request> saveRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

	@Bean("UPDATE_REQUEST")
	public Navigation<Request> updateRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

	@Bean("DELETE_REQUEST")
	public Navigation<Request> deleteRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

}
