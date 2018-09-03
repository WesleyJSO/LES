export default class Employee {
  constructor (id, name, email, lastName, login, telephoneList, pis, joiningDate, manager, baseHourCalculation) {
    this.id = id
    this.name = name
    this.email = email
    this.lastName = lastName
    this.login = login
    this.telephoneList = telephoneList
    this.pis = pis
    this.joiningDate = joiningDate
    this.manager = manager
    this.baseHourCalculation = baseHourCalculation
  }
}
