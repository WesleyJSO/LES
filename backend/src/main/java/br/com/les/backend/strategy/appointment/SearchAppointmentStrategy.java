package br.com.les.backend.strategy.appointment;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class SearchAppointmentStrategy extends AbstractAppointmentStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		
		result = new Result();
		
		Appointment appointment = ( Appointment ) entity;
		
		if ( appointment != null ) {
			
			switch ( callerMethod ) {
			
				case "FindByDay":
					
					break;
					
				default:
					break;
			}
		}
		return result;
	}

}
