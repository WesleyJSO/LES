export default class AddressValidators {
  streetRules (street) {
    if (!street) {
      return ['Rua deve ser preenchida!']
    }
  }
  numberRules (number) {
    if (!number) {
      return ['Número deve ser preenchido!']
    } else if (!/^[\d]+$/.test(number)) {
      return ['Campo Número deve conter apenas números!']
    }
  }
  cityRules (city) {
    if (!city) {
      return ['Cidade deve ser preenchida!']
    }
  }
  stateRules (state) {
    if (!state) {
      return ['Estado deve ser preenchido!']
    }
  }
  zipCodeRules (zipCode) {
    if (!zipCode) {
      return ['CEP deve ser preenchido!']
    } else if (!/^[0-9]{8}$/.test(zipCode)) {
      return ['CEP deve conter 8 numeros!']
    }
  }
}
