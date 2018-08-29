package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class LogAcao extends EntidadeDominio {

	private static final long serialVersionUID = -5136333151680089735L;

	private String acao;
	private Date dataAcao;
	
	@ManyToOne( fetch=FetchType.LAZY )
	private Usuario usuario;
	
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataAcao() {
		return dataAcao;
	}
	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
	}	
}
