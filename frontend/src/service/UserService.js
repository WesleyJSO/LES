export const HEADERS = [
  { text: 'Data', align: 'center', sortable: false, value: 'date' },
  { text: 'Entrada', align: 'center', value: 'entry' },
  { text: 'Saída Almoço', value: 'lauch_break' },
  { text: 'Retorno Almoço', value: 'return_launch_break' },
  { text: 'Saída', value: 'exit' },
  { text: 'Saída Particular', value: 'abscence' },
  { text: 'Total Horas', value: 'amount_hours' }
]
export const SUB_HEADERS = [
  { text: 'Hora Saída', value: 'abscence_date', align: 'center', sortable: false },
  { text: 'Hora Retorno', value: 'abscente_return', align: 'center', sortable: false },
  { text: 'Justificativa', value: 'description', align: 'center', sortable: false },
  { text: 'Total Faltas', value: 'amount_abscence', align: 'center', sortable: false }
]
export const DATE_FORMATS = [ {year: 'numeric', month: 'numeric', day: 'numeric'} ]
export const PAGINATION = { sortBy: 'id' }
export const APPOINTMENTS = { title: 'Apontamento de Horas', table: 'Lista de Apontamentos', addAppointments: 'Apontar Horas', types: ['Entrada', 'Almoço', 'Retorno Almoço', 'Saída', 'Saída Particular'] }
export const BUTTONS = {
  cancel: 'Cancelar',
  confirm: 'Confirmar'
}
export const TITLES = {
  default: 'Solicitação de Apontamento'
}
export const MESSAGES = {
  atention: 'ATENÇÃO!',
  default: 'Esse Apontamento viola o limite de tempo para apontamentos retroativos/posteriores. Você deseja Registrar uma Solicitação de Apontamento?'
}
export const LABELS = {
  requestDescription: 'Observação sobre a Solicitação: ',
  values: 'Alterações: ',
  appointmentField: 'Apontamento: '
}
export const FIELD_TO_CHANGE = {
  getMorningEntrance: 'Entrada Manhã',
  getMorningOut: 'Saída Manhã',
  getAfternoonEntrance: 'Entrada Tarde',
  getAfternoonOut: 'Saída Tarde',
  getNightEntrance: 'Entrada Noite',
  getNightOut: 'Saída Noite',
  getParticularExit: 'Saída Particular',
  getParticularExitReturn: 'Retorno Saída Particular'
}
export const MOMENT_FORMATS = {
  yearMonthDay: 'YYYY/MM/DD'
}

export default class UserService {
  static get HEADERS () { return HEADERS }
  static get SUB_HEADERS () { return SUB_HEADERS }
  static get DATE_FORMATS () { return SUB_HEADERS }
  static get PAGINATION () { return PAGINATION }
  static get APPOINTMENTS () { return APPOINTMENTS }
  static get TITLES () { return TITLES }
  static get MESSAGES () { return MESSAGES }
  static get LABELS () { return LABELS }
  static get BUTTONS () { return BUTTONS }
  static get FIELD_TO_CHANGE () { return FIELD_TO_CHANGE }
  static get MOMENT_FORMATS () { return MOMENT_FORMATS }
}
