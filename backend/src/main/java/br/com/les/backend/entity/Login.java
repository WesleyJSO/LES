package br.com.les.backend.entity;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Login extends DomainEntity {

	private static final long serialVersionUID = 2225388166302371888L;

	@Transient
	private String passwordValidator;
	private String password;
	
	
	public String getPasswordValidator() {
		return passwordValidator;
	}
	public void setPasswordValidator(String passwordValidator) {
		this.passwordValidator = passwordValidator;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
