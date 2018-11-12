<template>
  <div>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>

    <v-form>
      <v-toolbar>
        <v-toolbar-title>{{ tittle }}</v-toolbar-title>
        <v-spacer></v-spacer>

        <!-- novo campo de data -->
        <v-flex xs12 sm6 md4>
          <v-dialog
            v-model="modal"
            width="290px" ref="dialog"
            :return-value.sync="appointment.monthAndYear"
            persistent lazy full-width>

          <v-text-field
            slot="activator" v-model="appointment.monthAndYear"
            label="Data dos apontamentos"
            prepend-icon="event" readonly
          ></v-text-field>

            <v-date-picker
              v-model="appointment.monthAndYear" scrollable locale="pt-br"
              header-color="black" type="month"
            >
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="modal = false">Cancelar</v-btn>
              <v-btn flat color="primary"
                @mouseup="beforeCallApi(appointment.monthAndYear)"
                @click="$refs.dialog.save(appointment.monthAndYear)"
              >OK</v-btn>
            </v-date-picker>

          </v-dialog>
        </v-flex>

      </v-toolbar>
      <v-layout>
        <v-flex center >
          <AppointTable :editable="editable" :appointments="appointments" @register="takeAppointment($event)"></AppointTable>
          <v-card class="elevation-10" >
            <v-layout class="text-xs-center">
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Carga Horaria: {{ workload }}</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Carga Horaria Mensal: {{ monthWorkload }}</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Total Faltas: {{ abscenses }}</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Total Extras: {{ overtime }}</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Saldo Mensal: {{ balance }}</v-card-text>
              </v-flex>
            </v-layout>
          </v-card>
        </v-flex>
      </v-layout>
    </v-form>
  </div>
</template>

<script>
import AppointDialog from '@/components/shared/AppointDialog.vue'
import AppointTable from '@/components/shared/AppointTable.vue'
import moment from 'moment'

export default {
  props: [
    'employee',
    'editable'
  ],
  data: () => ({
    modal: false,
    monthWorkload: '0',
    workload: '0',
    abscenses: '00:00',
    overtime: '00:00',
    balance: '00:00',
    appointWithMonth: {},
    appointment: {
      monthAndYear: null
    },
    monthAndYear: '',
    monthYear: '',
    messages: [],
    haveMessage: false,
    messageColor: '',
    empty: '--:--',
    tittle: 'Lista de Apontamentos',
    appointments: [],
    months: [],
    month: '',
    years: [],
    year: ''
  }),
  components: {
    AppointDialog,
    AppointTable
  },
  beforeMount () {
    this.beforeCallApi(moment().format('YYYY-MM'))
  },
  watch: {
    employee () {
      this.beforeCallApi(this.monthYear)
    }
  },
  methods: {
    beforeCallApi (monthYear) {
      this.monthYear = monthYear
      if (this.employee) {
        this.appointWithMonth = {employee: this.employee, monthAndYear: new Date(`${monthYear}-01`)}
        this.findWorkload(this.employee)
        this.findMonthlyBalanceData(this.appointWithMonth)
        this.callApi(this.appointWithMonth)
      }
    },
    takeAppointment (appointment) {
      this.appointment = appointment
      this.registerAppointments()
    },
    registerAppointments () {
      // let previousList = this.appointments
      this.$_axios.put(`${this.$_url}appointment`, this.appointment)
        .then(response => {
          var result = response.data
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
          this.callApi(this.appointWithMonth)
        })
        .catch(error => {
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
    },
    callApi (appointment) {
      console.log(JSON.stringify(appointment))
      this.$_axios.patch(`${this.$_url}appointment`, appointment)
        .then(response => {
          var result = response.data
          console.log(JSON.stringify(result))
          this.appointments = result.resultList
          if (this.appointments.length > 0) {
            this.appointment = this.appointments[0]
          }
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
        })
        .catch(error => {
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
    },
    async findWorkload (employee) {
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}employee`, employee)
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          employee = result.resultList[0]
          this.workload = employee.baseHourCalculation.workload
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
    },
    findMonthlyBalanceData (monthlyBalance) {
      this.$_axios.patch(`${this.$_url}monthlybalance`, monthlyBalance)
        .then(response => {
          var result = response.data
          var monthData = null
          if (result.resultList.length > 0) {
            monthData = result.resultList[0]
            this.mountData(monthData)
          }
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
        })
        .catch(error => {
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
    },
    mountData (monthData) {
      this.abscenses = monthData.abscenseHours < 10 ? '0' + monthData.abscenseHours + ':' : monthData.abscenseHours + ':'
      this.abscenses += monthData.abscenseMinutes < 10 ? '0' + monthData.abscenseMinutes : monthData.abscenseMinutes
      this.overtime = monthData.overtimeHours < 10 ? '0' + monthData.overtimeHours + ':' : monthData.overtimeHours + ':'
      this.overtime += monthData.overtimeMinutes < 10 ? '0' + monthData.overtimeMinutes : monthData.overtimeMinutes
      this.balance = monthData.balanceHours < 10 ? '0' + monthData.balanceHours + ':' : monthData.balanceHours + ':'
      this.balance += monthData.balanceMinutes < 10 ? '0' + monthData.balanceMinutes : monthData.balanceMinutes
      if (monthData.monthWorkload < 150) {
        monthData.monthWorkload = 176
      }
      this.monthWorkload = monthData.monthWorkload
    }
  }
}
</script>