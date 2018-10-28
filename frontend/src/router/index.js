import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import ConsultarListar from '@/components/admin/ConsultarListar'
import UpdateUser from '@/components/admin/UpdateUser'
import RegisterUser from '@/components/admin/RegisterUser'
import Appointments from '@/components/user/Appointments'
import Request from '@/components/user/Request'
import RegisterCompany from '@/components/admin/RegisterCompany'
import RequestView from '@/components/admin/RequestView'
import Reports from '@/components/reports/Reports'
import CostCentre from '@/components/admin/CostCentre'
// import SystemParameters from '@/components/admin/SystemParameters'
import ManageHours from '@/components/admin/ManageHours'
import Timeline from '@/components/user/Timeline'
import Appoint from '@/components/user/Appoint'
// import RegisterSystemParameters from '@/components/admin/RegisterSystemParameters'
import Authenticator from '@/service/Authenticator'

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
      path: '/Consultar',
      name: 'ConsultarListar',
      component: ConsultarListar,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    },
    {
      path: '/alterar',
      name: 'UpdateUser',
      component: UpdateUser,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    },
    {
      path: '/cadastro',
      name: 'RegisterUser',
      component: RegisterUser
    },
    {
      path: '/Apontamentos',
      name: 'Appointments',
      component: Appointments
    },
    {
      path: '/Solicitacao',
      name: 'Request',
      component: Request
    },
    {
      path: '/CadastrarEmpresa',
      name: 'RegisterCompany',
      component: RegisterCompany,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    },
    {
      path: '/Solicitacoes',
      name: 'RequestView',
      component: RequestView
    },
    {
      path: '/Graficos',
      name: 'Reports',
      component: Reports
    },
    {
      path: '/CentroDeCustos',
      name: 'CostCentre',
      component: CostCentre,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    },
    /* {
      path: '/Parametros',
      name: 'SystemParameters',
      component: SystemParameters,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    }, */
    {
      path: '/GerenciarBancoHoras',
      name: 'ManageHours',
      component: ManageHours,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    },
    {
      path: '/LinhaDoTempo',
      name: 'Timeline',
      component: Timeline
    },
    {
      path: '/Apontar',
      name: 'Appoint',
      component: Appoint
    }/*,
    {
      path: '/CadastroParametros',
      name: 'RegisterSystemParameters',
      component: RegisterSystemParameters,
      props: true,
      beforeEnter: (to, from, next) => {
        Authenticator.HAS_ROLE('ROLE_ADMIN') ? next() : next('/Login')
      }
    } */
  ]
})
