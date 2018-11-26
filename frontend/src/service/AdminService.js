export const HEADERS = [
  { text: 'Nome', align: 'left', sortable: false, value: 'lastName' },
  { text: 'Carga Horária (Horas)', align: 'center', value: 'cargaHoraria' },
  { text: 'Salário (R$)', align: 'center', value: 'salario' },
  { text: 'Tipo de Hora', align: 'center', value: 'tipoHora' },
  { text: 'Telefone', align: 'center', value: 'telefone1' },
  { text: 'E-mail', align: 'center', value: 'email' },
  { text: 'Actions', align: 'center', value: 'name', sortable: false }
]
export const SUB_HEADERS = [
  { text: 'PIS/PASESP', value: 'pis', align: 'center', sortable: false },
  { text: 'Data de Ingresso na Empresa', value: 'dataIngressoEmpresa', align: 'center', sortable: false },
  { text: 'Telefone 2', value: 'telefone2', align: 'center', sortable: false },
  { text: 'Telefone 3', value: 'telefone3', align: 'center', sortable: false }
]
export const ITEMS = [
  'Bill Gates', 'Torvald Linux', 'Elon Musk'
]
export const DATE_FORMATS = [ {year: 'numeric', month: 'numeric', day: 'numeric'} ]
export const COST_CENTRE_HEADERS = [
  { text: 'Código', align: 'left', sortable: false, value: 'code' },
  { text: 'Nome', align: 'center', sortable: false, value: 'name' },
  { text: 'Membros', value: 'employees' },
  { text: 'Data Criação', align: 'center', value: 'criationDate' },
  { text: 'Ação', align: 'center', value: 'action' }
]
export const MANAGE_HOURS_TABLE_HEADERS = [
  { text: 'Total Horas Banco', align: 'center', sortable: false, value: 'total_banco' },
  { text: 'Total Dias', align: 'center', sortable: false, value: 'total_dias' },
  { text: 'Férias', align: 'center', sortable: false, value: 'ferias' },
  { text: 'Valor Total (R$)', align: 'center', sortable: false, value: 'valor_total' }
]
export const TITLES = {
  employees: 'Lista de Funcionários',
  manageCompTime: 'Gerenciamento de Horas'

}
export const MESSAGES = {
  emptyDataTable: 'Nenhum registro foi encontrado'
}
export const LABELS = {
  search: 'Pesquisar'
}

export default class AdminService {
  static get HEADERS () { return HEADERS }
  static get SUB_HEADERS () { return SUB_HEADERS }
  static get DATE_FORMATS () { return SUB_HEADERS }
  static get ITEMS () { return ITEMS }
  static get COST_CENTRE_HEADERS () { return COST_CENTRE_HEADERS }
  static get MANAGE_HOURS_TABLE_HEADERS () { return MANAGE_HOURS_TABLE_HEADERS }
  static get MESSAGES () { return MESSAGES }
  static get TITLES () { return TITLES }
  static get LABELS () { return LABELS }
}
