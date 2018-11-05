package br.com.les.backend.strategy.employee;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class SalaryValidator implements IStrategy<Employee> {
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getBaseHourCalculation() != null 
				&& !Strings.isNullOrEmpty(aEntity.getBaseHourCalculation().getSalary().toString())) {
		
			if(aEntity.getBaseHourCalculation().getSalary() <= 0) {
				aCase.getResult().setError("Salário informado deve ser maior que R$ "
						.concat(aEntity.getBaseHourCalculation().getSalary().toString()).concat("!"));
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Salário não definido!");
		return;
	}
}
