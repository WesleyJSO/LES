package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Role extends EntidadeDominio {

	private static final long serialVersionUID = -1322638493619055579L;

	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="role_sequence" )
	@SequenceGenerator( name="role_sequence", sequenceName="ROLE_SEQ", allocationSize=0 ) 
	private Long idRole;
	private String nomeRole;

	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_usuario" )
	private Usuario usuario;
	
	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole( Long idRole ) {
		this.idRole = idRole;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
	
