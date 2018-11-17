const minMonths = [4, 6, 9, 11]
export default {
  /**
* @description check the leap year
* @param {Number} year
* @return {Boolean}
*/
  isLeapYear (year) {
    return (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0
  },
  /**
   * @description get target date  or today timestamp
   * @param {String} date
   * @return {Number}
   */
  getTimestamp (date) {
    return date ? new Date(date).getTime() : new Date().getTime()
  },
  /**
  * @description conver date by lang
  * @param {String} year
  * @param {String} month
  * @param {String} day
  * @return {String}
  */
  splicingDate ({ year, month, day } = {}) {
    day = day > 9 ? day : '0' + day
    month = month > 9 ? month : '0' + month
    return `${year}-${month}-${day}`
  },
  /**
 * @description get the total days in the month
 * @param {Number} year
 * @param {Number} month
 * @return {Number} totalDays
 */
  getTotalDays (year, month) {
    let totalDays
    if (month === 2) {
      totalDays = this.isLeapYear(year) ? 29 : 28
    } else {
      totalDays = minMonths.includes(month) ? 30 : 31
    }
    return totalDays
  },
  /**
   * @description get the total days in the month
   * @param {Number} year
   * @param {Number} month
   * @return {Array} days
   */
  handleDays (year, month, markers) {
    let days = []
    const totalDays = this.getTotalDays(year, month)
    for (let i = 0; i < totalDays; i++) {
      const day = i + 1
      const date = this.splicingDate({ year, month, day })
      const dayObj = {
        day: day,
        date: date,
        isFutureDay:
          this.getTimestamp() < this.getTimestamp(date)
      }

      // add marker
      markers && markers.map(item => {
        if (dayObj.date === item.date) {
          dayObj['className'] = item.className
        }
      })
      days.push(dayObj)
    }
    return days
  },
  /**
 * @description init month
 */
  initCalendar ({ year, month } = {}, markers) {
    const prevMonth = month === 1 ? 12 : month - 1
    const nextMonth = month === 12 ? 1 : month + 1

    const currentMonthAllDays = this.handleDays(year, month, markers)
    let prevMonthDays = this.handleDays(year, prevMonth, markers)
    let nextMonthDays = this.handleDays(year, nextMonth, markers)

    // get the first day and the last day of the month is the day of the week
    const firstDay = new Date(currentMonthAllDays[0].date).getDay() + 1
    const lastDay = new Date(
      currentMonthAllDays[currentMonthAllDays.length - 1].date
    ).getDay()

    // concat prev month and next month
    const prevMonthFewDays = prevMonthDays.splice(
      prevMonthDays.length - firstDay,
      prevMonthDays.length - 1
    )
    const nextMonthFewDays = nextMonthDays.splice(0, 7 - (lastDay + 1))

    prevMonthFewDays.map(item => { item['isOtherMonthDay'] = true })
    nextMonthFewDays.map(item => { item['isOtherMonthDay'] = true })
    return [
      ...prevMonthFewDays,
      ...currentMonthAllDays,
      ...nextMonthFewDays
    ]
  }
}
