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
	private Integer bankBalanceHour;
	private Integer bankBalanceMinute;

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
		this.bankBalanceHour = 0;
		this.bankBalanceMinute = 0;
	}
	
	public void calculateBalanceHoursAndMinutes(Integer balanceMinutes) {
		this.balanceMinutes += balanceMinutes;
		if ( this.balanceMinutes >= 60 ) {
			this.balanceHours += this.balanceMinutes / 60;
			this.balanceMinutes = this.balanceMinutes % 60;
		}
	}
	public void calculateAbscenseHoursAndMinutes(Integer abscenseMinutes) {
		this.abscenseMinutes += abscenseMinutes;
		if ( this.abscenseMinutes >= 60 ) {
			this.abscenseHours += this.abscenseMinutes / 60;
			this.abscenseMinutes = this.abscenseMinutes % 60;
		}
	}
	public void calculateOvertimeHoursAndMinutes(Integer overtimeMinutes) {
		this.overtimeMinutes += overtimeMinutes;
		if ( this.overtimeMinutes >= 60 ) {
			this.overtimeHours += this.overtimeMinutes / 60;
			this.overtimeMinutes = this.overtimeMinutes % 60;
		}
	}
	public void addToBankBalanceHours(Integer bankBalanceHour) {
		this.bankBalanceHour += bankBalanceHour;
	}
	public void subToBankBalanceHours(Integer bankBalanceHour) {
		this.bankBalanceHour -= bankBalanceHour;
	}
	public void calculateBankBalanceHoursAndMinutes(Integer bankBalanceMinute) {
		this.bankBalanceMinute += bankBalanceMinute;
		if ( this.bankBalanceHour < 0 ) {
			if ( this.bankBalanceMinute > 0 ) {
				this.bankBalanceHour += 1;
				this.bankBalanceMinute = (60 - this.bankBalanceMinute % 60) * -1;
			}
		} else {
			if ( this.bankBalanceMinute >= 60 ) {
				this.bankBalanceHour += this.bankBalanceMinute / 60;
				this.bankBalanceMinute = this.bankBalanceMinute % 60;
			}
		}
	}
	public void subBankBalanceHoursAndMinutes(Integer bankBalanceMinute) {
		this.bankBalanceMinute -= bankBalanceMinute;
		if ( this.bankBalanceHour > 0 ) {
			if ( this.bankBalanceMinute < 0 ) {
				this.bankBalanceHour -= 1;
				this.bankBalanceMinute = 60 + this.bankBalanceMinute % 60;
			}
		} else {
			if ( this.bankBalanceMinute < -60 ) {
				this.bankBalanceHour += this.bankBalanceMinute / 60;
				this.bankBalanceMinute = this.bankBalanceMinute % 60;
			}
		}
	}
	public void setPlusEightMonthWorkload() {
		this.monthWorkload += 8;
	}
	public void addToOvertimeHours(Integer overtimeHours) {
		this.overtimeHours += overtimeHours;
	}
	public void addToBalanceHours(Integer balanceHours) {
		this.balanceHours += balanceHours;
	}
	public void addToAbscenseHours(Integer abscenseHours) {
		this.abscenseHours += abscenseHours;
	}
	public void addToMonthWorkload(Integer monthWorkload) {
	 this.monthWorkload += monthWorkload;
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

	public Integer getAbscenseHours() {
		return abscenseHours;
	}
	
	public Integer getOvertimeHours() {
		return overtimeHours;
	}

	public Integer getBalanceMinutes() {
		return balanceMinutes;
	}

	public Integer getAbscenseMinutes() {
		return abscenseMinutes;
	}

	public Integer getOvertimeMinutes() {
		return overtimeMinutes;
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

	public void setBalanceMinutes(Integer balanceMinutes) {
		this.balanceMinutes = balanceMinutes;
	}

	public void setAbscenseMinutes(Integer abscenseMinutes) {
		this.abscenseMinutes = abscenseMinutes;
	}

	public void setOvertimeMinutes(Integer overtimeMinutes) {
		this.overtimeMinutes = overtimeMinutes;
	}

	public void setBalanceHours(Integer balanceHours) {
		this.balanceHours = balanceHours;
	}

	public void setAbscenseHours(Integer abscenseHours) {
		this.abscenseHours = abscenseHours;
	}

	public void setOvertimeHours(Integer overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	public void setMonthWorkload(Integer monthWorkload) {
		this.monthWorkload = monthWorkload;
	}

	public Integer getBankBalanceHour() {
		return bankBalanceHour;
	}

	public void setBankBalanceHour(Integer bankBalanceHour) {
		this.bankBalanceHour = bankBalanceHour;
	}

	public Integer getBankBalanceMinute() {
		return bankBalanceMinute;
	}

	public void setBankBalanceMinute(Integer bankBalanceMinute) {
		this.bankBalanceMinute = bankBalanceMinute;
	}
	
	
}
