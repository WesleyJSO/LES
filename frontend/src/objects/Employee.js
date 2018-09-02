export default class Employee {
  constructor (id, name, email, lastName, login, telephoneList, pis, entryCompanyDate, manager, baseHourCalculationList) {
    this.id = id
    this.name = name
    this.email = email
    this.lastName = lastName
    this.login = login
    this.telephoneList = telephoneList
    this.pis = pis
    this.entryCompanyDate = entryCompanyDate
    this.manager = manager
    this.baseHourCalculationList = baseHourCalculationList
  }
}
