package br.com.les.backend.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Telefone extends EntidadeDominio {

	private static final long serialVersionUID = 7779619258535476494L;

	@EmbeddedId
	private Long idTelefone;
	private String numero;
	private String tipo;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_usuario" )
	private Usuario usuario;
	
	public Long getIdTelefone() {
		return idTelefone;
	}
	public void setIdTelefone(Long idTelefone) {
		this.idTelefone = idTelefone;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	
}
