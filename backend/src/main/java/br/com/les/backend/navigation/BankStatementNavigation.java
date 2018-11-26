package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.BankStatement;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.bankstatement.GenerateStatement;

@Configuration
public class BankStatementNavigation {
	
	@Autowired private GenerateStatement generateStatement;

	@Bean("FIND_BANKSTATEMENT")
	public Navigation<BankStatement> findRoleValidator() {
		return new NavigationBuilder<BankStatement>()
				.next(generateStatement)
				.build();
	}

	@Bean("SAVE_BANKSTATEMENT")
	public Navigation<BankStatement> saveRoleValidator() {
		return new NavigationBuilder<BankStatement>().build();
	}

	@Bean("UPDATE_BANKSTATEMENT")
	public Navigation<BankStatement> updateRoleValidator() {
		return new NavigationBuilder<BankStatement>().build();
	}

	@Bean("DELETE_BANKSTATEMENT")
	public Navigation<BankStatement> deleteRoleValidator() {
		return new NavigationBuilder<BankStatement>().build();
	}

}