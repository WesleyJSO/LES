export default class BaseCalculoHoras {
  constructor (idBaseCalculoHoras, tipoHora, cargaHoraria, dataVigencia, salario) {
    this.idBaseCalculoHoras = idBaseCalculoHoras
    this.tipoHora = tipoHora
    this.cargaHoraria = cargaHoraria
    this.dataVigencia = dataVigencia
    this.salario = salario
  }
}
