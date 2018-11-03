package br.com.les.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Parameter extends DomainEntity {

	private Double overtimePercentage;
	private Double nightOvertimePercentage;
	private Double weekEndOvertimePercentage;
	private Integer retroactiveAppointmentLimitTime;
	private Integer relocationRequestLimitTime; // limit to change work days
	private Integer bankCompensationLimitTime; // limit in months
	private LocalDateTime endDate;
	
	@OneToOne()
	@JoinColumn(name = "over_time_id")
	private HourType overTime;
	
	@OneToOne()
	@JoinColumn(name = "comp_time_id")
	private HourType compTime;
	
	@OneToOne()
	@JoinColumn(name = "both_id")
	private HourType bothTypes;
	
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
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public HourType getOverTime() {
		return overTime;
	}
	public void setOverTime(HourType overTime) {
		this.overTime = overTime;
	}
	public HourType getCompTime() {
		return compTime;
	}
	public void setCompTime(HourType compTime) {
		this.compTime = compTime;
	}
	public HourType getBothTypes() {
		return bothTypes;
	}
	public void setBothTypes(HourType bothTypes) {
		this.bothTypes = bothTypes;
	}	
}