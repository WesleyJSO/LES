package br.com.les.backend.validator.employee;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class WorkLoadLimit implements IStrategy<Employee> {

	private final static Short WORKLOAD_LIMIT = 10;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getBaseHourCalculation() != null
				&& !Strings.isNullOrEmpty(aEntity.getBaseHourCalculation().getWorkload().toString())
				&& aEntity.getBaseHourCalculation().getWorkload() > 0) {
		
			if(aEntity.getBaseHourCalculation().getWorkload() > WORKLOAD_LIMIT) {
				aCase.suspendExecution();
				aCase.getResult().setError("Carga horária diaria maior que o limite de ".concat(WORKLOAD_LIMIT.toString()));
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Carga horária de trabalho não definida!");
		return;
	}
}
