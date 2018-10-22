export default class Authenticator {
  static HAS_ROLE (role) {
    if (localStorage.principal) {
      let principal = JSON.parse(localStorage.principal)
      return principal.authorities.find(i => i.authority === role)
    }
    console.log('#### Unauthorized User ####')
    return false
  }
}
