package br.com.les.backend.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.les.backend.annotation.DeepSearchQuery;

@Entity
public class Request extends DomainEntity {

	private String description;
	/**
	 * User by managers to specify details after
	 * approve or reprove requests
	 */
	private String observation;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate updatedDate;
	
	private String managerName;
	
	private Integer status;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonDeserialize(using= LocalDateDeserializer.class)
	private LocalDate startDate;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@JsonDeserialize(using= LocalDateDeserializer.class)
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
	@DeepSearchQuery(name="t.employee")
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	
	
}
