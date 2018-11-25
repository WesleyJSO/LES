package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.User;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.EmployeeRepository;
import br.com.les.backend.repository.UserRepository;

@Configuration
public class EmailExistence implements IStrategy<Employee> {
	
	@Autowired private UserRepository userRepository;
	@Autowired private EmployeeRepository employeeRepository;

	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getUser() != null) {
		
			if(!Strings.isNullOrEmpty(aEntity.getUser().getEmail())) {
				
				User user = userRepository.findByEmail(aEntity.getUser().getEmail());
				if(user != null) {
					// Need to verify if is the user is trying to change his email
					Optional<Employee> emp = null;
					if ( null != aEntity.getId() )
						 emp = employeeRepository.findActiveById(aEntity.getId());
					if ( null == emp && null != user )
						aCase.getResult().setError("E-mail já cadastrado!");
					else if ( !user.getId().equals( emp.get().getUser().getId() ) )
						aCase.getResult().setError("E-mail já cadastrado!");
				}
				return;
			}
			aCase.getResult().setError("E-mail deve ser informado!");			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Funcionário inexistente!");
		return;
	}
}