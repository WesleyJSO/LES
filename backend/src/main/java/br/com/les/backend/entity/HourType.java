package br.com.les.backend.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Controller;

@Controller
@Entity
public class HourType extends DomainEntity {
	
	static final String BANKEDHOURS = "Banco de horas";
	static final String EXTRATIME = "Hora extra";
	static final String BOTH = "Ambos";
	
	private Boolean both;
	private String first;
	private Integer quantityFirst;
	private String second;
	private Integer quantitySecond;
	
	public Boolean getBoth() {
		return both;
	}
	public void setBoth(Boolean both) {
		this.both = both;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public Integer getQuantityFirst() {
		return quantityFirst;
	}
	public void setQuantityFirst(Integer quantityFirst) {
		this.quantityFirst = quantityFirst;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public Integer getQuantitySecond() {
		return quantitySecond;
	}
	public void setQuantitySecond(Integer quantitySecond) {
		this.quantitySecond = quantitySecond;
	}
}