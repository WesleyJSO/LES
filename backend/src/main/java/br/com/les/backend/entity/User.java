package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
public class User extends DomainEntity {

	private String email;
	private String name;
	private String lastName;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
    	name="USER_ROLE", 
        joinColumns={@JoinColumn(name="USER_ID")}, 
        inverseJoinColumns={@JoinColumn(name="ROLE_ID")}
    )
	private List< Role > roleList;
	
	@OneToMany( mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< LogAction > logActionList;
	
	@OneToMany( mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true )
	@JsonManagedReference
	private List< Telephone > telephoneList;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<LogAction> getLogActionList() {
		return logActionList;
	}

	public void setLogActionList(List<LogAction> logActionList) {
		this.logActionList = logActionList;
	}

	public List< Telephone > getTelephoneList() {
		return telephoneList;
	}

	public void setTelephoneList(List< Telephone > telephoneList) {
		this.telephoneList = telephoneList;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
