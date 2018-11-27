package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.EmployeeRepository;

@Component
public class FetchManager implements IStrategy<Employee>{

	@Autowired private EmployeeRepository employeeRepository;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {
		
		if (aEntity != null) {
			if(aEntity.getManager() != null) {
				Optional<Employee> manager = employeeRepository.findActiveById(aEntity.getManager().getId());
				if(manager.isPresent()) {
					aEntity.setManager(manager.get());
					return;
				}
				aCase.suspendExecution();
				aCase.getResult().setError("Gestor não encontrado!");
			}
			aCase.suspendExecution();
			aCase.getResult().setError("Gestor deve ser selecionado!");
	        return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Colaborador inexistente!");
		return;
	}
}
