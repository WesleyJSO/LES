package br.com.les.backend.entity;

public abstract class Person extends DomainEntity {
	
	private static final long serialVersionUID = -7587779564393226610L;
	
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
