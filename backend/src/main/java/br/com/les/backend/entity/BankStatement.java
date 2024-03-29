package br.com.les.backend.entity;

import java.time.LocalDate;

public class BankStatement extends DomainEntity {
	
	private Long employeeId;
	private LocalDate month;
	
	public BankStatement(Long employeeId, LocalDate month) {
		this.employeeId = employeeId;
		this.month = month;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getMonth() {
		return month;
	}

	public void setMonth(LocalDate month) {
		this.month = month;
	}
	
}
