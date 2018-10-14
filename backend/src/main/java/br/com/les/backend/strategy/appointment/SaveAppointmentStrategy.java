package br.com.les.backend.strategy.appointment;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class SaveAppointmentStrategy extends AbstractAppointmentStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {

		result = new Result();
		
		Appointment appointment = ( Appointment ) entity;
		switch ( callerMethod ) {
		case "save":
		}
		result.setSuccess( null );
		return result;
	}

}
