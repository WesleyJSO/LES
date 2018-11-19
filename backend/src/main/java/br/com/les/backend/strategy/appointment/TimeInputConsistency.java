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
			
			// order the inputs in a list
			inputList.add(aEntity.getMorningEntrance());
			inputList.add(aEntity.getMorningOut());
			inputList.add(aEntity.getAfternoonEntrance());
			inputList.add(aEntity.getAfternoonOut());
			inputList.add(aEntity.getNightEntrance());
			// verify if the night out is after midnight
			if ( null != aEntity.getNightOut() && aEntity.getNightOut().isBefore(LocalTime.of(6, 0))) {
				inputList.add(LocalTime.MIDNIGHT.minusMinutes(1));
			} else {
				inputList.add(aEntity.getNightOut());
			}
			
			for (int i = 0; i < inputList.size() - 1; i++) {
				
				int next = i + 1;
				
				// get the index of the next input with value or the last
				while ( inputList.size() > next + 1 && null == inputList.get(next) ) {
					next++;
				}
				
				if ( !verifyFields(inputList.get(i), inputList.get(next)) ) {
					aCase.suspendExecution();
					aCase.getResult().setError("Os horários inseridos devem estar ordenados!");
					return;
				}
			}
			// verify if the night out is after midnight
			if ( null != aEntity.getParticularExitReturn() && aEntity.getParticularExitReturn().isBefore(LocalTime.of(6, 0))
				&& aEntity.getParticularExitReturn().isAfter(LocalTime.MIDNIGHT)) {
				if ( !verifyFields( aEntity.getParticularExit(), LocalTime.MIDNIGHT.minusMinutes(1) ) ) {
					aCase.suspendExecution();
					aCase.getResult().setError("Os horários inseridos devem estar ordenados!");
				}
			} else {
				if ( !verifyFields( aEntity.getParticularExit(), aEntity.getParticularExitReturn() ) ) {
					aCase.suspendExecution();
					aCase.getResult().setError("Os horários inseridos devem estar ordenados!");
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento nulo!");
		return;
	}
	
	public Boolean verifyFields(LocalTime first, LocalTime second) {
		// if one of them are null, there's nothing to validate
		if ( null != first && null != second ) {
			
			if (first.isAfter(second) ) {
				
				return false;
			}
		}
		return true;
	}
}
