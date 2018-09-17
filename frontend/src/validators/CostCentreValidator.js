export default class CostCentreValidator {
  nameRules (name) {
    if (!name) {
      return ['Informe um Nome!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(name)) {
      return ['Informe um Nome Válido!']
    } else if (name.length <= 3 || length >= 20) {
      return ['Informe um nome Válido!']
    }
  }
  codeRules (code) {
    if (!code) {
      return ['Informe um Código!']
    }
  }
}
