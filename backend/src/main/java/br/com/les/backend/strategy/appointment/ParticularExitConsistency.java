package br.com.les.backend.strategy.appointment;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class ParticularExitConsistency implements IStrategy<Appointment> {

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
			
			for (int i = 0; i < inputList.size() - 1; i = i + 2) {
				
				int next = i + 1;
				
				while ( inputList.size() > next + 1 && null == inputList.get(next) ) {
					next++;
				}
				
				if ( null != aEntity.getParticularExit()
					&& null != aEntity.getParticularExitReturn() ) {
					
					if ( validate(inputList.get(i), aEntity.getParticularExit(), inputList.get(next) ) 
						&& validate(inputList.get(i), aEntity.getParticularExitReturn(), inputList.get(next) ) ) {
						if ( validateRange(aEntity.getMorningOut(), aEntity.getAfternoonEntrance(), 
								aEntity.getParticularExit(), aEntity.getParticularExitReturn() )
							&& validateRange(aEntity.getAfternoonOut(), aEntity.getNightEntrance(), 
									aEntity.getParticularExit(), aEntity.getParticularExitReturn() ) ) {
							return;							
						}
					}
					
				} else if ( null != aEntity.getParticularExit() ) {
					
					if ( validate(inputList.get(i), aEntity.getParticularExit(), inputList.get(next) ) ) {
						return;
					}
					
				} else if ( null != aEntity.getParticularExitReturn() ) {

					if ( validate(inputList.get(i), aEntity.getParticularExitReturn(), inputList.get(next) ) ) {
						return;
					}
					
				} else {
					return;
				}
			}

			aCase.suspendExecution();
			aCase.getResult().setError("Saída particular fora do horário trabalhado!");
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento nulo!");
		return;
	}
	
	private Boolean validate(LocalTime entrance, LocalTime time, LocalTime out) {
		if ( null != entrance && null != out ) {
			if ( time.isAfter(entrance) && time.isBefore(out) ) {
				return true;
			}
		} else if ( null != entrance && time.isAfter(entrance) ) {
			 return true;
		}
		return false;
	}
	
	private Boolean validateRange(LocalTime entrance, LocalTime out, LocalTime particularExit, LocalTime particularExitReturn) {
		// if one of the values is null, there's nothing to validate
		if ( null != entrance && null != out ) {
			// if there's time between an exit and the next entrance
			if (0 != entrance.compareTo(out) ) {
				// verify if one of the particular appointments is in between the exit and the entrance
				if (particularExit.isBefore(out) && particularExitReturn.isAfter(entrance) ) {
					return false;
				}
			}
		}
		return true;
		
	}
}
