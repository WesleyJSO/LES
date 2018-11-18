package br.com.les.backend.strategy.appointment;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.HolidayRepository;
import br.com.les.backend.service.SecurityService;

@Configuration
public class CalculateBalance implements IStrategy<Appointment> {
	
	@Autowired HolidayRepository holidayRepository;

	@Override
	public void process(Appointment aEntity, INavigationCase<Appointment> aCase) {

		if (aEntity != null) {

			LocalDate today = LocalDate.now();
			Employee employee = (Employee) SecurityService.getAuthenticatedUser();
			int employeeWorkload = 0;
			
			if ( today.getDayOfWeek() != DayOfWeek.SUNDAY 
				&& today.getDayOfWeek() != DayOfWeek.SATURDAY
				&& holidayRepository.findBydate(today).isEmpty()) {
				employeeWorkload = employee.getBaseHourCalculation().getWorkload();
			}
			
			
			LocalTime balance = LocalTime.MIN;
			LocalTime workload = LocalTime.of(employeeWorkload, 0);
			
			aEntity.setCalculated(false);
			
			if ( aEntity.getMorningOut() != null && aEntity.getMorningEntrance() != null) {
				balance = balance.plusNanos( (aEntity.getMorningOut().minusNanos(aEntity.getMorningEntrance().toNanoOfDay())).toNanoOfDay() );			
			}
			if ( aEntity.getAfternoonOut() != null && aEntity.getAfternoonEntrance() != null) {
				balance = balance.plusNanos( (aEntity.getAfternoonOut().minusNanos(aEntity.getAfternoonEntrance().toNanoOfDay())).toNanoOfDay() );			
			}
			if ( aEntity.getNightOut() != null && aEntity.getNightEntrance() != null) {
				balance = balance.plusNanos( (aEntity.getNightOut().minusNanos(aEntity.getNightEntrance().toNanoOfDay())).toNanoOfDay() );
			}
			if ( aEntity.getParticularExitReturn() != null && aEntity.getParticularExit() != null) {
				balance = balance.minusNanos( (aEntity.getParticularExitReturn().minusNanos(aEntity.getParticularExit().toNanoOfDay())).toNanoOfDay() );			
			}
			
			aEntity.setBalance(balance);
			
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
		aCase.getResult().setError("Apontamento necessário para realização do cálculo inexistente!");
		return;
	}
}