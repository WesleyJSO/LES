package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class AbsenceRequest extends Request {

	private static final long serialVersionUID = -5240926882445795466L;

	
	private Date absenceDate;
	private Date returnDate;
	private byte[] attachment;
	
	
	public Date getAbsenceDate() {
		return absenceDate;
	}
	public void setAbsenceDate(Date absenceDate) {
		this.absenceDate = absenceDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
}
