package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class User extends Person {

	@Embedded
	private Login login;
	private String email;
	
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

	
	// constructors
	public User () {}

	public User(Long userId ) {
		this.setId( userId );
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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
}
