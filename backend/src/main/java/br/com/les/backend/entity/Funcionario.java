package br.com.les.backend.entity;
 
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Funcionario extends Usuario {

	private static final long serialVersionUID = 7031669546742429620L;

	
	private String pis;
	private Date dataIngressoEmpresa;
	
	@ManyToOne( cascade=CascadeType.ALL)
	@JoinColumn( name="id_gestor" )
	private Usuario gestor;

	@OneToMany( cascade=CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Solicitacao > listaSolicitacao;
	
	@ManyToMany( mappedBy="listaColaboradoresNotificados", fetch=FetchType.LAZY )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Solicitacao > listaSolicitacaoColaboradoresNotificados;
	
	@OneToMany( cascade=CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< Apontamento > listaApontamento;
	
	@OneToOne( mappedBy="funcionario", fetch=FetchType.LAZY )
	private BaseCalculoHoras baseCalculoHoras;
	
	public BaseCalculoHoras getBaseCalculoHoras() {
		return baseCalculoHoras;
	}

	public void setBaseCalculoHoras(BaseCalculoHoras baseCalculoHoras) {
		this.baseCalculoHoras = baseCalculoHoras;
	}

	@OneToMany( cascade=CascadeType.ALL )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List< BancoMensal > listaBancoMensal;

	public Funcionario() {}
	
	public Funcionario(Long idFuncionario ) {
		this.setId( idFuncionario );
	}

	public Date getDataIngressoEmpresa() {
		return dataIngressoEmpresa;
	}

	public void setDataIngressoEmpresa(Date dataIngressoEmpresa) {
		this.dataIngressoEmpresa = dataIngressoEmpresa;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Usuario getGestor() {
		return gestor;
	}

	public void setGestor(Usuario gestor) {
		this.gestor = gestor;
	}

	public List<Solicitacao> getListaSolicitacao() {
		return listaSolicitacao;
	}

	public void setListaSolicitacao(List<Solicitacao> listaSolicitacao) {
		this.listaSolicitacao = listaSolicitacao;
	}

	public List<Apontamento> getListaApontamento() {
		return listaApontamento;
	}

	public void setListaApontamento(List<Apontamento> listaApontamento) {
		this.listaApontamento = listaApontamento;
	}

	public List<BancoMensal> getListaBancoMensal() {
		return listaBancoMensal;
	}

	public void setListaBancoMensal(List<BancoMensal> listaBancoMensal) {
		this.listaBancoMensal = listaBancoMensal;
	}

	public List< Solicitacao > getListaSolicitacaoColaboradoresNotificados() {
		return listaSolicitacaoColaboradoresNotificados;
	}

	public void setListaSolicitacaoColaboradoresNotificados(List< Solicitacao > listaSolicitacaoColaboradoresNotificados) {
		this.listaSolicitacaoColaboradoresNotificados = listaSolicitacaoColaboradoresNotificados;
	}
}
