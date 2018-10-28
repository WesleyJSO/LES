package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class BankedHours extends DomainEntity {
	
	private Double balance;
	
	@OneToOne()
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public BankedHours() {		
	}
	
	public BankedHours(Employee employee) {
		this.employee = employee;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
