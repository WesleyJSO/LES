package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class CostCentre extends DomainEntity {
	
	private String name;
	private String code;
	
	@OneToMany(mappedBy = "costCentre")
	private List < Employee > employees;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public CostCentre () {
		
	}
	
	public CostCentre ( Long id ) {
		this.setId( id );
	}
	
	public CostCentre(String name, String code, String nickName, List<Employee> employees) {
		super();
		this.name = name;
		this.code = code;
		this.employees = employees;
	}
	
	
	
}
