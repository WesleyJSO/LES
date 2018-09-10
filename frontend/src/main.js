// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'vuetify/dist/vuetify.min.css'
import Vue from 'vue'
import App from './App'
import axios from 'axios'
import router from './router'
import Vuetify from 'vuetify'
import moment from 'moment'
import VeeValidate from 'vee-validate'
import UserValidator from './validators/UserValidators'
import BaseHourCalculationValidators from './validators/BaseHourCalculationValidators'
import VueChartkick from 'vue-chartkick'
import Chart from 'chart.js'
import ParametersValidators from './validators/ParametersValidators'

Vue.use(VueChartkick, {adapter: Chart})

Vue.use(VeeValidate)
Vue.use(Vuetify)

Vue.prototype.$_axios = axios
Vue.prototype.$_url = 'http://localhost:8080/'
Vue.prototype.$_moment = moment

Vue.prototype.$v_user = new UserValidator()
Vue.prototype.$v_baseHour = new BaseHourCalculationValidators()
Vue.prototype.$v_parameters = new ParametersValidators()

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
