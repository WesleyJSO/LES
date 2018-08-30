package br.com.les.backend.entity;

import java.util.Date;


public abstract class Person extends DomainEntity {
	
	private static final long serialVersionUID = -7587779564393226610L;
	
	private String name;
	private String lastName;
	private Date birthDate;
	
	
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
