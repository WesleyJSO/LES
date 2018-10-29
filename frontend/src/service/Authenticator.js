export default class Authenticator {
  static HAS_ROLE (role) {
    let principal = JSON.parse(sessionStorage.getItem('principal'))
    if (principal) {
      return principal.authorities.find(i => i.authority === role)
    } else {
      console.log('#### There is NO User ####')
      console.log('#### Redirecting To Login ####')
      return false
    }
  }
}
