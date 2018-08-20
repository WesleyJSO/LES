package br.com.les.backend.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Embeddable
public class Role extends EntidadeDominio {

	private static final long serialVersionUID = -1322638493619055579L;

	/*@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="role_sequence" )
	@SequenceGenerator( name="role_sequence", sequenceName="ROLE_SEQ", allocationSize=0 ) 
	private Long idRole;*/
	private String nomeRole;

	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	/*public Long getIdRole() {
		return idRole;
	}

	public void setIdRole( Long idRole ) {
		this.idRole = idRole;
	}*/
}
	
