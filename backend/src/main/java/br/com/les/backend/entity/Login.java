package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Login extends EntidadeDominio {

	private static final long serialVersionUID = 2225388166302371888L;
	
	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="login_sequence" )
	@SequenceGenerator( name="login_sequence", sequenceName="LOGIN_SEQ", allocationSize=0 )
	private Long idLogin;
	private String nomeLogin;
	private String senha;
	private Date dataCriacao;
	private Boolean ativo;
	
	// TODO @OneToOne using fetch type LAZY doesn't work, read more about instrumentation and no proxy to solve this problem
	@OneToOne( mappedBy="login", fetch=FetchType.LAZY )
	@LazyToOne( LazyToOneOption.NO_PROXY ) 
	private Usuario usuario;
	
	public Long getIdLogin() {
		return idLogin;
	}
	public void setIdLogin( Long idLogin ) {
		this.idLogin = idLogin;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha( String senha ) {
		this.senha = senha;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao( Date dataCriacao ) {
		this.dataCriacao = dataCriacao;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo( Boolean ativo ) {
		this.ativo = ativo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}
	public String getNomeLogin() {
		return nomeLogin;
	}
	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}
}
