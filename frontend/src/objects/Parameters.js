export default class Parameters {
  constructor (id, overtime, nightOvertime, weekEndOvertime, retroactiveAppointment, relocationRequest, bankCompensation,
    hourType, hoursLimit, overtimeTypeLimit, bankOfHoursTypeLimit) {
    this.id = id
    this.overtime = overtime
    this.nightOvertime = nightOvertime
    this.weekEndOvertime = weekEndOvertime
    this.retroactiveAppointment = retroactiveAppointment
    this.relocationRequest = relocationRequest
    this.bankCompensation = bankCompensation
    this.hourType = hourType
    this.hoursLimit = hoursLimit
    this.overtimeTypeLimit = overtimeTypeLimit
    this.bankOfHoursTypeLimit = bankOfHoursTypeLimit
  }
}
