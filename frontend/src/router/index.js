import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import ConsultarListar from '@/components/admin/ConsultarListar'
import UpdateUser from '@/components/admin/UpdateUser'
import RegisterUser from '@/components/admin/RegisterUser'
import Appointments from '@/components/user/Appointments'

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
      name: 'UpdateUser',
      component: UpdateUser
    },
    {
      path: '/CadastrarUsuario',
      name: 'RegisterUser',
      component: RegisterUser
    },
    {
      path: '/Appointments',
      name: 'Appointments',
      component: Appointments
    }
  ]
})
