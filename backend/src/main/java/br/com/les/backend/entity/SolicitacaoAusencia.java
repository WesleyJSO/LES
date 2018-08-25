package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class SolicitacaoAusencia extends Solicitacao {

	private static final long serialVersionUID = -5240926882445795466L;

	
	private Date dataAusencia;
	private Date dataRetorno;
	private byte[] anexo;
	
	
	public Date getDataAusencia() {
		return dataAusencia;
	}
	public void setDataAusencia(Date dataAusencia) {
		this.dataAusencia = dataAusencia;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	public byte[] getAnexo() {
		return anexo;
	}
	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}
}
