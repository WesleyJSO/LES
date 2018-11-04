package br.com.les.backend.strategy.employee;

import java.util.regex.Matcher;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.utils.Regex;

@Configuration
public class EmailFormat implements IStrategy<Employee>{

	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {
		
		if (aEntity != null && aEntity.getUser() != null && !Strings.isNullOrEmpty(aEntity.getUser().getEmail())) {
			
			Matcher matcher = Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(aEntity.getUser().getEmail());
	        if(!matcher.find()) {
	    		aCase.suspendExecution();
	    		aCase.getResult().setError("Formato de e-mail inválido!");
	        }
	        return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("E-mail inexistente!");
		return;
	}
}
