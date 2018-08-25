package br.com.les.backend.entity;

import java.util.Date;

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
public class Apontamento extends EntidadeDominio {

	private static final long serialVersionUID = -5608859856328862142L;

	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="apontamento_sequence" )
	@SequenceGenerator( name="apontamento_sequence", sequenceName="APONTAMENTO_SEQ", allocationSize=0 ) 
	private Long idApontamento;
	private String descricao;
	private Date dataApontamentoUsuario;
	private Date dataAcessoSistema;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_funcionario" )
	private Funcionario funcionario;


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataApontamentoUsuario() {
		return dataApontamentoUsuario;
	}

	public void setDataApontamentoUsuario(Date dataApontamentoUsuario) {
		this.dataApontamentoUsuario = dataApontamentoUsuario;
	}

	public Date getDataAcessoSistema() {
		return dataAcessoSistema;
	}

	public void setDataAcessoSistema(Date dataAcessoSistema) {
		this.dataAcessoSistema = dataAcessoSistema;
	}

	public Long getIdApontamento() {
		return idApontamento;
	}

	public void setIdApontamento(Long idApontamento) {
		this.idApontamento = idApontamento;
	}
}
