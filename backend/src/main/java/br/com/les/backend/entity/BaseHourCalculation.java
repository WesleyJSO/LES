package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class BaseHourCalculation extends DomainEntity {

	private Short hourType = -1; // 0 - comp time, 1 - extra time, 2 - both
	private Double workload;
	private Date expirationDate;
	private Double salary;

	@OneToOne()
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Short getHourType() {
		return hourType;
	}

	public void setHourType(Short hourType) {
		this.hourType = hourType;
	}

	public Double getWorkload() {
		return workload;
	}

	public void setWorkload(Double workload) {
		this.workload = workload;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}
