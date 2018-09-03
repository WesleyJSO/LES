export default class BaseHourCalculationValidators {
  hourTypeRules (hourType) {
    if (!hourType) {
      return ['Tipo de horas deve ser definido!']
    } else if (hourType < 0 || hourType > 2) {
      return ['Tipo de horas deve ser, banco de horas, hora extras ou ambos, selecione um!']
    }
  }
  workloadRules (workload) {
    if (!workload) {
      return ['Carga Horária deve ser definida!']
    } else if (workload > 200 || workload < 0) {
      return ['Carga Horária inválida!']
    }
  }
  salaryRules (salary) {
    if (!salary) {
      return ['Salário deve ser definido!']
    } else if (salary < 0) {
      return ['Salário inválido!']
    }
  }
  expirationDateRules (expirationDate) {
    if (!expirationDate) {
      return ['Data de expiração da regra atual deve ser definida!']
    }
  }
}
