
export default class TimeHelper {
  static formatTime (time) {
    if (time) {
      return time.substring(0, 5)
    }
  }
}
