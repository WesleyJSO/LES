package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Appointments extends DomainEntity {

	private static final long serialVersionUID = -5608859856328862142L;

	private String description;
	private Date userAppointmentDate;
	private Date userSystemAccessDate;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="employee_id" )
	private Employee employee;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUserAppointmentDate() {
		return userAppointmentDate;
	}

	public void setUserAppointmentDate(Date userAppointmentDate) {
		this.userAppointmentDate = userAppointmentDate;
	}

	public Date getUserSystemAccessDate() {
		return userSystemAccessDate;
	}

	public void setUserSystemAccessDate(Date userSystemAccessDate) {
		this.userSystemAccessDate = userSystemAccessDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
