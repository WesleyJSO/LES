package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RoleRepository;

@Configuration
public class WorkLoadLimit implements IStrategy<Employee> {

	private final static Short WORKLOAD_LIMIT = 10;
	@Autowired private RoleRepository roleRepository;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getBaseHourCalculation() != null) {
			
			Optional<Role> r = Optional.empty();
			for ( GrantedAuthority role : aEntity.getUser().getAuthorities() ) {
				r = roleRepository.findActiveById( ( ( DomainEntity ) role ).getId() );
				if(r.get().getAuthority().equals(Role.ROLE_EMPLOYEE))
					break;
				else
					r = Optional.empty();
			}
			if(r.isPresent()) {
				
				aEntity.getBaseHourCalculation().setEmployee(aEntity);
				
				if(aEntity.getBaseHourCalculation().getWorkload() <= 0)
					aCase.getResult().setError("Carga horária diaria deve ser maior que zero!");
				
				if(aEntity.getBaseHourCalculation().getWorkload() > WORKLOAD_LIMIT) 
					aCase.getResult().setError("Carga horária diaria maior que o limite de ".concat(WORKLOAD_LIMIT.toString()));
				
				if(aEntity.getBaseHourCalculation().getOvertimePercentage() == null || aEntity.getBaseHourCalculation().getOvertimePercentage() <= 0) 
					aCase.getResult().setError("Porcentagem de hora extra deve ser cadastrada!");
				
				if(aEntity.getBaseHourCalculation().getNightOvertimePercentage() == null || aEntity.getBaseHourCalculation().getNightOvertimePercentage() <= 0) 
					aCase.getResult().setError("Porcentagem de hora extra noturna deve ser cadastrada!");
				
				if(aEntity.getBaseHourCalculation().getWeekendOvertimePercentage() == null || aEntity.getBaseHourCalculation().getWeekendOvertimePercentage() <= 0) 
					aCase.getResult().setError("Porcentagem de hora extra aos finais de semana deve ser cadastrada!");
				
			}
			return;
		}					
		aCase.suspendExecution();
		aCase.getResult().setError("Carga horária de trabalho não definida!");
		return;
	}
}
