package br.com.les.backend.entity;

import java.util.Calendar;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;


@Component
@Entity
public class Parameter extends DomainEntity {

	private static final long serialVersionUID = -5240926882445795466L;
    
	private Double overtimePercentage;
	private Double nightOvertimePercentage;
	private Double weekEndOvertimePercentage;
	private Integer retroactiveAppointmentLimitTime;
	private Integer relocationRequestLimitTime;
	private Integer bankCompensationLimitTime;
	private String hourType;
	private Integer hoursLimit;
	private Integer overtimeTypeLimit;
	private Integer bankedHourTypeLimit;
	private String firstTypeApplied;
	private Calendar endDate;
	private Boolean active;
	
	public Parameter() {}
	
	public Parameter( boolean active ) {
		this.setId(1l);
		this.active = active;
		this.overtimePercentage = 0d;
		this.nightOvertimePercentage = 0d;
		this.weekEndOvertimePercentage = 0d;
		this.retroactiveAppointmentLimitTime = 0;
		this.relocationRequestLimitTime = 0;
		this.bankCompensationLimitTime = 0;
		this.hourType = "Não cadastrado";
		this.hoursLimit = 0;
		this.overtimeTypeLimit = 0;
		this.bankedHourTypeLimit = 0;
	}
	
	public Double getOvertimePercentage() {
		return overtimePercentage;
	}
	public void setOvertimePercentage(Double overtimePercentage) {
		this.overtimePercentage = overtimePercentage;
	}
	public Double getNightOvertimePercentage() {
		return nightOvertimePercentage;
	}
	public void setNightOvertimePercentage(Double nightOvertimePercentage) {
		this.nightOvertimePercentage = nightOvertimePercentage;
	}
	public Double getWeekEndOvertimePercentage() {
		return weekEndOvertimePercentage;
	}
	public void setWeekEndOvertimePercentage(Double weekEndOvertimePercentage) {
		this.weekEndOvertimePercentage = weekEndOvertimePercentage;
	}
	public Integer getRetroactiveAppointmentLimitTime() {
		return retroactiveAppointmentLimitTime;
	}
	public void setRetroactiveAppointmentLimitTime(Integer retroactiveAppointmentLimitTime) {
		this.retroactiveAppointmentLimitTime = retroactiveAppointmentLimitTime;
	}
	public Integer getRelocationRequestLimitTime() {
		return relocationRequestLimitTime;
	}
	public void setRelocationRequestLimitTime(Integer relocationRequestLimitTime) {
		this.relocationRequestLimitTime = relocationRequestLimitTime;
	}
	public Integer getBankCompensationLimitTime() {
		return bankCompensationLimitTime;
	}
	public void setBankCompensationLimitTime(Integer bankCompensationLimitTime) {
		this.bankCompensationLimitTime = bankCompensationLimitTime;
	}
	public String getHourType() {
		return hourType;
	}
	public void setHourType(String hourType) {
		this.hourType = hourType;
	}
	public Integer getHoursLimit() {
		return hoursLimit;
	}
	public void setHoursLimit(Integer hoursLimit) {
		this.hoursLimit = hoursLimit;
	}
	public Integer getOvertimeTypeLimit() {
		return overtimeTypeLimit;
	}
	public void setOvertimeTypeLimit(Integer overtimeTypeLimit) {
		this.overtimeTypeLimit = overtimeTypeLimit;
	}
	public Integer getBankedHourTypeLimit() {
		return bankedHourTypeLimit;
	}
	public void setBankedHourTypeLimit(Integer bankOfHoursTypeLimit) {
		this.bankedHourTypeLimit = bankOfHoursTypeLimit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFirstTypeApplied() {
		return firstTypeApplied;
	}

	public void setFirstTypeApplied(String firstTypeApplied) {
		this.firstTypeApplied = firstTypeApplied;
	}
}