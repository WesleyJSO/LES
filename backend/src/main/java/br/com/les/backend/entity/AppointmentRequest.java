package br.com.les.backend.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;


@Component
@Entity
public class AppointmentRequest extends DomainEntity {
	
	private String fieldToChange;
	private Boolean isAproved;
	private LocalTime replacement;
	
	@ManyToOne
	@JoinColumn(name="id_appointment")
	private Appointment appointment;

	public String getFieldToChange() {
		return fieldToChange;
	}

	public void setFieldToChange(String fieldToChange) {
		this.fieldToChange = fieldToChange;
	}

	public Boolean getIsAproved() {
		return isAproved;
	}

	public void setIsAproved(Boolean isAproved) {
		this.isAproved = isAproved;
	}

	public LocalTime getReplacement() {
		return replacement;
	}

	public void setReplacement(LocalTime replacement) {
		this.replacement = replacement;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

}
