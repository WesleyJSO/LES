export const HEADERS = [
  { text: 'Nome', align: 'center', sortable: false, value: 'nome' },
  { text: 'Carga Horária', align: 'center', value: 'cargaHoraria' },
  { text: 'Salário', value: 'salario' },
  { text: 'Tipo de Hora', value: 'tipoHora' },
  { text: 'Gestor', value: 'gestor' },
  { text: 'Telefone', value: 'telefone1' },
  { text: 'E-mail', align: 'center', value: 'email' },
  { text: 'Actions', value: 'name', sortable: false }
]
export const SUB_HEADERS = [
  { text: 'PIS/PASESP', value: 'pis', align: 'center', sortable: false },
  { text: 'Data de Nascimento', value: 'birthdate', align: 'center', sortable: false },
  { text: 'Data de Ingresso na Empresa', value: 'dataIngressoEmpresa', align: 'center', sortable: false },
  { text: 'Telefone 2', value: 'telefone2', align: 'center', sortable: false },
  { text: 'Telefone 3', value: 'telefone3', align: 'center', sortable: false }
]
export const ITEMS = [
  'Bill Gates', 'Torvald Linux', 'Elon Musk'
]
export const DATE_FORMATS = [ {year: 'numeric', month: 'numeric', day: 'numeric'} ]
export const GROUPS_HEADERS = [
  { text: 'Nome', align: 'center', sortable: false, value: 'nome' },
  { text: 'Data Criação', align: 'center', value: 'dataCriacao' },
  { text: 'Centro de Custos', value: 'centroDeCusto' },
  { text: 'Membros', value: 'tipoHora' }
]
export const MANAGE_HOURS_TABLE_HEADERS = [
  { text: 'Total Horas Banco', align: 'center', sortable: false, value: 'total_banco' },
  { text: 'Total Dias', align: 'center', sortable: false, value: 'total_dias' },
  { text: 'Férias', align: 'center', sortable: false, value: 'ferias' },
  { text: 'Valor Total (R$)', align: 'center', sortable: false, value: 'valor_total' }
]
export default class AdminService {
  static get HEADERS () { return HEADERS }
  static get SUB_HEADERS () { return SUB_HEADERS }
  static get DATE_FORMATS () { return SUB_HEADERS }
  static get ITEMS () { return ITEMS }
  static get GROUPS_HEADERS () { return GROUPS_HEADERS }
  static get MANAGE_HOURS_TABLE_HEADERS () { return MANAGE_HOURS_TABLE_HEADERS }
}
