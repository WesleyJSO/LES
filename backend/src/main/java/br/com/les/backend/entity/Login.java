package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Login extends EntidadeDominio {

	private static final long serialVersionUID = 2225388166302371888L;

	private String nomeLogin;
	private String senha;
	
	@Transient
	private String senhaValidacao;
	
	private Boolean ativo;

	public String getSenha() {
		return senha;
	}
	public void setSenha( String senha ) {
		this.senha = senha;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo( Boolean ativo ) {
		this.ativo = ativo;
	}
	public String getNomeLogin() {
		return nomeLogin;
	}
	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}
	public String getSenhaValidacao() {
		return senhaValidacao;
	}
	public void setSenhaValidacao(String senhaValidacao) {
		this.senhaValidacao = senhaValidacao;
	}
}
