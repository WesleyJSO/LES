package br.com.les.backend.entity;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Usuario extends EntidadeDominio {

	private static final long serialVersionUID = 7031669546742429620L;

	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="usuario_sequence" )
	@SequenceGenerator( name="usuario_sequence", sequenceName="USUARIO_SEQ", allocationSize=0 )
	private Long idUsuario;
	private String nome;
	private String email;
	
	@OneToOne( cascade=CascadeType.ALL )
	@JoinColumn( nullable=false )
	private Login login;
	
	@ManyToMany( cascade=CascadeType.ALL )
	List< Role > roles;
	
	public Usuario() {}
	
	public Usuario( String nome, List< Role > roles ) {
		this.nome = nome;
		this.roles = roles;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario( Long idUsuario ) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles( List<Role> roles ) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
