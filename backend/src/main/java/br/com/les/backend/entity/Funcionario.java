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

import org.springframework.stereotype.Component;

@Component
@Entity
public class Funcionario extends Usuario {

	private static final long serialVersionUID = 7031669546742429620L;

	
	private Date dataIngressoEmpresa;
	private String pis;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_gestor" )
	private Usuario gestor;

	@OneToMany( cascade=CascadeType.ALL )
	private List< Solicitacao > listaSolicitacao;
	
	@ManyToMany( mappedBy="listaColaboradoresNotificados", fetch=FetchType.LAZY )
	private List< Solicitacao > listaSolicitacoes;
	
	@OneToMany( cascade=CascadeType.ALL )
	private List< Apontamento > listaApontamento;
	
	@OneToMany( cascade=CascadeType.ALL )
	private List< BaseCalculoHoras > listaBaseCalculoHoras;
	
	@OneToMany( cascade=CascadeType.ALL )
	private List< BancoMensal > listaBancoMensal;

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

	public List<Solicitacao> getListaSolicitacoes() {
		return listaSolicitacoes;
	}

	public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
		this.listaSolicitacoes = listaSolicitacoes;
	}

	public List<Apontamento> getListaApontamento() {
		return listaApontamento;
	}

	public void setListaApontamento(List<Apontamento> listaApontamento) {
		this.listaApontamento = listaApontamento;
	}

	public List<BaseCalculoHoras> getListaBaseCalculoHoras() {
		return listaBaseCalculoHoras;
	}

	public void setListaBaseCalculoHoras(List<BaseCalculoHoras> listaBaseCalculoHoras) {
		this.listaBaseCalculoHoras = listaBaseCalculoHoras;
	}

	public List<BancoMensal> getListaBancoMensal() {
		return listaBancoMensal;
	}

	public void setListaBancoMensal(List<BancoMensal> listaBancoMensal) {
		this.listaBancoMensal = listaBancoMensal;
	}
}
