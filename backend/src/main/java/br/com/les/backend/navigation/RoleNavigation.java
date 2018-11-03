package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class RoleNavigation {

	@Bean("FIND_ROLE")
	public Navigation<Role> findRoleValidator() {
		return new NavigationBuilder<Role>().build();
	}

	@Bean("SAVE_ROLE")
	public Navigation<Role> saveRoleValidator() {
		return new NavigationBuilder<Role>().build();
	}

	@Bean("UPDATE_ROLE")
	public Navigation<Role> updateRoleValidator() {
		return new NavigationBuilder<Role>().build();
	}

	@Bean("DELETE_ROLE")
	public Navigation<Role> deleteRoleValidator() {
		return new NavigationBuilder<Role>().build();
	}

}
