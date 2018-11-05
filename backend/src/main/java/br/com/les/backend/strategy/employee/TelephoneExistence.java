package br.com.les.backend.strategy.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Telephone;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class TelephoneExistence implements IStrategy<Employee> {

	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getTelephoneList() != null) {
		
			aEntity.getTelephoneList().forEach(e -> e.setEmployee(aEntity));
			
			List<Telephone> telephoneList = aEntity.getTelephoneList().stream()
					.filter(t -> t.getNumber().length() == 8 || t.getNumber().length() == 9)
					.collect(Collectors.toList());
			
			aEntity.getTelephoneList().clear();
			if(telephoneList.size() == 0)
				aCase.getResult().setError("Nenhum telefone válido foi informado!");
			else
				aEntity.setTelephoneList(telephoneList);
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Nenhum telefone foi informado!");
		return;
	}

}
