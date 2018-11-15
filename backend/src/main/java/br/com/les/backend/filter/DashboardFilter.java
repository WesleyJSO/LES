package br.com.les.backend.filter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class DashboardFilter extends DomainEntity {

    private String employee;
    private Double workedHours;
    private String balance;
    private String lastMonth;
    private Double hoursInLastMonth;
    private Double workedHourComercial;
    private Double workedHourNight;
    
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public Double getWorkedHours() {
		return workedHours;
	}
	public void setWorkedHours(Double workedHours) {
		this.workedHours = workedHours;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}
	public Double getHoursInLastMonth() {
		return hoursInLastMonth;
	}
	public void setHoursInLastMonth(Double hoursInLastMonth) {
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
}
