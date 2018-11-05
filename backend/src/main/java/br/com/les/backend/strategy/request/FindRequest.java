package br.com.les.backend.strategy.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.service.SecurityService;

@Configuration
public class FindRequest implements IStrategy < Request > {
	
	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null ) {
			Employee emp = (Employee) SecurityService.getAuthenticatedUser();
			List <Role> roles = ( List< Role > ) emp.getUser().getAuthorities();
			for (Role r : roles) {
				if (Role.ROLE_EMPLOYEE.equals(r.getRole())) {
					aEntity.setEmployee(emp);
				} else if ( Role.ROLE_ADMIN.equals(r.getRole() ) || Role.ROLE_MANAGER.equals(r.getRole() ) ) {
					Employee userRequest = new Employee ();
					userRequest.setManager(emp);
					aEntity.setEmployee(userRequest);
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}
	
}
