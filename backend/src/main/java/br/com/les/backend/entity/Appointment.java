package br.com.les.backend.entity;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component
@Entity
public class Appointment extends DomainEntity {

	private static final long serialVersionUID = 1L;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Calendar date;
	private LocalTime morningEntrance;
	private LocalTime morningOut;
	private LocalTime afternoonEntrance;
	private LocalTime afternoonOut;
	private LocalTime nightEntrance;
	private LocalTime nightOut;
	private LocalTime particularExit;
	private LocalTime particularExitReturn;
	private LocalTime balance;
	private LocalTime hoursLeft;
	private LocalTime dayOvertime;
	
	@ManyToOne( fetch=FetchType.EAGER )
	@JoinColumn( name="employee_id" )
	private Employee employee;

	public Appointment() {}
	
	public Appointment( Long employeeId ) {
		this.employee = new Employee( employeeId );;
	}

	public Calendar getDate() {
		return date;
	}

	@PrePersist
	public void setDate() {
		this.date = Calendar.getInstance();
		this.date.add(Calendar.DATE, 0);
	}

	public LocalTime getMorningEntrance() {
		return morningEntrance;
	}

	public void setMorningEntrance(LocalTime morningEntrance) {
		this.morningEntrance = morningEntrance;
	}

	public LocalTime getMorningOut() {
		return morningOut;
	}

	public void setMorningOut(LocalTime morningOut) {
		this.morningOut = morningOut;
	}

	public LocalTime getAfternoonEntrance() {
		return afternoonEntrance;
	}

	public void setAfternoonEntrance(LocalTime afternoonEntrance) {
		this.afternoonEntrance = afternoonEntrance;
	}

	public LocalTime getAfternoonOut() {
		return afternoonOut;
	}

	public void setAfternoonOut(LocalTime afternoonOut) {
		this.afternoonOut = afternoonOut;
	}

	public LocalTime getNightEntrance() {
		return nightEntrance;
	}

	public void setNightEntrance(LocalTime nightEntrance) {
		this.nightEntrance = nightEntrance;
	}

	public LocalTime getNightOut() {
		return nightOut;
	}

	public void setNightOut(LocalTime nightOut) {
		this.nightOut = nightOut;
	}

	public LocalTime getParticularExit() {
		return particularExit;
	}

	public void setParticularExit(LocalTime particularExit) {
		this.particularExit = particularExit;
	}

	public LocalTime getParticularExitReturn() {
		return particularExitReturn;
	}

	public void setParticularExitReturn(LocalTime particularExitReturn) {
		this.particularExitReturn = particularExitReturn;
	}

	public LocalTime getHoursLeft() {
		return hoursLeft;
	}

	public void setHoursLeft(LocalTime hoursLeft) {
		this.hoursLeft = hoursLeft;
	}

	public LocalTime getDayOvertime() {
		return dayOvertime;
	}

	public void setDayOvertime(LocalTime dayOvertime) {
		this.dayOvertime = dayOvertime;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalTime getBalance() {
		return balance;
	}

	public void setBalance(LocalTime balance) {
		this.balance = balance;
	}
}
