package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Strings;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RoleRepository;

@Configuration
public class SalaryValidator implements IStrategy<Employee> {
	
	@Autowired private RoleRepository roleRepository;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getBaseHourCalculation() != null 
				&& !Strings.isNullOrEmpty(String.valueOf(aEntity.getBaseHourCalculation().getSalary()))) {
		
			Optional<Role> r = Optional.empty();
			for (GrantedAuthority role : aEntity.getUser().getAuthorities()) {
				r = roleRepository.findActiveById(((DomainEntity) role).getId());
				if(r.get().getAuthority().equals(Role.ROLE_EMPLOYEE))
					break;
				else
					r = Optional.empty();
			}
			if(r.isPresent()) {
				
				if(aEntity.getBaseHourCalculation().getSalary() <= 0) {
					aCase.getResult().setError("Salário informado deve ser maior que R$ "
							.concat(aEntity.getBaseHourCalculation().getSalary().toString()).concat("!"));
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Salário não definido!");
		return;
	}
}
