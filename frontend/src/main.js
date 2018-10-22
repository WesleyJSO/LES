// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import '@mdi/font/css/materialdesignicons.css'
import '@fortawesome/fontawesome-free/css/all.css'
import Vue from 'vue'
import App from './App'
import axios from 'axios'
import router from './router'
import Vuetify from 'vuetify'
import moment from 'moment'
import VeeValidate from 'vee-validate'
import UserValidator from './validators/UserValidators'
import BaseHourCalculationValidators from './validators/BaseHourCalculationValidators'
import ParametersValidators from './validators/ParametersValidators'
import CostCentreValidator from './validators/CostCentreValidator'
import VCalendar from 'v-calendar'
import 'v-calendar/lib/v-calendar.min.css'
import VueChartkick from 'vue-chartkick'
import Chart from 'chart.js'
import CompanyValidators from '@/validators/CompanyValidators'
import AddressValidators from '@/validators/AddressValidators'
import RequestValidator from './validators/RequestValidator'
import RoleValidators from './validators/RoleValidators'

Vue.use(VueChartkick, {adapter: Chart})

Vue.use(Vuetify, {
  iconfont: 'mdi' // 'md' || 'mdi' || 'fa' || 'fa4'
})

Vue.use(VCalendar, {
  formats: {
    title: 'MMMM YYYY',
    weekdays: 'W',
    navMonths: 'MMM',
    input: ['L', 'DD-MM-YYYY', 'DD/MM/YYYY'],
    dayPopover: 'L',
    data: ['L', 'DD-MM-YYYY', 'DD/MM/YYYY']
  }
})

Vue.use(VeeValidate)
Vue.use(Vuetify)

Vue.prototype.$_axios = axios
Vue.prototype.$_url = 'http://localhost:9999/'
Vue.prototype.$_viaCep = 'https://viacep.com.br/ws/'
Vue.prototype.$_moment = moment
Vue.prototype.$v_user = new UserValidator()
Vue.prototype.$v_baseHour = new BaseHourCalculationValidators()
Vue.prototype.$v_parameters = new ParametersValidators()
Vue.prototype.$v_costCentre = new CostCentreValidator()
Vue.prototype.$v_company = new CompanyValidators()
Vue.prototype.$v_address = new AddressValidators()
Vue.prototype.$v_request = new RequestValidator()
Vue.prototype.$v_role = new RoleValidators()

// Axios interceptors
// Request
axios.interceptors.request.use(
  (config) => {
    let token = localStorage.getItem('access_token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)
// Response
axios.interceptors.response.use(function (response) {
  // Get JWT token from response and save it on localStorage
  let token = response.headers['authorization']
  if (token) {
    localStorage.setItem('access_token', token)
  }
  return response
}, function (error) {
  if (error.response.status === 401) {
    alert('Usuário não reconhecido')
    this.router.push('Login')
  } else {
    return Promise.reject(error)
  }
})

Vue.filter('dataFormatada', function (value) {
  if (value) {
    return moment(String(value)).format('DD/MM/YYYY')
  }
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
