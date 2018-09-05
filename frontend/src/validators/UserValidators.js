export default class UserValidators {
  nameRules (name) {
    if (!name) {
      return ['Informe um Nome!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(name)) {
      return ['Informe um Nome Válido!']
    } else if (name.length <= 3 || length >= 20) {
      return ['Informe um nome Válido!']
    }
  }
  lastNameRules (lastName) {
    if (!lastName) {
      return ['Informe um Sobrenome!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(lastName)) {
      return ['Informe um Sobrenome Válido!']
    } else if (lastName.length <= 3 || lastName.length >= 20) {
      return ['Informe um Sobrenome Válido!']
    }
  }
  emailRules (email) {
    if (!email) {
      return ['Informe um e-mail!']
    } else if (!/.+@.+\..+/.test(email)) {
      return ['Informe um e-mail válido!!']
    }
  }
  salaryRules (salary) {
    if (!salary) {
      return ['Informe um Salário!']
    } else if (salary.length <= 0) {
      return ['Informe um salário Válido!']
    }
  }
  workloadRules (workload) {
    if (!workload) {
      return ['Informe uma Carga Horária!']
    } else if (workload > 8 || workload <= 0) {
      return ['Informe uma Carga Horária Válida!']
    }
  }
  managerRules (manager) {
    if (!manager) {
      return ['Informe um Gestor!']
    } else if (manager.length <= 2 || manager.length >= 20) {
      return ['Informe um Gestor Válido!']
    }
  }
  phoneRule1 (number1, number2, number3) {
    if (!number1 && !number2 && !number3) {
      return ['Informe pelo menos um Telefone!']
    } else if (number1.length >= 10) {
      return ['Informe um Telefone Válido!']
    } else if (number1.length <= 7) {
      return ['Informe um Telefone Válido']
    }
  }
  phoneRule2 (number1, number2, number3) {
    if (!number1 && !number2 && !number3) {
      return ['Informe pelo menos um Telefone!']
    } else if (number2.length >= 10) {
      return ['Informe um Telefone Válido!']
    } else if (number2.length <= 7) {
      return ['Informe um Telefone Válido']
    }
  }
  phoneRule3 (number1, number2, number3) {
    if (!number1 && !number2 && !number3) {
      return ['Informe pelo menos um Telefone!']
    } else if (number3.length >= 10) {
      return ['Informe um Telefone Válido!']
    } else if (number3.length <= 7) {
      return ['Informe um Telefone Válido']
    }
  }
  loginRules (login) {
    if (!login) {
      return ['Informe um Login!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(login)) {
      return ['Informe um Login Válido!']
    } else if (login.length <= 2 || login.length > 20) {
      return ['Informe um Login com até 20 Carácteres!']
    }
  }
  pisRules (pis) {
    if (!pis) {
      return ['Informe um número PIS/PASESP!']
    } else if (pis.length !== 9) {
      return ['Informe um número PIS/PASESP Válido!']
    }
  }
  passwordRules (password) {
    if (!password) {
      return ['campo Senha deve ser preenchido!']
    } else if (password.length <= 3) {
      return ['campo Senha deve conter no mínimo 3 caracteres!']
    }
  }
  passwordValidationRules (password, passwordValidation) {
    if (!password && !passwordValidation) {
      return ['campo Senha de Confirmação deve ser preenchido!']
    } else if ((!password && passwordValidation) || (password && !passwordValidation)) {
      return ['As Senhas informadas são divergentes!']
    } else if (password !== passwordValidation) {
      return ['As Senhas informadas são divergentes!']
    }
  }
  joiningDateRules (joiningDate) {
    if (!joiningDate) {
      return ['Data de ingresso deve ser informada!']
    }
  }
}
