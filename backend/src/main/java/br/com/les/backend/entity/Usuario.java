package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Usuario extends Pessoa {

	private static final long serialVersionUID = -2053120971761794871L;

	
	@Embedded
	private Login login;
	
	@OneToMany( cascade=CascadeType.ALL, orphanRemoval=true )
	private List< Role > listaRole;
	
	@OneToMany( mappedBy="usuario", cascade=CascadeType.ALL, orphanRemoval=true )
	private List< LogAcao > listaLogAcao;
	
	@OneToMany( cascade=CascadeType.ALL, orphanRemoval=true )
	@JoinColumn(name = "id_usuario")
	private List< Telefone > listaTelefone;

	
	// constructors
	public Usuario () {}

	public Usuario(Long idUsuario) {
		this.setId( idUsuario );
	}
	
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public List<Telefone> getListaTelefone() {
		return listaTelefone;
	}
	public void setListaTelefone(List<Telefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}
	public List< LogAcao > getListaLogAcao() {
		return listaLogAcao;
	}
	public void setListaLogAcao(List< LogAcao > listaLogAcao) {
		this.listaLogAcao = listaLogAcao;
	}
	public List<Role> getListaRole() {
		return listaRole;
	}
	public void setListaRole(List<Role> listaRole) {
		this.listaRole = listaRole;
	}
}
