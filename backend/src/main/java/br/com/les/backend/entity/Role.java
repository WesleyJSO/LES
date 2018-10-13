package br.com.les.backend.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Role extends DomainEntity {

	private static final long serialVersionUID = -1322638493619055579L;

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
	
