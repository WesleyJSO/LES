export default class CompanyValidator {
  socialNameRules (socialName) {
    if (!socialName) {
      return ['Razão Social deve ser preenchido!']
    }
  }
  tradingNameRules (tradingName) {
    if (!tradingName) {
      return ['Nome Fantasia deve ser preenchido!']
    }
  }
  cnpjRules (cnpj) {
    if (!cnpj) {
      return ['CNPJ deve ser preenchido!']
    } else if (!/^[\d]{14}$/.test(cnpj)) {
      return ['CNPJ deve conter 14 números!']
    } else if (!this.verifyKnownInvalid(cnpj)) {
      return ['CNPJ deve ser válido!']
    } else if (!this.verifyUnknownInvalid(cnpj)) {
      return ['CNPJ inválido!']
    }
  }
  stateRegistrationRules (stateRegistration) {
    if (!stateRegistration) {
      return ['Inscrição Estadual deve ser preenchida!']
    } else if (!/^[\d]{12}$/.test(stateRegistration)) {
      return ['Inscrição Estadual deve conter 12 números!']
    }
  }
  verifyKnownInvalid (cnpj) {
    if (cnpj === '00000000000000' ||
    cnpj === '11111111111111' ||
    cnpj === '22222222222222' ||
    cnpj === '33333333333333' ||
    cnpj === '44444444444444' ||
    cnpj === '55555555555555' ||
    cnpj === '66666666666666' ||
    cnpj === '77777777777777' ||
    cnpj === '88888888888888' ||
    cnpj === '99999999999999') {
      return false
    } else return true
  }
  verifyUnknownInvalid (cnpj) {
    var cnpjLength = cnpj.length - 2
    var numbers = cnpj.substring(0, cnpjLength)
    var digits = cnpj.substring(cnpjLength)
    var sum = 0
    var pos = cnpjLength - 7
    for (let i = cnpjLength; i >= 1; i--) {
      sum += numbers.charAt(cnpjLength - i) * pos--
      if (pos < 2) {
        pos = 9
      }
    }
    var result = sum % 11 < 2 ? 0 : 11 - sum % 11
    if (result.toString() !== digits.charAt(0)) {
      return false
    }
    cnpjLength = cnpjLength + 1
    numbers = cnpj.substring(0, cnpjLength)
    sum = 0
    pos = cnpjLength - 7
    for (let i = cnpjLength; i >= 1; i--) {
      sum += numbers.charAt(cnpjLength - i) * pos--
      if (pos < 2) {
        pos = 9
      }
    }
    result = sum % 11 < 2 ? 0 : 11 - sum % 11
    if (result.toString() !== digits.charAt(1)) {
      return false
    }
    return true
  }
}
