export default class BaseHourCalculation {
  constructor (baseHourCalculationId, hourType, workload, effectiveDate, salary) {
    this.baseHourCalculationId = baseHourCalculationId
    this.hourType = hourType
    this.workload = workload
    this.effectiveDate = effectiveDate
    this.salary = salary
  }
}
