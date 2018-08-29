package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Apontamento extends EntidadeDominio {

	private static final long serialVersionUID = -5608859856328862142L;

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
}
