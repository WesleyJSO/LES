package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Role;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.RoleRepository;

@Configuration
public class PisLength implements IStrategy<Employee> {

	private final static Short PIS_LENGTH = 11;
	@Autowired private RoleRepository roleRepository;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getPis())) {
		
			Optional<Role> r = Optional.empty();
			for (Role role : aEntity.getUser().getRoleList()) {
				r = roleRepository.findActiveById(role.getId());
				if(r.get().equals(Role.ROLE_EMPLOYEE))
					break;
				else
					r = Optional.empty();
			}
			if(r.isPresent()) {
				
				if(aEntity.getPis().length() > PIS_LENGTH) {
					aCase.getResult().setError("Numero do PIS maior que o limite de ".concat(PIS_LENGTH.toString()).concat(" caracteres!"));
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Número do PIS não definido!");
		return;
	}
}
