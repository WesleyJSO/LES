export default class RequestValidator {
  startDate (startDate) {
    let start = new Date(startDate)
    if (!startDate) {
      return ['Informe a Data Alvo da solicitação!']
    } else if (start < new Date()) {
      return ['A Data Alvo deve ser maior que a data atual!']
    }
  }
  endDate (startDate, endDate) {
    let start = new Date(startDate)
    let end = new Date(endDate)
    if (!startDate) {
      return ['Informe a data Alvo!']
    }
    if (start >= end) {
      return ['A Data Final deve ser maior que a Data Alvo!']
    }
  }
  description (description) {
    if (!description) {
      return ['Informe uma descrição para a solicitação!']
    } else if (description.length < 10) {
      return ['Informe uma descrição válida!!']
    }
  }
}
