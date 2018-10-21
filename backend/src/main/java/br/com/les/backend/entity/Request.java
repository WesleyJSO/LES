package br.com.les.backend.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.les.backend.utils.RequestStatus;
import br.com.les.backend.utils.RequestType;

@Entity
@Inheritance( strategy=InheritanceType.TABLE_PER_CLASS )
public class Request extends DomainEntity {

	private static final long serialVersionUID = 9026354384897022658L;

	private String description;
	private RequestStatus status;
	private Date startDate;
	private Date endDate;
	private int type;
	private byte[] attachment;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="employee_id" )
	private User employee;
	
	@ManyToMany( cascade={ CascadeType.MERGE, CascadeType.PERSIST } )
	private List< Employee > notificatedColaboratorsList;
	
	public Request () {}
	
	public Request (Long requestId) {
		setId(requestId);
	}

	public Request(String description, RequestStatus status, Date startDate, Date endDate, int type,
			byte[] attachment, User employee, List<Employee> notificatedColaboratorsList) {
		super();
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.attachment = attachment;
		this.employee = employee;
		this.notificatedColaboratorsList = notificatedColaboratorsList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}	
}
