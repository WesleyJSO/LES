package br.com.les.backend.entity;

import javax.persistence.Entity; 
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
public class Telephone extends DomainEntity {

	private String type;
	private String number;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="user_id" )
	@JsonBackReference
	private Employee user;

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}
}
