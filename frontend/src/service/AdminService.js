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
export default class AdminService {
  static getHeaders () {
    return HEADERS
  }
  static getSubHeaders () {
    return SUB_HEADERS
  }
}
