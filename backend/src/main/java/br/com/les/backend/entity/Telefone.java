package br.com.les.backend.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Telefone extends EntidadeDominio {

	private static final long serialVersionUID = 7779619258535476494L;

	@EmbeddedId
	private TelefoneId TelefoneId;
	private String tipo;
	
	public TelefoneId getTelefoneId() {
		return TelefoneId;
	}
	public void setTelefoneId(TelefoneId telefoneId) {
		TelefoneId = telefoneId;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
