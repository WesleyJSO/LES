package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Company;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class CompanyNavigation {

	@Bean("FIND_COMPANY")
	public Navigation<Company> findCompanyValidator() {
		return new NavigationBuilder<Company>().build();
	}

	@Bean("SAVE_COMPANY")
	public Navigation<Company> saveCompanyValidator() {
		return new NavigationBuilder<Company>().build();
	}

	@Bean("UPDATE_COMPANY")
	public Navigation<Company> updateCompanyValidator() {
		return new NavigationBuilder<Company>().build();
	}

	@Bean("DELETE_COMPANY")
	public Navigation<Company> deleteCompanyValidator() {
		return new NavigationBuilder<Company>().build();
	}

}
