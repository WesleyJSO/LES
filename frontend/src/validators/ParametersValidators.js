export default class ParametersValidators {
  overtimeRules (overtime) {
    if (!overtime) {
      return ['Informe o valor da hora extra!']
    }
  }
  nightOvertimeRules (nightOvertime) {
    if (!nightOvertime) {
      return ['Informe o valor da hora extra noturna!']
    }
  }
  weekEndOvertimeRules (weekEndOvertime) {
    if (!weekEndOvertime) {
      return ['Informe o valor da hora extra de domingos e feriados!']
    }
  }
  retroactiveAppointmentRules (retroactiveAppointment) {
    if (!retroactiveAppointment) {
      return ['Informe o prazo de apontamento retroativo!']
    }
  }
  relocationRequestRules (relocationRequest) {
    if (!relocationRequest) {
      return ['Informe o prazo de solicitação de remanejamento!']
    }
  }
  bankCompensationRules (bankCompensationRules) {
    if (!bankCompensationRules) {
      return ['Informe o prazo para compensação do banco de horas!']
    }
  }
  hoursLimitRules (type, notSetLimit, hoursLimit) {
    if (type === 'Banco de Horas' || type === 'Hora Extra') {
      if (!notSetLimit) {
        if (!hoursLimit) {
          return ['Informe o limite de horas!']
        }
      }
    }
  }
  overtimeTypeLimitRules (type, notSetLimit, overtimeTypeLimit) {
    if (type === 'Hora Extra e Banco de Horas' && !notSetLimit) {
      if (!overtimeTypeLimit) {
        return ['Informe o limite diário de horas extras!']
      }
    }
  }
  bankOfHoursTypeLimitRules (type, notSetLimit, bankOfHoursTypeLimit) {
    if (type === 'Hora Extra e Banco de Horas' && !notSetLimit) {
      if (!bankOfHoursTypeLimit) {
        return ['Informe o limite diário de horas do banco!']
      }
    }
  }
}
