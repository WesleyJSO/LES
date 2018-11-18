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
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
	public static final String ROLE_MANAGER = "ROLE_MANAGER";
	
	private String roleName;
	private String role;

	@ManyToMany(mappedBy="authorities", fetch=FetchType.LAZY)
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
		return this.role;
	}
	
}
	
