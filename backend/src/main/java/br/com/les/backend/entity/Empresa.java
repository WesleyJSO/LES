package br.com.les.backend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Empresa extends EntidadeDominio {

	private static final long serialVersionUID = 2623152606711154523L;

	private String razaoSocial;
	private String nomeFatasia;
	private String cnpj;
	private String inscricaoEstadual;
	
	@ManyToMany
	private List< Funcionario > listaFuncionario;
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFatasia() {
		return nomeFatasia;
	}

	public void setNomeFatasia(String nomeFatasia) {
		this.nomeFatasia = nomeFatasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
}
