export const TITLES = {
  report: 'Gráficos'
}
export const LABELS = {
  employees: 'Colaboradores',
  startDate: 'Data Inicial',
  endDate: 'Data  Final'
}
export const PREFIXES = {
  dialogTitle: 'Solicitação de ',
  begin: 'Início: ',
  end: 'Fim: '
}
export const BUTTONS = {
  approve: 'Aprovar',
  deny: 'Reprovar',
  close: 'Fechar'
}
export const DATE_CONFIG = {
  locale: 'pt-BR',
  format: [ {year: 'numeric', month: 'numeric', day: 'numeric'} ],
  date: 10,
  hour: 5
}
export const COLORS = {
  black: 'black',
  info: 'info'
}
export const TABS_NAME = [
  'Horas Trabalhadas',
  'Horas de Almoço',
  'Horas Extras'
]
export const FREQUENCY = [
  'Semana',
  'Mês',
  'Ano'
]
export const REPORT_TITLE = {
  workedHours: 'Horas Trabalhadas',
  lunchHours: 'Horas de Almoço',
  workOvertime: 'Horas Extras'
}
export const REPORT_AXIS = {
  x: {
    days: 'Dias'
  },
  y: {
    hours: 'Horas'
  }
}

export default class ReportService {
  static get LABELS () { return LABELS }
  static get TITLES () { return TITLES }
  static get BUTTONS () { return BUTTONS }
  static get PREFIXES () { return PREFIXES }
  static get DATE_CONFIG () { return DATE_CONFIG }
  static get COLORS () { return COLORS }
  static get TABS_NAME () { return TABS_NAME }
  static get FREQUENCY () { return FREQUENCY }
  static get REPORT_TITLE () { return REPORT_TITLE }
  static get REPORT_AXIS () { return REPORT_AXIS }
}
