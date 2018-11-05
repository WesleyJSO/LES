package br.com.les.backend.strategy.employee;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class PisLength implements IStrategy<Employee> {

	private final static Short PIS_LENGTH = 11;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getPis().toString())) {
		
			if(aEntity.getPis().length() > PIS_LENGTH) {
				aCase.getResult().setError("Numero do PIS maior que o limite de ".concat(PIS_LENGTH.toString()).concat(" caracteres!"));
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Número do PIS não definido!");
		return;
	}
}
