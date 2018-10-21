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

	public static final String USER_ROLE = "USER";
	public static final String ADMIN_ROLE = "ADMIN";
	
	private String roleName;

	@ManyToMany(mappedBy="roleList", fetch=FetchType.LAZY)
	private List<User> userList;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return getId().toString();
	}
	
}
	
