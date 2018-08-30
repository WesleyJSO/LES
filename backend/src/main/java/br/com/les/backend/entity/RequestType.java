package br.com.les.backend.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class RequestType extends DomainEntity {

	private static final long serialVersionUID = 7549530991131057672L;

	private String description;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
