package br.com.les.backend.validator.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.User;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.UserRepository;

@Configuration
public class EmailExistence implements IStrategy<Employee> {
	
	@Autowired private UserRepository userRepository;

	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getUser() != null && !Strings.isNullOrEmpty(aEntity.getUser().getEmail())) {
		
			User user = userRepository.findByEmail(aEntity.getUser().getEmail());    	
			if( user != null) {
				aCase.suspendExecution();
				aCase.getResult().setError("Email já cadastrado!");
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("E-mail inexistente!");
		return;
	}
}