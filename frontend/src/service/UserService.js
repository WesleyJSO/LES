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
export const REQUEST = [
  {id: 1, description: 'Hora Extra'},
  {id: 3, description: 'Banco de Horas'},
  {id: 2, description: 'Remanejamento de Horas/Dias'}
]
export default class UserService {
  static get HEADERS () { return HEADERS }
  static get SUB_HEADERS () { return SUB_HEADERS }
  static get DATE_FORMATS () { return SUB_HEADERS }
  static get PAGINATION () { return PAGINATION }
  static get APPOINTMENTS () { return APPOINTMENTS }
  static get REQUEST () { return REQUEST }
}
