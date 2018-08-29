package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class SolicitacaoTrocaApontamento extends Solicitacao {

	private static final long serialVersionUID = -6495527335471584927L;

	private Date dataAntiga;
	private Date dataNova;

	
	public Date getDataAntiga() {
		return dataAntiga;
	}
	public void setDataAntiga(Date dataAntiga) {
		this.dataAntiga = dataAntiga;
	}
	public Date getDataNova() {
		return dataNova;
	}
	public void setDataNova(Date dataNova) {
		this.dataNova = dataNova;
	}
}
