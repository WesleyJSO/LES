package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class LogAcao extends EntidadeDominio {

	private static final long serialVersionUID = -5136333151680089735L;

	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="log_acao_sequence" )
	@SequenceGenerator( name="log_acao_sequence", sequenceName="LOG_ACAO_SEQ", allocationSize=0 ) 
	private Long idLogAcao;
	private String acao;
	private Date dataAcao;
	
	@ManyToOne( fetch=FetchType.LAZY )
	private Usuario usuario;
	
	
	public long getIdLogAcao() {
		return idLogAcao;
	}
	public void setIdLogAcao(long idLogAcao) {
		this.idLogAcao = idLogAcao;
	}
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
