package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Role extends DomainEntity {

	private static final long serialVersionUID = -1322638493619055579L;

	private String roleName;

	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="user_id" )
	private User user;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
	
