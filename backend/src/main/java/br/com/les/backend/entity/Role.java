package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Role extends DomainEntity {

	private String roleName;

	@ManyToMany(mappedBy="roleList")
	private List<User> userList;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
	
