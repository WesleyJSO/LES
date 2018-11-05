import moment from 'moment'

export default class DateHelper {
  static diffDates (date1, date2) {
    date1 = moment(date1, 'dd/MM/yyyy')
    date2 = moment(date2, 'dd/MM/yyyy')
    console.log(date1.startOf('day'))
    console.log(date2.startOf('day'))
    return date2.diff(date1, 'days')
  }
  static formatShortDate (date) {
    var dateVar = new Date(date.substring(0, 10).replace(/-/g, '/'))
    var dateString = (dateVar.getUTCDate() < 10 ? '0' + dateVar.getUTCDate() : dateVar.getUTCDate()) + '/'
    dateString += (dateVar.getUTCMonth() + 1) < 10 ? '0' + (dateVar.getUTCMonth() + 1) : (dateVar.getUTCMonth() + 1)
    dateString += '/' + dateVar.getFullYear()
    return dateString
  }
  static formatISODate (date) {
    var dateVar2 = date.split('/')
    return new Date(dateVar2[2] + '-' + dateVar2[1] + '-' + dateVar2[0])
  }
}
