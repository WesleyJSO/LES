package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@Entity
@SuppressWarnings("serial")
public class Role extends DomainEntity implements GrantedAuthority{
	
	private String roleName;
	private String role;

	@ManyToMany(mappedBy="roleList", fetch=FetchType.LAZY)
	private List<User> userList;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return getId().toString();
	}
	
}
	
