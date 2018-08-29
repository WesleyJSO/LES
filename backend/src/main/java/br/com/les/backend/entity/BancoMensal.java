package br.com.les.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class BancoMensal extends EntidadeDominio {

	private static final long serialVersionUID = -6687722120215401938L;

	private float saldo;
	private float limiteHorasMensal;
	private float limiteHorasDiario;
	private boolean limiteMensalHorasAtivo;
	private short mesDesteSaldo;
	private String descricaoMesDesteSaldo;
	private short mesLimitePagamento;
	private String descricaoMesLimitePagamento;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_funcionario" )
	private Funcionario funcionario;

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getLimiteHorasMensal() {
		return limiteHorasMensal;
	}

	public void setLimiteHorasMensal(float limiteHorasMensal) {
		this.limiteHorasMensal = limiteHorasMensal;
	}

	public float getLimiteHorasDiario() {
		return limiteHorasDiario;
	}

	public void setLimiteHorasDiario(float limiteHorasDiario) {
		this.limiteHorasDiario = limiteHorasDiario;
	}

	public boolean isLimiteMensalHorasAtivo() {
		return limiteMensalHorasAtivo;
	}

	public void setLimiteMensalHorasAtivo(boolean limiteMensalHorasAtivo) {
		this.limiteMensalHorasAtivo = limiteMensalHorasAtivo;
	}

	public short getMesDesteSaldo() {
		return mesDesteSaldo;
	}

	public void setMesDesteSaldo(short mesDesteSaldo) {
		this.mesDesteSaldo = mesDesteSaldo;
	}

	public String getDescricaoMesDesteSaldo() {
		return descricaoMesDesteSaldo;
	}

	public void setDescricaoMesDesteSaldo(String descricaoMesDesteSaldo) {
		this.descricaoMesDesteSaldo = descricaoMesDesteSaldo;
	}

	public short getMesLimitePagamento() {
		return mesLimitePagamento;
	}

	public void setMesLimitePagamento(short mesLimitePagamento) {
		this.mesLimitePagamento = mesLimitePagamento;
	}

	public String getDescricaoMesLimitePagamento() {
		return descricaoMesLimitePagamento;
	}

	public void setDescricaoMesLimitePagamento(String descricaoMesLimitePagamento) {
		this.descricaoMesLimitePagamento = descricaoMesLimitePagamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
