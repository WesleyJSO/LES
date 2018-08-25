package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Inheritance( strategy=InheritanceType.TABLE_PER_CLASS )
public abstract class Solicitacao extends EntidadeDominio {

	private static final long serialVersionUID = 9026354384897022658L;


	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="solicitacao_sequence" )
	@SequenceGenerator( name="solicitacao_sequence", sequenceName="SOLICITACAO_SEQ", allocationSize=0 )
	private Long id;
	private String descricao;
	private boolean aceite;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_funcionario" )
	private Usuario funcionario;
		
	@ManyToMany( cascade={ CascadeType.MERGE, CascadeType.PERSIST } )
	private List< Funcionario > listaColaboradoresNotificados;

	@OneToOne( fetch=FetchType.LAZY )
	private TipoSolicitacao tipoSolicitacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAceite() {
		return aceite;
	}

	public void setAceite(boolean aceite) {
		this.aceite = aceite;
	}

	public Usuario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoSolicitacao getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(TipoSolicitacao tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public List<Funcionario> getListaColaboradoresNotificados() {
		return listaColaboradoresNotificados;
	}

	public void setListaColaboradoresNotificados(List<Funcionario> listaColaboradoresNotificados) {
		this.listaColaboradoresNotificados = listaColaboradoresNotificados;
	}
}
