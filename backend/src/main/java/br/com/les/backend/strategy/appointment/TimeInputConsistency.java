package br.com.les.backend.strategy.appointment;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class TimeInputConsistency implements IStrategy<Appointment> {

	@Override
	public void process(Appointment aEntity, INavigationCase<Appointment> aCase) {

		if (aEntity != null) {
			
			List< LocalTime > inputList = new ArrayList<>();
			
			inputList.add(aEntity.getMorningEntrance());
			inputList.add(aEntity.getMorningOut());
			inputList.add(aEntity.getAfternoonEntrance());
			inputList.add(aEntity.getAfternoonOut());
			inputList.add(aEntity.getNightEntrance());
			inputList.add(aEntity.getNightOut());
			
			for (int i = 0; i < inputList.size() - 1; i++) {
				
				int next = i + 1;
				
				while ( inputList.size() > next + 1 && null == inputList.get(next) ) {
					next++;
				}
				
				if ( !verifyFields(inputList.get(i), inputList.get(next)) ) {
					aCase.suspendExecution();
					aCase.getResult().setError("Os horários inseridos devem estar ordenados!");
					return;
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento nulo!");
		return;
	}
	
	public Boolean verifyFields(LocalTime first, LocalTime second) {
		if ( null != first && null != second ) {
			
			if (first.isAfter(second) ) {
				
				return false;
			}
		}
		return true;
	}
}
