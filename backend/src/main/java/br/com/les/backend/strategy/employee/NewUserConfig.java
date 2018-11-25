package br.com.les.backend.strategy.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.User;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.Util;

@Component
public class NewUserConfig implements IStrategy<Employee> {

	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if(aEntity != null) {
			
			if(aEntity.getUser() != null) {
				aCase.getResult().setSuccess(Util.SAVE_SUCCESSFUL_EMPLOYEE);
				User u = aEntity.getUser();
				u.setPassword( SecurityService.encodeUserPassword( aEntity.getUser().getPassword() ) );
				u.setEnabled(true);
				u.setAccountNonLocked(true);
				u.setAccountNonExpired(true);
				u.setCredentialsNonExpired(true);
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Funcionário inexistente!");
		return;
	}
}
