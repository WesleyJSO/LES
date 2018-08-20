package br.com.les.backend.entity;

import javax.persistence.Embeddable;

@Embeddable
public class TelefoneId extends EntidadeDominio {

	private static final long serialVersionUID = -6824714275508181714L;

	private long idUsuario;
	private String numero;
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
