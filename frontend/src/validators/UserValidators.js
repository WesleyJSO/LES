export default class UserValidators {
  email (email) {
    if (!email) {
      return ['campo E-mail deve ser preenchido!']
    } else if (!/.+@.+.+\..+/.test(email)) {
      return ['Formato de e-mail inválido!']
    }
  }
  password (password) {
    if (!password) {
      return ['campo Senha deve ser preenchido!']
    } else if (password.length <= 3) {
      return ['campo Senha deve conter no mínimo 3 caracteres!']
    }
  }
  nameRules (name) {
    if (!name) {
      return ['Informe um Nome!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(name)) {
      return ['Informe um Nome Válido!']
    } else if (name.length <= 3 || length >= 20) {
      return ['Informe um nome Válido!']
    }
    return []
  }
  lastNameRules (lastName) {
    if (!lastName) {
      return ['Informe um Sobrenome!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(lastName)) {
      return ['Informe um Sobrenome Válido!']
    } else if (lastName.length <= 3 || lastName.length >= 20) {
      return ['Informe um Sobrenome Válido!']
    }
    return []
  }
  emailRules (email) {
    if (!email) {
      return ['Informe um e-mail!']
    } else if (!/.+@.+.+\..+/.test(email)) {
      return ['Informe um e-mail válido!!']
    }
    return []
  }
  salaryRules (salary) {
    if (!salary) {
      return ['Informe um Salário!']
    } else if (salary.length <= 0) {
      return ['Informe um salário Válido!']
    }
    return []
  }
  workloadRules (workload) {
    if (!workload) {
      return ['Informe uma Carga Horária!']
    } else if (workload > 8 || workload <= 0) {
      return ['Informe uma Carga Horária Válida!']
    }
    return []
  }
  managerRules (manager) {
    if (!manager) {
      return ['Informe um Gestor!']
    } else if (manager.length <= 2 || manager.length >= 20) {
      return ['Informe um Gestor Válido!']
    }
    return []
  }
  phoneRule1 (thelephoneList) {
    if (!thelephoneList[0].number && !thelephoneList[1].number && !thelephoneList[2].number) {
      return ['Informe pelo menos um Telefone!']
    } else if (thelephoneList[1].number || thelephoneList[2].number) {
      return []
    } else if (thelephoneList[0].number.length >= 10) {
      return ['Informe um Telefone Válido!']
    } else if (thelephoneList[0].number.length <= 7) {
      return ['Informe um Telefone Válido']
    }
    return []
  }
  phoneRule2 (thelephoneList) {
    if (!thelephoneList[0].number && !thelephoneList[1].number && !thelephoneList[2].number) {
      return ['Informe pelo menos um Telefone!']
    } else if (thelephoneList[0].number || thelephoneList[2].number) {
      return []
    } else if (thelephoneList[1].number.length >= 10) {
      return ['Informe um Telefone Válido!']
    } else if (thelephoneList[1].number.length <= 7) {
      return ['Informe um Telefone Válido']
    }
    return []
  }
  phoneRule3 (thelephoneList) {
    if (!thelephoneList[0].number && !thelephoneList[1].number && !thelephoneList[2].number) {
      return ['Informe pelo menos um Telefone!']
    } else if (thelephoneList[0].number || thelephoneList[1].number) {
      return []
    } else if (thelephoneList[2].number.length >= 10) {
      return ['Informe um Telefone Válido!']
    } else if (thelephoneList[2].number.length <= 7) {
      return ['Informe um Telefone Válido']
    }
    return []
  }
  loginRules (login) {
    if (!login) {
      return ['Informe um Login!']
    } else if (!/[^0-9]+[a-zA-Z]/.test(login)) {
      return ['Informe um Login Válido!']
    } else if (login.length <= 2 || login.length > 20) {
      return ['Informe um Login com até 20 Carácteres!']
    }
    return []
  }
  pisRules (pis) {
    if (!pis) {
      return ['Informe um número PIS/PASESP!']
    } else if (pis.length !== 9) {
      return ['Informe um número PIS/PASESP Válido!']
    }
    return []
  }
  passwordRule (password, passwordValidation) {
    if (!password && !passwordValidation) {
      return ['Informe uma Senha!']
    } else if ((!password && passwordValidation) || (password && !passwordValidation)) {
      return ['As Senhas informadas são divergentes!']
    }
    return []
  }
  passwordValidationRule (password, passwordValidation) {
    if (!password && !passwordValidation) {
      return ['Informe uma Senha!']
    } else if ((!password && passwordValidation) || (password && !passwordValidation)) {
      return ['As Senhas informadas são divergentes!']
    } else if (password !== passwordValidation) {
      return ['As Senhas informadas são divergentes!']
    }
    return []
  }
}
