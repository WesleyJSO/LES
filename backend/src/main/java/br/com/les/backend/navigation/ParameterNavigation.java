package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.parameter.BothTypesValues;
import br.com.les.backend.strategy.parameter.CompTimeValues;
import br.com.les.backend.strategy.parameter.InferiorLimits;
import br.com.les.backend.strategy.parameter.OvertimeValues;
import br.com.les.backend.strategy.parameter.SaveHourTypes;
import br.com.les.backend.strategy.parameter.SetIdNull;
import br.com.les.backend.strategy.parameter.VerifyDate;

@Configuration
public class ParameterNavigation {
	
	@Autowired InferiorLimits inferiorLimits;
	@Autowired OvertimeValues overtimeValues;
	@Autowired CompTimeValues compTimeValues;
	@Autowired BothTypesValues bothTypesValues;
	@Autowired SaveHourTypes saveHourTypes;
	@Autowired SetIdNull setIdNull;
	@Autowired VerifyDate verifyDate;

	@Bean("FIND_PARAMETER")
	public Navigation<Parameter> findParameterValidator() {
		return new NavigationBuilder<Parameter>().build();
	}

	@Bean("SAVE_PARAMETER")
	public Navigation<Parameter> saveParameterValidator() {
		return new NavigationBuilder<Parameter>()
				.next(inferiorLimits)
				.next(overtimeValues)
				.next(compTimeValues)
				.next(bothTypesValues)
				.next(saveHourTypes)
				.next(setIdNull)
				.build();
	}

	@Bean("UPDATE_PARAMETER")
	public Navigation<Parameter> updateParameterValidator() {
		return new NavigationBuilder<Parameter>()
				.next(verifyDate)
				.next(inferiorLimits)
				.next(overtimeValues)
				.next(compTimeValues)
				.next(bothTypesValues)
				.next(saveHourTypes)
				.build();
	}

	@Bean("DELETE_PARAMETER")
	public Navigation<Parameter> deleteParameterValidator() {
		return new NavigationBuilder<Parameter>().build();
	}

}
