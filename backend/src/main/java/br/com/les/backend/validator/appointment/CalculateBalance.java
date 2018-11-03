package br.com.les.backend.validator.appointment;

import java.time.LocalTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

public class CalculateBalance implements IStrategy<Appointment> {


	@Override
	public void process(Appointment aEntity, INavigationCase<Appointment> aCase) {

		if (aEntity != null) {

			Employee employee = (Employee) authenticatedUser();
			int employeeWorkload = employee.getBaseHourCalculation().getWorkload();
			

			LocalTime balance = LocalTime.MIN;
			LocalTime workload = LocalTime.of(employeeWorkload, 0);
			
			aEntity.setCalculated(false);
			
			if ( aEntity.getMorningOut() != null ) {
				balance = balance.plusNanos( (aEntity.getMorningOut().minusNanos(aEntity.getMorningEntrance().toNanoOfDay())).toNanoOfDay() );			
			}
			if ( aEntity.getAfternoonOut() != null ) {
				balance = balance.plusNanos( (aEntity.getAfternoonOut().minusNanos(aEntity.getAfternoonEntrance().toNanoOfDay())).toNanoOfDay() );			
			}
			if ( aEntity.getNightOut() != null ) {
				balance = balance.plusNanos( (aEntity.getNightOut().minusNanos(aEntity.getNightEntrance().toNanoOfDay())).toNanoOfDay() );
			}
			if ( aEntity.getParticularExitReturn() != null ) {
				balance = balance.minusNanos( (aEntity.getParticularExitReturn().minusNanos(aEntity.getParticularExit().toNanoOfDay())).toNanoOfDay() );			
			}
			
			if ( balance.isBefore( workload ) ) {
				aEntity.setHoursLeft( workload.minusNanos( balance.toNanoOfDay() ) );
				aEntity.setDayOvertime( LocalTime.MIN );
			} else {
				aEntity.setHoursLeft( LocalTime.MIN );
				aEntity.setDayOvertime( balance.minusNanos( workload.toNanoOfDay() ) );
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento inexistente!");
		return;
	}
	
	protected DomainEntity authenticatedUser () {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		DomainEntity entity = (DomainEntity) authentication.getPrincipal();
		
		return entity;
		
	}
}