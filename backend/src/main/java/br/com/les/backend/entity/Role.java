package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Role extends EntidadeDominio {

	private static final long serialVersionUID = -1322638493619055579L;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
	
