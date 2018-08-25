package br.com.les.backend.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
@Inheritance( strategy=InheritanceType.TABLE_PER_CLASS )
public abstract class Pessoa extends EntidadeDominio {
	
	private static final long serialVersionUID = -7587779564393226610L;
	
	
	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="pessoa_sequence" )
	@SequenceGenerator( name="pessoa_sequence", sequenceName="PESSOA_SEQ", allocationSize=0 )
	private Long id;
	private String nome;
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
