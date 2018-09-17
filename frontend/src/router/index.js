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
import SystemParameters from '@/components/admin/SystemParameters'
import ManageHours from '@/components/admin/ManageHours'
import Timeline from '@/components/user/Timeline'

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
      component: RegisterCompany
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
      component: CostCentre
    },
    {
      path: '/Parametros',
      name: 'SystemParameters',
      component: SystemParameters
    },
    {
      path: '/GerenciarBancoHoras',
      name: 'ManageHours',
      component: ManageHours
    },
    {
      path: '/LinhaDoTempo',
      name: 'Timeline',
      component: Timeline
    }
  ]
})
