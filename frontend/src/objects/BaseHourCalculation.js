export default class BaseHourCalculation {
  constructor (baseHourCalculationId, hourType, workload, expirationDate, salary) {
    this.id = baseHourCalculationId
    this.hourType = hourType
    this.workload = workload
    this.expirationDate = expirationDate
    this.salary = salary
  }
}
