import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import ConsultarListar from '@/components/Admin/ConsultarListar'
import AlterarUsuario from '@/components/usuario/Alterar'
import RegisterUser from '@/components/Admin/RegisterUser'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Login
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/ConsultarListar',
      name: 'ConsultarListar',
      component: ConsultarListar
    },
    {
      path: '/AlterarUsuario',
      name: 'AlterarUsuario',
      component: AlterarUsuario
    },
    {
      path: '/RegisterUser',
      name: 'RegisterUser',
      component: RegisterUser
    }
  ]
})
