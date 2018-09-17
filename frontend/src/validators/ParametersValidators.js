export default class ParametersValidators {
  overtimeRules (overtime) {
    if (!overtime) {
      return ['Informe o adicional da hora extra!']
    } else if (overtime < 50) {
      return ['O adicional da hora extra deve ser no minimo 50%']
    }
  }
  nightOvertimeRules (nightOvertime) {
    if (!nightOvertime || nightOvertime <= 0) {
      return ['Informe o adicional da hora extra noturna!']
    } else if (nightOvertime < 20) {
      return ['O adicional noturno deve ser de no minimo 20%']
    }
  }
  weekEndOvertimeRules (weekEndOvertime) {
    if (!weekEndOvertime || weekEndOvertime <= 0) {
      return ['Informe o adicional da hora extra de domingos e feriados!']
    } else if (weekEndOvertime < 100) {
      return ['O adicional de domingos e feriados deve ser no minimo 100%']
    }
  }
  retroactiveAppointmentRules (retroactiveAppointment) {
    if (!retroactiveAppointment || retroactiveAppointment <= 0) {
      return ['Informe o prazo de apontamento retroativo!']
    }
  }
  relocationRequestRules (relocationRequest) {
    if (!relocationRequest || relocationRequest <= 0) {
      return ['Informe o prazo de solicitação de remanejamento!']
    }
  }
  bankCompensationRules (bankCompensationRules) {
    if (!bankCompensationRules || bankCompensationRules <= 0) {
      return ['Informe o prazo para compensação do banco de horas!']
    } else if (bankCompensationRules < 6) {
      return ['O prazo para compensação do banco deve ser de no mínimo 6 meses']
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
      if (!overtimeTypeLimit || overtimeTypeLimit <= 0) {
        return ['Informe o limite diário de horas extras!']
      }
    }
  }
  bankOfHoursTypeLimitRules (type, notSetLimit, bankOfHoursTypeLimit) {
    if (type === 'Hora Extra e Banco de Horas' && !notSetLimit) {
      if (!bankOfHoursTypeLimit || bankOfHoursTypeLimit <= 0) {
        return ['Informe o limite diário de horas do banco!']
      }
    }
  }
}
