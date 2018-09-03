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

export default class AdminService {
  static get HEADERS () { return HEADERS }
  static get SUB_HEADERS () { return SUB_HEADERS }
  static get DATE_FORMATS () { return SUB_HEADERS }
  static get ITEMS () { return ITEMS }
}
