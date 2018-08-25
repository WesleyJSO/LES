package br.com.les.backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class BaseCalculoHoras extends EntidadeDominio {

	private static final long serialVersionUID = 5309951868771462074L;

	
	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="base_calculo_horas_sequence" )
	@SequenceGenerator( name="base_calculo_horas_sequence", sequenceName="BASE_CALCULO_HORAS_SEQ", allocationSize=0 ) 
	private Long idBaseCalculoHoras;
	private short tipoHora; // 0 - comp time, 1 - extra time, 2 - both
	private float cargaHoraria;
	private Date dataVigencia;
	private float salario;
	
	@ManyToOne( fetch=FetchType.LAZY )
	@JoinColumn( name="id_funcionario" )
	private Funcionario funcionario;

	
	public short getTipoHora() {
		return tipoHora;
	}

	public void setTipoHora(short tipoHora) {
		this.tipoHora = tipoHora;
	}

	public float getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(float cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Date getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(Date dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Long getIdBaseCalculoHoras() {
		return idBaseCalculoHoras;
	}

	public void setIdBaseCalculoHoras(Long idBaseCalculoHoras) {
		this.idBaseCalculoHoras = idBaseCalculoHoras;
	}
	
}
