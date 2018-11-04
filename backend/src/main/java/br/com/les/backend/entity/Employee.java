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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
public class Employee extends DomainEntity {
	
	private String pis;
	private Date  joiningDate;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;
	private String name;
	private String lastName;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Telephone> telephoneList;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="manager_id")
	private Employee manager;

	@OneToMany( cascade=CascadeType.ALL )
	private List< Request > requestList;
	
	@ManyToMany( mappedBy="notificatedColaboratorsList", fetch=FetchType.LAZY )
	private List< Request > notifiedEmployeesRequestList;
	
	@JsonIgnore
	@OneToMany( cascade=CascadeType.ALL )
	private List< Appointment > appointmentList;
	
	@JsonIgnore
	@OneToOne(mappedBy="employee", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private BaseHourCalculation baseHourCalculation;
	
	@JsonIgnore
	@OneToOne(mappedBy="employee", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private BankedHours bankedHours;

	@OneToMany( cascade=CascadeType.ALL )
	private List< CompTime > monthlyCompTimeList;
	
	@JsonIgnore
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "costCentre_id")
	private CostCentre costCentre;
	
	@JsonIgnore
	@OneToMany( cascade=CascadeType.ALL )
	private List< MonthlyBalance > monthBalanceList;

	public List<MonthlyBalance> getMonthBalanceList() {
		return monthBalanceList;
	}

	public void setMonthBalanceList(List<MonthlyBalance> monthBalanceList) {
		this.monthBalanceList = monthBalanceList;
	}

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

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
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

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}
	
	public List<CompTime> getMonthlyCompTimeList() {
		return monthlyCompTimeList;
	}

	public void setMonthlyCompTimeList(List<CompTime> monthlyCompTimeList) {
		this.monthlyCompTimeList = monthlyCompTimeList;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public BaseHourCalculation getBaseHourCalculation() {
		return baseHourCalculation;
	}

	public void setBaseHourCalculation(BaseHourCalculation baseHourCalculation) {
		this.baseHourCalculation = baseHourCalculation;
	}

	public CostCentre getCostCentre() {
		return costCentre;
	}

	public void setCostCentre(CostCentre costCentre) {
		this.costCentre = costCentre;
	}

	public List<Telephone> getTelephoneList() {
		return telephoneList;
	}

	public void setTelephoneList(List<Telephone> telephoneList) {
		this.telephoneList = telephoneList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
