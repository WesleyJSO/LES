package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class LogAction extends DomainEntity {

	private static final long serialVersionUID = -5136333151680089735L;

	private String action;
	private Date actionDate;
	
	@ManyToOne( fetch=FetchType.LAZY )
	private User user;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
