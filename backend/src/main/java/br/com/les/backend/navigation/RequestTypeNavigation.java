package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.RequestType;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class RequestTypeNavigation {

	@Bean("FIND_REQUESTTYPE")
	public Navigation<RequestType> findRequestTypeValidator() {
		return new NavigationBuilder<RequestType>().build();
	}

	@Bean("SAVE_REQUESTTYPE")
	public Navigation<RequestType> saveRequestTypeValidator() {
		return new NavigationBuilder<RequestType>().build();
	}

	@Bean("UPDATE_REQUESTTYPE")
	public Navigation<RequestType> updateRequestTypeValidator() {
		return new NavigationBuilder<RequestType>().build();
	}

	@Bean("DELETE_REQUESTTYPE")
	public Navigation<RequestType> deleteRequestTypeValidator() {
		return new NavigationBuilder<RequestType>().build();
	}

}
