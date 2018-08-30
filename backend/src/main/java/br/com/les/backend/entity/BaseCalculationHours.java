package br.com.les.backend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class  BaseCalculationHours extends DomainEntity {

	private static final long serialVersionUID = 5309951868771462074L;

	private Short hourType = -1; // 0 - comp time, 1 - extra time, 2 - both
	private Double workload;
	private Date effectiveDate;
	private BigDecimal salary;

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

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
