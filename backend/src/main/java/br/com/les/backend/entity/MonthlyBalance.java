package br.com.les.backend.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.les.backend.annotation.DeepSearchQuery;

@Component
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class MonthlyBalance extends DomainEntity {

	private LocalDate monthAndYear;
	private Integer balanceHours;
	private Integer abscenseHours;
	private Integer overtimeHours;
	private Integer balanceMinutes;
	private Integer abscenseMinutes;
	private Integer overtimeMinutes;
	private Integer monthWorkload;

	@ManyToOne( fetch=FetchType.EAGER )
	@JoinColumn( name="employee_id" )
	private Employee employee;

	public MonthlyBalance () {
		this.balanceHours = 0;
		this.abscenseHours = 0;
		this.overtimeHours = 0;
		this.balanceMinutes = 0;
		this.abscenseMinutes = 0;
		this.overtimeMinutes = 0;
		this.monthWorkload = 0;
	}

	public LocalDate getMonthAndYear() {
		return monthAndYear;
	}

	public void setMonthAndYear(LocalDate monthAndYear) {
		this.monthAndYear = monthAndYear;
	}

	public Integer getBalanceHours() {
		return balanceHours;
	}

	public void setBalanceHours(Integer balanceHours) {
		this.balanceHours += balanceHours;
	}

	public Integer getAbscenseHours() {
		return abscenseHours;
	}

	public void setAbscenseHours(Integer abscenseHours) {
		this.abscenseHours += abscenseHours;
	}

	public Integer getOvertimeHours() {
		return overtimeHours;
	}

	public void setOvertimeHours(Integer overtimeHours) {
		this.overtimeHours += overtimeHours;
	}

	public Integer getBalanceMinutes() {
		return balanceMinutes;
	}

	public void setBalanceMinutes(Integer balanceMinutes) {
		this.balanceMinutes += balanceMinutes;
		if ( this.balanceMinutes >= 60 ) {
			this.balanceHours += this.balanceMinutes / 60;
			this.balanceMinutes = this.balanceMinutes % 60;
		}
	}

	public Integer getAbscenseMinutes() {
		return abscenseMinutes;
	}

	public void setAbscenseMinutes(Integer abscenseMinutes) {
		this.abscenseMinutes += abscenseMinutes;
		if ( this.abscenseMinutes >= 60 ) {
			this.abscenseHours += this.abscenseMinutes / 60;
			this.abscenseMinutes = this.abscenseMinutes % 60;
		}
	}

	public Integer getOvertimeMinutes() {
		return overtimeMinutes;
	}

	public void setOvertimeMinutes(Integer overtimeMinutes) {
		this.overtimeMinutes += overtimeMinutes;
		if ( this.overtimeMinutes >= 60 ) {
			this.overtimeHours += this.overtimeMinutes / 60;
			this.overtimeMinutes = this.overtimeMinutes % 60;
		}
	}

	@DeepSearchQuery(name="t.employee")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getMonthWorkload() {
		return monthWorkload;
	}

	public void setMonthWorkload(Appointment a) {
		this.monthWorkload += 8;
	}
	
}
