import moment from 'moment'

export default class DateHelper {
  static diffDates (date1, date2) {
    date1 = moment(date1, 'dd/MM/yyyy')
    date2 = moment(date2, 'dd/MM/yyyy')
    return date2.diff(date1, 'days')
  }
}
