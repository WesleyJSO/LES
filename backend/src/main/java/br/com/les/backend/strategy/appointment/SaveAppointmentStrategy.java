package br.com.les.backend.strategy.appointment;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.utils.Result;

@Component
public class SaveAppointmentStrategy extends AbstractStrategy<Appointment> {

	private Employee employee;
	
	@Override
	public Result<Appointment> execute(Appointment a) {

		Result<Appointment> result = new Result<>();
		
		employee = (Employee) authenticatedUser();
		
		int employeeWorkload = employee.getBaseHourCalculation().getWorkload();
		
		LocalTime balance = LocalTime.MIN;
		LocalTime workload = LocalTime.of(employeeWorkload, 0);
		
		a.setCalculated(false);
		
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
		
		if ( balance.isBefore( workload ) ) {
			a.setHoursLeft( workload.minusNanos( balance.toNanoOfDay() ) );
			a.setDayOvertime( LocalTime.MIN );
		} else {
			a.setHoursLeft( LocalTime.MIN );
			a.setDayOvertime( balance.minusNanos( workload.toNanoOfDay() ) );
		}
		
		return result;
	}

}
