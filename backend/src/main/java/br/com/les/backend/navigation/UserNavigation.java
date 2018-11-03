package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.User;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class UserNavigation {

	@Bean("FIND_USER")
	public Navigation<User> findUserValidator() {
		return new NavigationBuilder<User>().build();
	}

	@Bean("SAVE_USER")
	public Navigation<User> saveUserValidator() {
		return new NavigationBuilder<User>().build();
	}

	@Bean("UPDATE_USER")
	public Navigation<User> updateUserValidator() {
		return new NavigationBuilder<User>().build();
	}

	@Bean("DELETE_USER")
	public Navigation<User> deleteUserValidator() {
		return new NavigationBuilder<User>().build();
	}

}
