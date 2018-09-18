export default class RequestValidator {
  validateDates (hourType) {
    if (!hourType) {
      return ['Tipo de horas deve ser definido!']
    } else if (hourType < 0 || hourType > 2) {
      return ['Tipo de horas deve ser, banco de horas, hora extras ou ambos, selecione um!']
    }
  }
}
