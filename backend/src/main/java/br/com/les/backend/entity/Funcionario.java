package br.com.les.backend.entity;
 
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Funcionario extends Usuario {

	private static final long serialVersionUID = 7031669546742429620L;

	
	private Date dataIngressoEmpresa;
	private String pis;
	
	@ManyToOne( cascade={ CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH } )
	private Usuario gestor;

	
	public Date getDataIngressoEmpresa() {
		return dataIngressoEmpresa;
	}

	public void setDataIngressoEmpresa(Date dataIngressoEmpresa) {
		this.dataIngressoEmpresa = dataIngressoEmpresa;
	}

	public Usuario getGestor() {
		return gestor;
	}

	public void setGestor(Usuario gestor) {
		this.gestor = gestor;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}
}
