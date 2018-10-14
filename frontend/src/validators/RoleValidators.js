export default class RoleValidators {
  validateSelectedRoles (roleList) {
    if (roleList !== undefined) {
      let active = false
      roleList.forEach(element => {
        if (element.active) {
          active = true
        }
      })
      if (!active) {
        return ['Selecione pelo menos um tipo de acesso!']
      }
    }
  }
}
