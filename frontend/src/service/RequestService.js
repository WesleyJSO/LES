export const HEADERS = [
  { text: 'Solicitante', align: 'left', sortable: false, value: 'employee.name' },
  { text: 'Tipo', align: 'left', value: 'type' },
  { text: 'Status', align: 'left', value: 'status' },
  { text: 'Editar', align: 'righ', value: 'acao' },
  { text: 'Vizualizar', align: 'righ', value: 'acao' }
]
export const HEADERS_ADMIN = [
  { text: 'Solicitante', align: 'left', sortable: false, value: 'employee.name' },
  { text: 'Tipo', align: 'left', value: 'type' },
  { text: 'Status', align: 'left', value: 'status' },
  { text: 'Editar', align: 'righ', value: 'acao' }
]
export const HEADERS_APPROVED = [
  { text: 'Solicitante', align: 'left', sortable: false, value: 'employee.name' },
  { text: 'Tipo', align: 'left', value: 'type' },
  { text: 'Status', align: 'left', value: 'status' },
  { text: 'Vizualizar', align: 'righ', value: 'acao' }
]
export const TITLES = {
  requests: 'Solicitações',
  myRequest: 'Minhas Solicitações',
  approved: 'Avalidas',
  pending: 'Pendentes',
  entryDate: 'Data Afetada - ',
  endDate: 'Nova Data - ',
  rangeDates: 'Datas Afetadas: ',
  tookDayOff: 'Folgar em - ',
  file: 'Anexo: ',
  // Should be replaced by origin file name from backend
  fileName: 'Nome do Arquivo',
  workDay: 'Trabalhar em - ',
  description: 'Descrição: ',
  employee: 'Colaborador - ',
  edit: 'Editar Solicitação',
  managerMessage: 'Detalhe: ',
  viewManagerMessage: 'Observações sobre a Solicitação: ',
  approvedManager: 'Atualizado por -  ',
  lastUpdate: 'Útilma atualização -  ',
  appointmentDate: 'Apontamento Realizado em: ',
  appointmentField: 'Alterações: ',
  from: 'De ',
  to: 'Para '

}
export const SUB_HEADERS = [
  { text: 'Descrição', align: 'center', value: 'description' }
]
export const LABELS = {
  managerMessage: 'Observações sobre a Solicitação: ',
  startDate: 'Data Alvo',
  endDate: 'Data Troca'
}
export const DATE_FORMATS = [ {year: 'numeric', month: 'numeric', day: 'numeric'} ]
// Using key value as number cause type attribute from backend object cames like this
export const TYPE_NAME = {
  1: 'Hora Extra',
  2: 'Banco de Hora',
  3: 'Alteração de Apontamento',
  4: 'Remanejamento de Dia'
}
export const PREFIXES = {
  dialogTitle: 'Solicitação de ',
  begin: 'Início: ',
  end: 'Fim: '
}
// Same here
export const STATUS = {
  1: 'Enviado',
  2: 'Aprovado',
  3: 'Negado'
}
export const BUTTONS = {
  approve: 'Aprovar',
  deny: 'Reprovar',
  close: 'Fechar',
  sendBack: 'Desfazer',
  confirm: 'Confirmar'
}
export const STATUS_VALUE = {
  sent: 1,
  approved: 2,
  denied: 3
}
export const MESSAGE = {
  consultError: 'Houve um erro ao consultar as Solicitações!',
  approveError: 'Houve um erro ao aprovar a solicitação!'
}
export const REQUEST = [
  {id: 1, description: 'Hora Extra'},
  {id: 2, description: 'Banco de Horas'},
  {id: 3, description: 'Alteração de Apontamento'},
  {id: 4, description: 'Remanejamento de Horas/Dias'}
]
export const REQUEST_VALUE = {
  1: {id: 1, description: 'Hora Extra'},
  2: {id: 2, description: 'Banco de Horas'},
  3: {id: 3, description: 'Alteração de Apontamento'},
  4: {id: 4, description: 'Remanejamento de Horas/Dias'}
}
export const COLORS = {
  black: 'black',
  info: 'info'
}

export default class RequestService {
  static get HEADERS () { return HEADERS }
  static get SUB_HEADERS () { return SUB_HEADERS }
  static get DATE_FORMATS () { return SUB_HEADERS }
  static get TYPE_NAME () { return TYPE_NAME }
  static get STATUS () { return STATUS }
  static get TITLES () { return TITLES }
  static get BUTTONS () { return BUTTONS }
  static get PREFIXES () { return PREFIXES }
  static get STATUS_VALUE () { return STATUS_VALUE }
  static get LABELS () { return LABELS }
  static get MESSAGE () { return MESSAGE }
  static get REQUEST () { return REQUEST }
  static get COLORS () { return COLORS }
  static get REQUEST_VALUE () { return REQUEST_VALUE }
  static get HEADERS_ADMIN () { return HEADERS_ADMIN }
  static get HEADERS_APPROVED () { return HEADERS_APPROVED }
}
