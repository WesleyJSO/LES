package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class HistMonthlyBalanceChange extends DomainEntity {

	@ManyToOne( fetch=FetchType.EAGER )
	@JoinColumn( name="appointment_id" )
	private Appointment appointment;
	
	@ManyToOne( fetch=FetchType.EAGER )
	@JoinColumn( name="monthly_balance_id" )
	private MonthlyBalance monthlyBalance;
	
	private Double amount;

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public MonthlyBalance getMonthlyBalance() {
		return monthlyBalance;
	}

	public void setMonthlyBalance(MonthlyBalance monthlyBalance) {
		this.monthlyBalance = monthlyBalance;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}