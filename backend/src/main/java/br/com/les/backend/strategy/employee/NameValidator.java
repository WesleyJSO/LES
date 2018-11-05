package br.com.les.backend.strategy.employee;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class NameValidator implements IStrategy<Employee> {
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {
			
		if (aEntity != null) {
			if(!Strings.isNullOrEmpty(aEntity.getName()) && !Strings.isNullOrEmpty(aEntity.getLastName())) {
				aCase.getResult().setError("Nome e sobrenome não foram preenchidos!");
			}
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Colaborador inexistente!");
		return;
	}
}
