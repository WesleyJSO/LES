package br.com.les.backend.filter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class DashboardFilter extends DomainEntity {

    private String employeeFullName;
    private String workedHoursOnTheParameterPeriod;
    private Long workedHoursOnTheParameterPeriodMinutes;
    private String balance;
    private String shouldBeWorked;
    private String countingFromMonth;
    private String lastMonth;
    private String hoursInLastMonth;
    private Double workedHoursComercial;
    private Double workedHoursNight;
    
    private Long balanceInMinutes;
    private Long hoursInLastMonthMinutes;
    private Short countingFromMonthNumber;
    private Short lastMonthNumber;
    
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
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getShouldBeWorked() {
		return shouldBeWorked;
	}
	public void setShouldBeWorked(String shouldBeWorked) {
		this.shouldBeWorked = shouldBeWorked;
	}
	public String getCountingFromMonth() {
		return countingFromMonth;
	}
	public void setCountingFromMonth(String countingFromMonth) {
		this.countingFromMonth = countingFromMonth;
	}
	public Double getWorkedHoursComercial() {
		return workedHoursComercial;
	}
	public void setWorkedHoursComercial(Double workedHoursComercial) {
		this.workedHoursComercial = workedHoursComercial;
	}
	public Double getWorkedHoursNight() {
		return workedHoursNight;
	}
	public void setWorkedHoursNight(Double workedHoursNight) {
		this.workedHoursNight = workedHoursNight;
	}
	public Long getBalanceInMinutes() {
		return balanceInMinutes;
	}
	public void setBalanceInMinutes(Long balanceInMinutes) {
		this.balanceInMinutes = balanceInMinutes;
	}
	public Long getHoursInLastMonthMinutes() {
		return hoursInLastMonthMinutes;
	}
	public void setHoursInLastMonthMinutes(Long hoursInLastMonthMinutes) {
		this.hoursInLastMonthMinutes = hoursInLastMonthMinutes;
	}
	public Short getLastMonthNumber() {
		return lastMonthNumber;
	}
	public void setLastMonthNumber(Short lastMonthNumber) {
		this.lastMonthNumber = lastMonthNumber;
	}
	public Short getCountingFromMonthNumber() {
		return countingFromMonthNumber;
	}
	public void setCountingFromMonthNumber(Short countingFromMonthNumber) {
		this.countingFromMonthNumber = countingFromMonthNumber;
	}
	public Long getWorkedHoursOnTheParameterPeriodMinutes() {
		return workedHoursOnTheParameterPeriodMinutes;
	}
	public void setWorkedHoursOnTheParameterPeriodMinutes(Long workedHoursOnTheParameterPeriodMinutes) {
		this.workedHoursOnTheParameterPeriodMinutes = workedHoursOnTheParameterPeriodMinutes;
	}
}
