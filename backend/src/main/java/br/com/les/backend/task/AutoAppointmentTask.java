package br.com.les.backend.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.EmployeeRepository;

@Component
public class AutoAppointmentTask {
	
	@Autowired EmployeeRepository employeeRepository;
	@Autowired AppointmentRepository appointmentRepository;

 	private final static int HOURRANGE = 2;
 	
 	private final static int MINUTERANGE = 59;
 	
    public void run() {
    	
    	Appointment appointment = null;
    	
    	Random random = new Random();
    	
    	Integer intHour = 0;
    	Integer intMinute = 0;
    	
    	LocalTime balance = LocalTime.MIN;
		LocalTime workload = null;
    	
    	List< Appointment > allAppointments = new ArrayList<>();
		
		List< Employee > employeeList = employeeRepository.findAll();
		
		for ( Employee employee: employeeList) {

			for (LocalDate date = LocalDate.of(2018, 7, 1); 
					date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()); 
					date = date.plusDays(1l)) {
				
				if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY )
					continue;
				
				appointment = new Appointment();
				
				int employeeWorkload = employee.getBaseHourCalculation().getWorkload();
				
				balance = LocalTime.MIN;
				workload = LocalTime.of(employeeWorkload, 0);
				
				appointment.setCalculated(false);
				
				intHour = random.nextInt(HOURRANGE);
				intMinute = random.nextInt(MINUTERANGE);
				appointment.setMorningEntrance(LocalTime.of(8 + intHour, intMinute));
				intMinute = random.nextInt(MINUTERANGE) / 2;
				appointment.setMorningOut(LocalTime.of(12, intMinute));
				intMinute = random.nextInt(MINUTERANGE) / 2;
				appointment.setAfternoonEntrance(LocalTime.of(13, intMinute));
				intHour = random.nextInt(HOURRANGE);
				intMinute = random.nextInt(MINUTERANGE);
				appointment.setAfternoonOut(LocalTime.of(17 + intHour, intMinute));
				appointment.setDate(LocalDateTime.of(date, LocalTime.MIN));
				appointment.setEmployee(employee);
				
				balance = balance.plusNanos( (appointment.getMorningOut().minusNanos(appointment.getMorningEntrance().toNanoOfDay())).toNanoOfDay() );
				balance = balance.plusNanos( (appointment.getAfternoonOut().minusNanos(appointment.getAfternoonEntrance().toNanoOfDay())).toNanoOfDay() );
				
				if ( balance.isBefore( workload ) ) {
					appointment.setHoursLeft( workload.minusNanos( balance.toNanoOfDay() ) );
					appointment.setDayOvertime( LocalTime.MIN );
				} else {
					appointment.setHoursLeft( LocalTime.MIN );
					appointment.setDayOvertime( balance.minusNanos( workload.toNanoOfDay() ) );
				}
				
				appointment.setBalance(balance);
				appointment.setMonthAndYear(date.withDayOfMonth(1));
				allAppointments.add(appointment);
			}
			
		}
		try {
			appointmentRepository.saveAll(allAppointments);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public Double getDoubleTime(LocalTime time) {

		Double minuteValue = Double.valueOf( time.getMinute() / 60d);
		Double hourValue = time.getHour() + minuteValue;
		return hourValue;
	}

}
