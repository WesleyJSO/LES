package br.com.les.backend.entity;
 
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Employee extends User {

	private static final long serialVersionUID = 7031669546742429620L;

	
	private String pis;
	private Date  entryDateInCompany;
	
	@ManyToOne( cascade=CascadeType.ALL)
	@JoinColumn( name="manager_id" )
	private User manager;

	@OneToMany( cascade=CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Request > requestList;
	
	@ManyToMany( mappedBy="notificatedColaboratorsList", fetch=FetchType.LAZY )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Request > notifiedEmployeesRequestList;
	
	@OneToMany( cascade=CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Appointments > appointmentList;
	
	@OneToOne( mappedBy="employee", fetch=FetchType.LAZY )
	private BaseHoursCalculation baseCalculationHours;

	@OneToMany( cascade=CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< CompTime > monthlyCompTimeList;

	public Employee() {}
	
	public Employee(Long employeeId ) {
		this.setId( employeeId );
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Date getEntryDateInCompany() {
		return entryDateInCompany;
	}

	public void setEntryDateInCompany(Date entryDateInCompany) {
		this.entryDateInCompany = entryDateInCompany;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	public List<Request> getNotifiedEmployeesRequestList() {
		return notifiedEmployeesRequestList;
	}

	public void setNotifiedEmployeesRequestList(List<Request> notifiedEmployeesRequestList) {
		this.notifiedEmployeesRequestList = notifiedEmployeesRequestList;
	}

	public List<Appointments> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointments> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public BaseHoursCalculation getBaseCalculationHours() {
		return baseCalculationHours;
	}

	public void setBaseCalculationHours(BaseHoursCalculation baseCalculationHours) {
		this.baseCalculationHours = baseCalculationHours;
	}

	public List<CompTime> getMonthlyCompTimeList() {
		return monthlyCompTimeList;
	}

	public void setMonthlyCompTimeList(List<CompTime> monthlyCompTimeList) {
		this.monthlyCompTimeList = monthlyCompTimeList;
	}
}
