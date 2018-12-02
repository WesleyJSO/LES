<template>
  <div>
    <h1>Apontamentos</h1>
    <br/>

    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>

    <v-form>
      <v-card>
        <v-layout>
          <v-flex xs12 sm6 d-flex>
            <v-select
              v-model="employeeName"
              :items="employeesNames"
              prepend-icon="person"
              label="Colaborador"
              :item-value="employeeName"
              @change="setEmployee">
            </v-select>
          </v-flex>
          
          <v-spacer></v-spacer>
          
          <v-flex class="text-xs-right"
            v-if="isAdmin">
            <v-btn
              @click="downloadDemonstrativo()"
              color="primary">
              Download Demonstrativo
            </v-btn>
          </v-flex>
          
          <v-flex class="text-xs-left"
            v-if="isAdmin">
            <v-btn
              @click="downloadPDF()"
              color="primary">
              Download Espelho
            </v-btn>
          </v-flex>
        </v-layout>

      </v-card>
      <br>
      <MonthAppointments :editable="false" :employee="employee" @haveValues="getValuesBool($event)" @setMonthYear="getMonthYear($event)"></MonthAppointments>
    </v-form>
  </div>
</template>

<script>
import MonthAppointments from '@/components/shared/MonthAppointments.vue'
import Authenticator from '../../service/Authenticator'

export default {
  data: () => ({
    haveValues: false,
    isAdmin: false,
    messages: '',
    haveMessage: false,
    employeeName: '',
    employeesNames: [],
    employees: [],
    employee: null,
    date: ''
  }),
  components: {
    MonthAppointments
  },
  beforeMount () {
    if (Authenticator.HAS_ROLE('ROLE_ADMIN')) {
      this.isAdmin = true
      this.callApi({active: true})
    } else {
      var user = {
        id: Authenticator.GET_AUTHENTICATED().id
      }
      this.callApi({manager: {user: user}})
    }
  },
  watch: {
    employees () {
      this.employeesNames = []
      this.employees.forEach(element => {
        element.user.authorities.forEach(i => {
          if (i.authority === 'ROLE_EMPLOYEE') {
            this.employeesNames.push(element.name + ' ' + element.lastName)
          }
        })
      })
    }
  },
  methods: {
    getValuesBool (haveValues) {
      this.haveValues = haveValues
    },
    getMonthYear (date) {
      this.date = date
    },
    setEmployee () {
      let name = this.employeeName.split(' ')
      this.employees.forEach(element => {
        if (element.name === name[0] && element.lastName === name[1]) {
          this.employee = {id: element.id}
        }
      })
    },
    async downloadDemonstrativo () {
      this.haveMessage = false
      if (this.employee && this.date !== '') {
        var response = null
        var result = null
        try {
          response = await this.$_axios.get(`${this.$_url}bankstatement/` + this.employee.id + `/` + this.date, {responseType: 'blob'})
          result = response.data
          console.log(JSON.stringify(result))
          const url = window.URL.createObjectURL(new Blob([response.data]))
          const link = document.createElement('a')
          link.href = url
          link.setAttribute('download', 'Demontrativo de Banco - ' + this.employeeName + '.pdf')
          document.body.appendChild(link)
          // link.click()
          link.dispatchEvent(new MouseEvent(`click`, {bubbles: true, cancelable: true, view: window}))
          if (result.mensagem) {
            // this.messages = [...result.message]
            // this.haveMessage = true
            if (result.success) {
            // retorno mensagem de sucesso /
              this.messageColor = 'info'
            } else {
              // retorno mensagem de erro /
              this.messageColor = 'warning'
            }
          }
        } catch (error) {
          // erro na requisição do serviço /
          console.log(error)
          // this.messages = ['Erro durante execução do serviço!']
          // this.haveMessage = true
          // this.messageColor = 'error'
        }
      } else {
        this.messages = ['Selecione um funcionário e uma data!']
        this.haveMessage = true
        this.messageColor = 'warning'
      }
    },
    async downloadPDF () {
      this.haveMessage = false
      if (this.employee && this.date !== '') {
        if (this.haveValues) {
          var response = null
          var result = null
          try {
            response = await this.$_axios.get(`${this.$_url}timetracking/` + this.employee.id + `/` + this.date, {responseType: 'blob'})
            result = response.data
            const url = window.URL.createObjectURL(new Blob([response.data]))
            const link = document.createElement('a')
            link.href = url
            link.setAttribute('download', 'Espelho de Horas - ' + this.employeeName + '.pdf')
            document.body.appendChild(link)
            // link.click()
            link.dispatchEvent(new MouseEvent(`click`, {bubbles: true, cancelable: true, view: window}))
            if (result.mensagem) {
              // this.messages = [...result.message]
              // this.haveMessage = true
              if (result.success) {
              // retorno mensagem de sucesso /
                this.messageColor = 'info'
              } else {
                // retorno mensagem de erro /
                this.messageColor = 'warning'
              }
            }
          } catch (error) {
            // erro na requisição do serviço /
            console.log(error)
            // this.messages = ['Erro durante execução do serviço!']
            // this.haveMessage = true
            // this.messageColor = 'error'
          }
        } else {
          this.messages = ['Mês sem registro!']
          this.haveMessage = true
          this.messageColor = 'warning'
        }
      } else {
        this.messages = ['Selecione um funcionário e uma data!']
        this.haveMessage = true
        this.messageColor = 'warning'
      }
    },
    async callApi (employee) {
      this.haveMessage = false
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}employee`, employee)
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          this.employees = result.resultList
        }
        if (result.mensagem) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
          // retorno mensagem de sucesso /
            this.messageColor = 'info'
          } else {
            // retorno mensagem de erro /
            this.messageColor = 'warning'
          }
        }
      } catch (error) {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    }
  }
}
</script>
