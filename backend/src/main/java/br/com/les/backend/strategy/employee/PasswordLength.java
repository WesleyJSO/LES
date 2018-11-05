package br.com.les.backend.strategy.employee;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class PasswordLength implements IStrategy<Employee> {

	private final static Short PASSWORD_LENGTH = 3;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getUser() != null) {
		
			if(Strings.isNullOrEmpty(aEntity.getUser().getPassword()))
				aCase.getResult().setError("Preencha a senha!");
			
			if(aEntity.getUser().getPassword().length() <= PASSWORD_LENGTH) 
				aCase.getResult().setError("Senha deve conter mais de ".concat(PASSWORD_LENGTH.toString()).concat(" caracteres!"));
			
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Nenhuma senha informada!");
		return;
	}
}
