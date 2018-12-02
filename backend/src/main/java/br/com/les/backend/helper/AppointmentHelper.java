package br.com.les.backend.helper;

import java.time.format.DateTimeFormatter;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.BaseHourCalculation;

public class AppointmentHelper {
	
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	private String day;
	private String hour;
	private String morningEntrance;
	private String morningOut;
	private String afternoonEntrance;
	private String afternoonOut;
	private String nightEntrance;
	private String nightOut;
	private String particularExit;
	private String particularExitReturn;
	private String balance;
	private String absence;
	private String overtime;
	
	public AppointmentHelper generateHelper(Appointment a, BaseHourCalculation bhc) {
		
		this.day = a.getDate().toLocalDate().format(dateFormatter);
		this.hour = null != bhc ? bhc.getWorkload().toString() : "";
		this.morningEntrance = null != a.getMorningEntrance() ? a.getMorningEntrance().format(timeFormatter) : "";
		this.morningOut = null != a.getMorningOut() ? a.getMorningOut().format(timeFormatter) : "";
		this.afternoonEntrance = null != a.getAfternoonEntrance() ? a.getAfternoonEntrance().format(timeFormatter) : "";
		this.afternoonOut = null != a.getAfternoonOut() ? a.getAfternoonOut().format(timeFormatter) : "";
		this.nightEntrance = null != a.getNightEntrance() ? a.getNightEntrance().format(timeFormatter) : "";
		this.nightOut = null != a.getNightOut() ? a.getNightOut().format(timeFormatter) : "";
		this.particularExit = null != a.getParticularExit() ? a.getParticularExit().format(timeFormatter) : "";
		this.particularExitReturn = null != a.getParticularExitReturn() ? a.getParticularExitReturn().format(timeFormatter) : "";
		this.balance = null != a.getBalance() ? a.getBalance().format(timeFormatter) : "";
		this.absence = null != a.getHoursLeft() ? a.getHoursLeft().format(timeFormatter) : "";
		this.overtime = null != a.getDayOvertime() ? a.getDayOvertime().format(timeFormatter) : "";
		return this;
	}
	
	
	public String getParticularExit() {
		return particularExit;
	}

	public void setParticularExit(String particularExit) {
		this.particularExit = particularExit;
	}	

	public String getParticularExitReturn() {
		return particularExitReturn;
	}

	public void setParticularExitReturn(String particularExitReturn) {
		this.particularExitReturn = particularExitReturn;
	}

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMorningEntrance() {
		return morningEntrance;
	}
	public void setMorningEntrance(String morningEntrance) {
		this.morningEntrance = morningEntrance;
	}
	public String getMorningOut() {
		return morningOut;
	}
	public void setMorningOut(String morningOut) {
		this.morningOut = morningOut;
	}
	public String getAfternoonEntrance() {
		return afternoonEntrance;
	}
	public void setAfternoonEntrance(String afternoonEntrance) {
		this.afternoonEntrance = afternoonEntrance;
	}
	public String getAfternoonOut() {
		return afternoonOut;
	}
	public void setAfternoonOut(String afternoonOut) {
		this.afternoonOut = afternoonOut;
	}
	public String getNightEntrance() {
		return nightEntrance;
	}
	public void setNightEntrance(String nightEntrance) {
		this.nightEntrance = nightEntrance;
	}
	public String getNightOut() {
		return nightOut;
	}
	public void setNightOut(String nightOut) {
		this.nightOut = nightOut;
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

}
