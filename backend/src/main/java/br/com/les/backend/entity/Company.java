package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Company extends DomainEntity {

	private static final long serialVersionUID = 2623152606711154523L;

	private String socialName;
	private String tradingName;
	private String cnpj;
	private String stateRegistration;
	
	@Embedded
	Address address;
	
	@ManyToMany
	private List< Employee > employeeList;

	public Company() {}
	
	public Company( Long companyId ) {
		super();
		this.setId( companyId );
	}

	public Company(boolean active) {
		this.setActive( active );
	}

	public String getSocialName() {
		return socialName;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}	
}
