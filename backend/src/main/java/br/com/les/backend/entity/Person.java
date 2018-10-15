package br.com.les.backend.entity;

import javax.persistence.Entity;

@Entity
public abstract class Person extends DomainEntity {
	
	private String name;
	private String lastName;
	
	
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
