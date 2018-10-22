package br.com.les.backend.strategy.appointment;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveAppointmentStrategy implements IApplicationStrategy<Appointment> {

	@Override
	public Result<Appointment> execute(Appointment a) {

		Result<Appointment> result = new Result<>();
		
		LocalTime balance = LocalTime.MIN;
		LocalTime left = LocalTime.of(8, 0);
		if ( a.getMorningOut() != null ) {
			balance = balance.plusNanos( (a.getMorningOut().minusNanos(a.getMorningEntrance().toNanoOfDay())).toNanoOfDay() );			
		}
		if ( a.getAfternoonOut() != null ) {
			balance = balance.plusNanos( (a.getAfternoonOut().minusNanos(a.getAfternoonEntrance().toNanoOfDay())).toNanoOfDay() );			
		}
		if ( a.getNightOut() != null ) {
			balance = balance.plusNanos( (a.getNightOut().minusNanos(a.getNightEntrance().toNanoOfDay())).toNanoOfDay() );
		}
		if ( a.getParticularExitReturn() != null ) {
			balance = balance.minusNanos( (a.getParticularExitReturn().minusNanos(a.getParticularExit().toNanoOfDay())).toNanoOfDay() );			
		}
		if ( balance.isBefore( left ) ) {
			a.setHoursLeft( left.minusNanos( balance.toNanoOfDay() ) );
			a.setDayOvertime( LocalTime.MIN );
		} else {
			a.setHoursLeft( LocalTime.MIN );
			a.setDayOvertime( balance.minusNanos( left.toNanoOfDay() ) );
		}
		a.setBalance(balance);
		
		if( result.isSuccess() )
			result.setSuccess( Util.SAVE_SUCCESSFUL_APPOINTMENTS );
		
		return result;
	}
}
