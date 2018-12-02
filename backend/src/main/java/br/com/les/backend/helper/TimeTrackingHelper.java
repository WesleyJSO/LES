package br.com.les.backend.helper;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.BaseHourCalculation;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.MonthlyBalance;

public class TimeTrackingHelper {
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String initDate;
	private String endDate;
	private String workingHours;
	private String balance;
	private String absence;
	private String overtime;
	private String monthlyBalance;
	private String employeeName;
	private List<AppointmentHelper> appointmentList;

	public TimeTrackingHelper generateHelper(LocalDate dt, List<Appointment> aList, 
			BaseHourCalculation bhc, MonthlyBalance mb, Employee emp) {
		
		this.initDate = dt.with(firstDayOfMonth()).format(dateFormatter);
		this.endDate = dt.with(lastDayOfMonth()).format(dateFormatter);
		this.workingHours = bhc.getWorkload().toString();
		this.balance = mb.getBalanceHours() < 10 ? "0" + mb.getBalanceHours() : mb.getBalanceHours().toString();
		this.balance += ":";
		this.balance += mb.getBalanceMinutes() < 10 ? "0" + mb.getBalanceMinutes() : mb.getBalanceMinutes().toString();
		this.absence = mb.getAbscenseHours() < 10 ? "0" + mb.getAbscenseHours() : mb.getAbscenseHours().toString();
		this.absence += ":";
		this.absence += mb.getAbscenseMinutes() < 10 ? "0" + mb.getAbscenseMinutes() : mb.getAbscenseMinutes().toString();
		this.overtime = mb.getOvertimeHours() < 10 ? "0" + mb.getOvertimeHours() : mb.getOvertimeHours().toString();
		this.overtime += ":";
		this.overtime += mb.getOvertimeMinutes() < 10 ? "0" + mb.getOvertimeMinutes() : mb.getOvertimeMinutes().toString();
		long monthBalance = 
				(mb.getOvertimeHours() * 60 + mb.getOvertimeMinutes())
				- (mb.getAbscenseHours() * 60 + mb.getAbscenseMinutes());
		Integer hour = Math.abs((int)monthBalance / 60);
		Integer minutes = Math.abs((int)monthBalance % 60);
		this.monthlyBalance = hour < 10 ? "0" + hour + ":" : hour + ":";
		this.monthlyBalance += minutes < 10 ? "0" + minutes : minutes.toString();
		if ( monthBalance < 0 ) {
			this.monthlyBalance = "-" + this.monthlyBalance;
		}
		this.employeeName = emp.getName() + " " + emp.getLastName();
		
		appointmentList = new ArrayList<AppointmentHelper>();
		AppointmentHelper aHelper = null;
		Appointment appointment = null;
		LocalDate daysCount = null;
		for ( daysCount = dt.with(firstDayOfMonth()); 
				daysCount.isBefore(dt.with(firstDayOfMonth()).plusMonths(1)); 
				daysCount = daysCount.plusDays(1)) {
			aHelper = new AppointmentHelper();
			LocalDateTime dateTime = LocalDateTime.of(daysCount, LocalTime.MIDNIGHT);
			appointment = aList.stream()
					.filter(a -> a.getDate().equals(dateTime))
					.findAny().orElse(null);
			if ( null == appointment) {
				appointment = new Appointment();
				appointment.setDate(daysCount.atStartOfDay());
				aHelper = aHelper.generateHelper(appointment, null);
			} else {
				aHelper = aHelper.generateHelper(appointment, bhc);
			}
			appointmentList.add(aHelper);
		}
		
		return this;
	}
	
	public String getInitDate() {
		return initDate;
	}
	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getAbsence() {
		return absence;
	}
	public void setAbsence(String absence) {
		this.absence = absence;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getMonthlyBalance() {
		return monthlyBalance;
	}
	public void setMonthlyBalance(String monthlyBalance) {
		this.monthlyBalance = monthlyBalance;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public List<AppointmentHelper> getAppointmentList() {
		return appointmentList;
	}
	public void setAppointmentList(List<AppointmentHelper> appointmentList) {
		this.appointmentList = appointmentList;
	} 

}
