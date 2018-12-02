package br.com.les.backend.task;

import java.time.LocalTime;
import java.util.ArrayList;
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
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.EmployeeRepository;
import br.com.les.backend.repository.MonthlyBalanceRepository;

@Component
public class CalcBankTask extends TimerTask {
	
	@Autowired HolidayTask holidayTask;
	@Autowired AutoAppointmentTask autoAppointmentTask;

	@Autowired GenericDAO<MonthlyBalance> monthlyBalanceDAO;
	@Autowired GenericDAO<BankedHours> bankedHoursDAO;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired AppointmentRepository appointmentRepository;
	@Autowired MonthlyBalanceRepository monthlyBalanceRepository;

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
		
		holidayTask.run();
    	
		autoAppointmentTask.run();
		
		MonthlyBalance monthlyBalance = null;
		
		List< MonthlyBalance > monthlyBalanceListHelper = null;
		
    	BankedHours bank = null;
		
		List< Employee > employeeList = employeeRepository.findAll();
		
		for ( Employee employee: employeeList) {
			
			if ( null == employee.getBaseHourCalculation() ) {
				continue;
			}

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

			List< MonthlyBalance > monthlyBalanceList = new ArrayList<>();
			
			Integer lastMonthHour = 0;
			Integer lastMonthMinute = 0;
			
			for (Appointment appointment: appointmentList) {
				
				if ( null == monthlyBalance || !monthlyBalance.getMonthAndYear().isEqual(appointment.getMonthAndYear())) {
					if ( null != monthlyBalance ) {
						lastMonthHour = monthlyBalance.getBankBalanceHour();
						lastMonthMinute = monthlyBalance.getBankBalanceMinute();
					}
					monthlyBalance = new MonthlyBalance();
					monthlyBalance.setMonthAndYear(appointment.getMonthAndYear());
					monthlyBalance.setEmployee(emp);
					monthlyBalanceListHelper = monthlyBalanceDAO.find(monthlyBalance);
					if ( monthlyBalanceListHelper.isEmpty() ) {
						monthlyBalance = null;
					} else {
						monthlyBalance = monthlyBalanceListHelper.get(0);
					}
					if ( null == monthlyBalance ) {
						monthlyBalance = new MonthlyBalance();
						monthlyBalance.setMonthAndYear(appointment.getMonthAndYear());
						monthlyBalance.setEmployee(employee);
					}
					monthlyBalance.setBankBalanceHour(lastMonthHour);
					monthlyBalance.setBankBalanceMinute(lastMonthMinute);
					monthlyBalanceList.add(monthlyBalance);
				}
				
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
				
				monthlyBalance.addToBankBalanceHours(appointment.getDayOvertime().getHour());
				monthlyBalance.calculateBankBalanceHoursAndMinutes(appointment.getDayOvertime().getMinute());
				monthlyBalance.subToBankBalanceHours(appointment.getHoursLeft().getHour());
				monthlyBalance.subBankBalanceHoursAndMinutes(appointment.getHoursLeft().getMinute());
				
				monthlyBalance.addToBalanceHours(appointment.getBalance().getHour());
				monthlyBalance.calculateBalanceHoursAndMinutes(appointment.getBalance().getMinute());
				monthlyBalance.addToAbscenseHours(appointment.getHoursLeft().getHour());
				monthlyBalance.calculateAbscenseHoursAndMinutes(appointment.getHoursLeft().getMinute());
				monthlyBalance.addToOvertimeHours(appointment.getDayOvertime().getHour());
				monthlyBalance.calculateOvertimeHoursAndMinutes(appointment.getDayOvertime().getMinute());
				monthlyBalance.setPlusEightMonthWorkload();
			}
			
			monthlyBalance = null;
			
			try {
				monthlyBalanceRepository.saveAll(monthlyBalanceList);
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
