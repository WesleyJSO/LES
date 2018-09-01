export default class UserValidators {
  email (email) {
    if (!email) {
      return ['campo E-mail deve ser preenchido!']
    } else if (!/.+@.+.+\..+/.test(email)) {
      return ['Formato de e-mail inválido!']
    }
  }
  password (password) {
    if (!password) {
      return ['campo Senha deve ser preenchido!']
    } else if (password.length <= 3) {
      return ['campo Senha deve conter no mínimo 3 caracteres!']
    }
  }
}
