package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class TipoSolicitacao extends EntidadeDominio {

	private static final long serialVersionUID = 7549530991131057672L;

	
	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="tipo_solicitacao_sequence" )
	@SequenceGenerator( name="tipo_solicitacao_sequence", sequenceName="TIPO_SOLICITACAO_SEQ", allocationSize=0 )
	private Long idTipoSolicitacao;
	private String descricao;
	
	
	public Long getIdTipoSolicitacao() {
		return idTipoSolicitacao;
	}
	public void setIdTipoSolicitacao(Long idTipoSolicitacao) {
		this.idTipoSolicitacao = idTipoSolicitacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
