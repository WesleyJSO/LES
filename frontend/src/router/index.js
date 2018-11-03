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
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/alterar',
      name: 'UpdateUser',
      component: UpdateUser,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/cadastro',
      name: 'RegisterUser',
      component: RegisterUser,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/Apontamentos',
      name: 'Appointments',
      component: Appointments,
      meta: { requiresAuth: true, employeeAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/Solicitacao',
      name: 'Request',
      component: Request,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true, employeeAuth: true }
    },
    {
      path: '/CadastrarEmpresa',
      name: 'RegisterCompany',
      component: RegisterCompany,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/Solicitacoes',
      name: 'RequestView',
      component: RequestView,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true, employeeAuth: true }
    },
    {
      path: '/Graficos',
      name: 'Reports',
      component: Reports,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/CentroDeCustos',
      name: 'CostCentre',
      component: CostCentre,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    /* {
      path: '/Parametros',
      name: 'SystemParameters',
      component: SystemParameters,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    }, */
    {
      path: '/GerenciarBancoHoras',
      name: 'ManageHours',
      component: ManageHours,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/LinhaDoTempo',
      name: 'Timeline',
      component: Timeline,
      meta: { requiresAuth: true, employeeAuth: true, adminAuth: true, managerAuth: true }
    },
    {
      path: '/Apontar',
      name: 'Appoint',
      component: Appoint,
      meta: { requiresAuth: true, employeeAuth: true, adminAuth: true, managerAuth: true }
    }/*,
    {
      path: '/CadastroParametros',
      name: 'RegisterSystemParameters',
      component: RegisterSystemParameters,
      props: true,
      meta: { requiresAuth: true, adminAuth: true, managerAuth: true }
    } */
  ]
})
