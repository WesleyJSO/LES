// tutoriel nuit (nightwatch) http://nightwatchjs.org/api
// comando para executar: em frontend, node_modules\.bin\nightwatch
// caso não funcione executar npm install, caso ainda não funcione assista ao video
module.exports = {
  'test': function (browser) {
    browser
      .url('http://localhost:8081/#/CadastrarEmpresa')
      .waitForElementVisible('body', 3000)
      .setValue('input[id=socialName]', 'test1')
      .pause(1000)
      .setValue('input[id=tradingName]', 'test2')
      .pause(1000)
      .setValue('input[id=cnpj]', '61442737000230')
      .pause(1000)
      .setValue('input[id=stateRegistration]', '454504330118')
      .pause(1000)
      .setValue('input[id=zipCode]', '08750710')
      .pause(1000)
      .setValue('input[id=number]', '123')
      .pause(1000)
      .setValue('input[id=street]', 'Rua teste')
      .pause(1000)
      .setValue('input[id=city]', 'Mogi das cruzes')
      .pause(1000)
      .setValue('input[id=state]', 'SP')
      .pause(1000)
      .click('button[id=success]')
      .pause(2000)
      .verify.containsText('#main', 'Dados da Empresa')
      .pause(5000)
      .end()
  }
}
