package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class ChangeAppointmentRequest extends Request {

	private static final long serialVersionUID = -6495527335471584927L;

	private Date oldDate;
	private Date newDate;

	
	public Date getOldDate() {
		return oldDate;
	}
	public void setOldDate(Date oldDate) {
		this.oldDate = oldDate;
	}
	public Date getNewDate() {
		return newDate;
	}
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	

}
