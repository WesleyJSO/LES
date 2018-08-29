package br.com.les.backend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class BaseCalculoHoras extends EntidadeDominio {

	private static final long serialVersionUID = 5309951868771462074L;

	private Short tipoHora = -1; // 0 - comp time, 1 - extra time, 2 - both
	private Double cargaHoraria;
	private Date dataVigencia;
	private BigDecimal salario;

	@OneToOne()
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	public short getTipoHora() {
		return tipoHora;
	}

	public void setTipoHora(short tipoHora) {
		this.tipoHora = tipoHora;
	}

	public Double getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Double cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setTipoHora(Short tipoHora) {
		this.tipoHora = tipoHora;
	}

}
