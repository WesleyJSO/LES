package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Request extends DomainEntity {

	private String description;
	private boolean agreed;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="employee_id" )
	private User employee;
	
	@ManyToMany( cascade={ CascadeType.MERGE, CascadeType.PERSIST } )
	private List< Employee > notificatedColaboratorsList;

	@OneToOne( fetch=FetchType.LAZY )
	private RequestType requestType;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAgreed() {
		return agreed;
	}

	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public List<Employee> getNotificatedColaboratorsList() {
		return notificatedColaboratorsList;
	}

	public void setNotificatedColaboratorsList(List<Employee> notificatedColaboratorsList) {
		this.notificatedColaboratorsList = notificatedColaboratorsList;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
}
