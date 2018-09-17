export default class Parameters {
  constructor (id, overtime, nightOvertime, weekEndOvertime, retroactiveAppointment, relocationRequest, bankCompensation,
    hourType, hoursLimit, overtimeTypeLimit, bankOfHoursTypeLimit) {
    this.id = id
    this.overtimePercentage = overtime
    this.nightOvertimePercentage = nightOvertime
    this.weekEndOvertimePercentage = weekEndOvertime
    this.retroactiveAppointmentLimitTime = retroactiveAppointment
    this.relocationRequestLimitTime = relocationRequest
    this.bankCompensationLimitTime = bankCompensation
    this.hourType = hourType
    this.hoursLimit = hoursLimit
    this.overtimeTypeLimit = overtimeTypeLimit
    this.bankedHourTypeLimit = bankOfHoursTypeLimit
  }
}
