package br.com.les.backend.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import br.com.les.backend.annotation.DeepSearchQuery;

@Component
@Entity
public class OvertimeBalance extends DomainEntity {
	
	private LocalDate monthAndYear;
	private Double balance;
	
	@OneToOne()
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@DeepSearchQuery (name="t.employee")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getMonthAndYear() {
		return monthAndYear;
	}

	public void setMonthAndYear(LocalDate monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

}
