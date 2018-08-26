export default class Funcionario {
  constructor (id, nome, email, ultimoNome, dataNascimento, login, listaTelefone, pis, dataIngressoEmpresa, gestor, listaBaseCalculoHoras) {
    this.id = id
    this.nome = nome
    this.email = email
    this.ultimoNome = ultimoNome
    this.dataNascimento = dataNascimento
    this.login = login
    this.listaTelefone = listaTelefone
    this.pis = pis
    this.dataIngressoEmpresa = dataIngressoEmpresa
    this.gestor = gestor
    this.listaBaseCalculoHoras = listaBaseCalculoHoras
  }
}
