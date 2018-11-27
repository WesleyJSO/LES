package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.HourType;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.HourTypeRepository;

@Component
public class FetchHourType implements IStrategy<Employee> {

	@Autowired private HourTypeRepository hourTypeRepository;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {
		
		if (aEntity != null) {
			if(aEntity.getBaseHourCalculation() != null && aEntity.getBaseHourCalculation().getHourType() != null) {
				Optional<HourType> selectedHourType = hourTypeRepository.findActiveById(aEntity.getBaseHourCalculation().getHourType().getId());
				if(selectedHourType.isPresent()) {
					aEntity.getBaseHourCalculation().setHourType(selectedHourType.get());
					return;
				}
				aCase.suspendExecution();
				aCase.getResult().setError("Tipo de horas não encontrado!");
			}
			aCase.suspendExecution();
			aCase.getResult().setError("Tipo de horas deve ser selecionado!");
	        return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Tipo de horas inexistente!");
		return;
	}
}
