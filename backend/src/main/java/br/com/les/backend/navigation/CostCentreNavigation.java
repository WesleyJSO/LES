package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class CostCentreNavigation {

	@Bean("FIND_COSTCENTRE")
	public Navigation<CostCentre> findCostCentreValidator() {
		return new NavigationBuilder<CostCentre>().build();
	}

	@Bean("SAVE_COSTCENTRE")
	public Navigation<CostCentre> saveCostCentreValidator() {
		return new NavigationBuilder<CostCentre>().build();
	}

	@Bean("UPDATE_COSTCENTRE")
	public Navigation<CostCentre> updateCostCentreValidator() {
		return new NavigationBuilder<CostCentre>().build();
	}

	@Bean("DELETE_COSTCENTRE")
	public Navigation<CostCentre> deleteCostCentreValidator() {
		return new NavigationBuilder<CostCentre>().build();
	}

}
