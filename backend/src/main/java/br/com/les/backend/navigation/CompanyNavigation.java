package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Company;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.validator.ValidationForDelete;
import br.com.les.backend.validator.company.CNPJAlreadyRegistred;
import br.com.les.backend.validator.company.SocialRegistrationAlreadyRegistred;
import br.com.les.backend.validator.company.ValidateCNPJLength;

@Configuration
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CompanyNavigation {
	
	@Autowired private CNPJAlreadyRegistred cnpjAlreadyRegistred;
	@Autowired private ValidateCNPJLength validateCNPJLength;
	@Autowired private SocialRegistrationAlreadyRegistred socialRegistrationAlreadyRegistred;

	@Autowired private ValidationForDelete validationForDelete;
	
	@Bean("FIND_COMPANY")
	public Navigation<Company> findCompanyValidator() {
		return new NavigationBuilder<Company>().build();
	}

	@Bean("SAVE_COMPANY")
	public Navigation<Company> saveCompanyValidator() {
		return new NavigationBuilder<Company>()
				.next(validateCNPJLength)
				.next(cnpjAlreadyRegistred)
				.next(socialRegistrationAlreadyRegistred)
				.build();
	}

	@Bean("UPDATE_COMPANY")
	public Navigation<Company> updateCompanyValidator() {
		return new NavigationBuilder<Company>()
				.next(validateCNPJLength)
				.next(cnpjAlreadyRegistred)
				.next(socialRegistrationAlreadyRegistred)
				.build();
	}

	@Bean("DELETE_COMPANY")
	public Navigation<Company> deleteCompanyValidator() {
		return new NavigationBuilder<Company>()
				.next(validationForDelete)
				.build();
	}

}
