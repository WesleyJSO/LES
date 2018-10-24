package br.com.les.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Parameter extends DomainEntity {

	private Double overtimePercentage;
	private Double nightOvertimePercentage;
	private Double weekEndOvertimePercentage;
	private Integer retroactiveAppointmentLimitTime;
	private Integer relocationRequestLimitTime;
	private Integer bankCompensationLimitTime;
	private HourType hourType;
	private Integer hoursLimit;
	private Integer overtimeTypeLimit;
	private Integer bankedHourTypeLimit;
	private HourType firstTypeApplied;
	private LocalDateTime endDate;

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
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public HourType getHourType() {
		return hourType;
	}

	public void setHourType(HourType hourType) {
		this.hourType = hourType;
	}

	public HourType getFirstTypeApplied() {
		return firstTypeApplied;
	}

	public void setFirstTypeApplied(HourType firstTypeApplied) {
		this.firstTypeApplied = firstTypeApplied;
	}
}