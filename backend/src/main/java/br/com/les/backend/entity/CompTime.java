package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class CompTime extends DomainEntity {

	private float balance;
	private float monthlyHoursLimit;
	private float dailyHoursLimit;
	private boolean monthlyHoursLimitActive;
	private short thisMonthBalance;
	private String thisMonthBalanceDescription;
	private short monthPaymentLimit;
	private String descriptionOfMontlyPaymentLimit;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="employee_id" )
	private Employee employee;

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getMonthlyHoursLimit() {
		return monthlyHoursLimit;
	}

	public void setMonthlyHoursLimit(float monthlyHoursLimit) {
		this.monthlyHoursLimit = monthlyHoursLimit;
	}

	public float getDailyHoursLimit() {
		return dailyHoursLimit;
	}

	public void setDailyHoursLimit(float dailyHoursLimit) {
		this.dailyHoursLimit = dailyHoursLimit;
	}

	public boolean isMonthlyHoursLimitActive() {
		return monthlyHoursLimitActive;
	}

	public void setMonthlyHoursLimitActive(boolean monthlyHoursLimitActive) {
		this.monthlyHoursLimitActive = monthlyHoursLimitActive;
	}

	public short getThisMonthBalance() {
		return thisMonthBalance;
	}

	public void setThisMonthBalance(short thisMonthBalance) {
		this.thisMonthBalance = thisMonthBalance;
	}

	public String getThisMonthBalanceDescription() {
		return thisMonthBalanceDescription;
	}

	public void setThisMonthBalanceDescription(String thisMonthBalanceDescription) {
		this.thisMonthBalanceDescription = thisMonthBalanceDescription;
	}

	public short getMonthPaymentLimit() {
		return monthPaymentLimit;
	}

	public void setMonthPaymentLimit(short monthPaymentLimit) {
		this.monthPaymentLimit = monthPaymentLimit;
	}

	public String getDescriptionOfMontlyPaymentLimit() {
		return descriptionOfMontlyPaymentLimit;
	}

	public void setDescriptionOfMontlyPaymentLimit(String descriptionOfMontlyPaymentLimit) {
		this.descriptionOfMontlyPaymentLimit = descriptionOfMontlyPaymentLimit;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
