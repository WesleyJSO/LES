package br.com.les.backend.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class TipoSolicitacao extends EntidadeDominio {

	private static final long serialVersionUID = 7549530991131057672L;

	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
