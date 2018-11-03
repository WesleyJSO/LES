package br.com.les.backend.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Request extends DomainEntity {

	private String description;
	private int status;
	private LocalDate startDate;
	private LocalDate endDate;
	private int type;
	private byte[] attachment;
	
	@ManyToOne( fetch=FetchType.EAGER )
	@JoinColumn( name="employee_id" )
	private Employee employee;
	
	@ManyToMany( cascade={ CascadeType.MERGE, CascadeType.PERSIST } )
	private List< Employee > notificatedColaboratorsList;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getNotificatedColaboratorsList() {
		return notificatedColaboratorsList;
	}

	public void setNotificatedColaboratorsList(List<Employee> notificatedColaboratorsList) {
		this.notificatedColaboratorsList = notificatedColaboratorsList;
	}
	
	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}	
}
