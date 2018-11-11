package br.com.les.backend.task;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.BankedHours;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.EmployeeRepository;

@Component
public class CalcBankTask extends TimerTask {
	
	@Autowired AutoAppointmentTask autoAppointmentTask;
	
	@Autowired GenericDAO<BankedHours> bankedHoursDAO;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired AppointmentRepository appointmentRepository;

	// Period in milliseconds
 	private final static long PERIOD = 1000 * 60 * 60 * 24;
 	// used to indicate init date
 	private final static int ONE_DAY = 0;
 	// schedule hour
 	private final static int HOUR = 4;
 	// schedule minutes
 	private final static int MINUTE = 0;
 	
 	@PostConstruct
 	public void calcBankTask() {
 		
 		// perform the task once a day at 4 a.m., starting tomorrow morning
 		Timer timer = new Timer();
 		timer.scheduleAtFixedRate(this, getSchedule(), PERIOD);
 	}
	
	private static Date getSchedule() {
		
		Calendar tomorrow = new GregorianCalendar();
		tomorrow.add(Calendar.DATE, ONE_DAY);
		Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
		    tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE), HOUR,
		    MINUTE);
		return result.getTime();
		
	}
	
	@Override
    public void run() {
    	
		autoAppointmentTask.run();
		
    	BankedHours bank = null;
		
		List< Employee > employeeList = employeeRepository.findAll();
		
		for ( Employee employee: employeeList) {

			Double balanceToInsert = 0d;
			
			// get employee workload
			int employeeWorkload = employee.getBaseHourCalculation().getWorkload();
			LocalTime workload = LocalTime.of(employeeWorkload, 0);
			
			bank = new BankedHours();
			Employee emp = new Employee();
			emp.setId(employee.getId());
			bank.setEmployee(emp);
			bank = bankedHoursDAO.find(bank).get(0);
			
			// get new or edited appointments
			List< Appointment > appointmentList = appointmentRepository.findPending(employee);
			
			for (Appointment appointment: appointmentList) {
				
				if ( appointment.getBalance().isBefore( workload ) ) {
					balanceToInsert = getDoubleTime(appointment.getHoursLeft()) * (-1);
				} else {
					balanceToInsert = getDoubleTime(appointment.getDayOvertime());
				}
				
				// subtract previous balance calculated, if exists
				if ( appointment.getPreviousBalanceInserted() != null ) {
					bank.setBalance( bank.getBalance() - appointment.getPreviousBalanceInserted() );
				}
				
				bank.setBalance(bank.getBalance() + balanceToInsert);
				appointment.setPreviousBalanceInserted(balanceToInsert);
				appointment.setCalculated(true);
			}
			
			try {
				bankedHoursDAO.save(bank);
				appointmentRepository.saveAll(appointmentList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    }

	public Double getDoubleTime(LocalTime time) {

		Double minuteValue = Double.valueOf( time.getMinute() / 60d);
		Double hourValue = time.getHour() + minuteValue;
		return hourValue;
	}

}
