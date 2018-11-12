package br.com.les.backend.strategy.request;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.utils.Util;

@Configuration
public class FindRequest implements IStrategy < Request > {
	
	@Override
	public void process(Request aEntity, INavigationCase<Request> aCase) {
		if (aEntity != null ) {
//			Employee emp = (Employee) SecurityService.getAuthenticatedUser();
//			@SuppressWarnings("unchecked")
//			List <Role> roles = ( List< Role > ) emp.getUser().getAuthorities();
//			
//			if ( null != roles.stream().filter(r -> Role.ROLE_MANAGER.equals(r.getRole() ) ).findAny() ) {
//				Employee userRequest = new Employee ();
//				Employee manager = new Employee(emp.getId());
//				userRequest.setManager(manager);
//				aEntity.setEmployee(userRequest);
//			}
//			
//			if ( null != roles.stream().filter(r -> Role.ROLE_EMPLOYEE.equals(r.getRole() ) ).findAny() ) {
//				Employee userRequest = new Employee(emp.getId());
//				aEntity.setEmployee(userRequest);
//			}
//			
//			for (Role r : roles) {
//				if (Role.ROLE_EMPLOYEE.equals(r.getRole())) {
//					
//				} else if ( Role.ROLE_ADMIN.equals(r.getRole() ) || Role.ROLE_MANAGER.equals(r.getRole() ) ) {
//					
//				}
//				
////				if (aCase.getResult().isSuccess())
////					aCase.getResult().setSuccess(Util.FIND_SUCCESSFUL_REQUEST);
//			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Requisição inexistente!");
		return;
	}
	
}
