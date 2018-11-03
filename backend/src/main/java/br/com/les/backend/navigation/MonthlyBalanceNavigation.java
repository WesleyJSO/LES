package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class MonthlyBalanceNavigation {

		@Bean("FIND_MONTHLYBALANCE")
		public Navigation<MonthlyBalance> findMonthlyBalanceValidator() {
			return new NavigationBuilder<MonthlyBalance>().build();
		}

		@Bean("SAVE_MONTHLYBALANCE")
		public Navigation<MonthlyBalance> saveMonthlyBalanceValidator() {
			return new NavigationBuilder<MonthlyBalance>().build();
		}

		@Bean("UPDATE_MONTHLYBALANCE")
		public Navigation<MonthlyBalance> updateMonthlyBalanceValidator() {
			return new NavigationBuilder<MonthlyBalance>().build();
		}

		@Bean("DELETE_MONTHLYBALANCE")
		public Navigation<MonthlyBalance> deleteMonthlyBalanceValidator() {
		return new NavigationBuilder<MonthlyBalance>().build();
		}

	}
