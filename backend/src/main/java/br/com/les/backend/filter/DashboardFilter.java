package br.com.les.backend.filter;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class DashboardFilter extends DomainEntity {

	
    private String employeeFullName;
    private String workedHoursOnTheParameterPeriod;
    private String Balance;
    private String lastMonth;
    private String hoursInLastMonth;
    private Double workedHourComercial;
    private Double workedHourNight;
    
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public String getHoursInLastMonth() {
		return hoursInLastMonth;
	}
	public void setHoursInLastMonth(String hoursInLastMonth) {
		this.hoursInLastMonth = hoursInLastMonth;
	}
	public Double getWorkedHourComercial() {
		return workedHourComercial;
	}
	public void setWorkedHourComercial(Double workedHourComercial) {
		this.workedHourComercial = workedHourComercial;
	}
	public Double getWorkedHourNight() {
		return workedHourNight;
	}
	public void setWorkedHourNight(Double workedHourNight) {
		this.workedHourNight = workedHourNight;
	}
	public String getEmployeeFullName() {
		return employeeFullName;
	}
	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}
	public String getWorkedHoursOnTheParameterPeriod() {
		return workedHoursOnTheParameterPeriod;
	}
	public void setWorkedHoursOnTheParameterPeriod(String workedHoursOnTheParameterPeriod) {
		this.workedHoursOnTheParameterPeriod = workedHoursOnTheParameterPeriod;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
}
