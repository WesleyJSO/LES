package br.com.les.backend.strategy.employee;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

//valpefuga@gmail.com meu nome completo e nome do curso
@Configuration
public class WorkLoadLimit implements IStrategy<Employee> {

	private final static Short WORKLOAD_LIMIT = 10;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getBaseHourCalculation() != null
				&& !Strings.isNullOrEmpty(String.valueOf(aEntity.getBaseHourCalculation().getWorkload()))) {
			
			for (GrantedAuthority auth : aEntity.getUser().getAuthorities()) {
				// if("ROLE_COLABORADOR".equals((Role)auth).getRoleName());	
			}
			if(aEntity.getBaseHourCalculation().getWorkload() > 0)
			if(aEntity.getBaseHourCalculation().getWorkload() > WORKLOAD_LIMIT) {
				aCase.getResult().setError("Carga horária diaria maior que o limite de ".concat(WORKLOAD_LIMIT.toString()));
			}
			return;
		} 
		aCase.suspendExecution();
		aCase.getResult().setError("Carga horária de trabalho não definida!");
		return;
	}
}
