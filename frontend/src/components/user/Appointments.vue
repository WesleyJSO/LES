<template>
  <div>
    <h1>Apontamento de Horas</h1>
    <br/>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-form>
      <v-toolbar flat color="white" >
        <v-toolbar-title>{{ tittle }}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-flex xs3 sm3 md3 lg3 xl3 class="text-xs-right">
          <v-select
            v-model="month"
            :items="months"
            prepend-icon="calendar_today"
            label="Mês"
            :item-value="month">
          </v-select>
        </v-flex>
        <v-flex xs3 sm3 md3 lg3 xl3 class="text-xs-right">
          <v-select
            v-model="year"
            :items="years"
            prepend-icon="calendar_today"
            label="Ano"
            :item-value="year">
          </v-select>
        </v-flex>
        <v-flex xs1 sm1 md1 lg1 xl1 class="text-xs-right">
          <v-icon @click="callApi()">search</v-icon>
        </v-flex>
      </v-toolbar>
      <v-layout>
        <v-flex center >
          <AppointTable :appointments="appointments" @register="takeAppointment($event)"></AppointTable>
          <v-card flat class="elevation-10" >
            <v-layout class="text-xs-center">
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Carga Horaria: 00:00</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Saldo Mensal: 00:00</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Total Faltas: 00:00</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Total Extras: 00:00</v-card-text>
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

export default {
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    empty: '--:--',
    tittle: 'Lista de Apontamentos',
    appointment: {},
    employee: {id: 1},
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
  computed: {
  },
  watch: {
  },
  created () {
    let today = new Date()
    let i = 0
    this.months = [
      'Janeiro',
      'Fevereiro',
      'Março',
      'Abril',
      'Maio',
      'Junho',
      'Julho',
      'Agosto',
      'Setembro',
      'Outubro',
      'Novembro',
      'Dezembro'
    ]
    for (let year = 2010; year <= today.getFullYear(); year++, i++) {
      this.years[i] = year
    }
    this.month = this.months[today.getMonth() + 1]
    this.year = this.years[i]
    this.callApi()
  },
  methods: {
    takeAppointment (appointment) {
      this.appointment = appointment
      this.registerAppointments()
    },
    registerAppointments () {
      if (!this.appointment.id) {
        // this.appointment.employee = this.employee
        console.log(JSON.stringify(this.appointment))
        this.$_axios.post(`${this.$_url}appointment`, this.appointment).then(response => {
          console.log('post')
          let result = response.data
          if (result.resultList.length !== 0) {
            this.appointments = result.resultList
            this.appointment = this.appointments[0]
          }
        }).catch(error => {
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      } else {
        this.$_axios.put(`${this.$_url}appointment`, this.appointment).then(response => {
          console.log('put')
          let result = response.data
          if (result.resultList.length !== 0) {
            this.appointments = result.resultList
            this.appointment = this.appointments[0]
          }
        }).catch(error => {
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      }
    },
    callApi () {
      this.$_axios.get(`${this.$_url}appointment/1`).then(response => {
        var result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          this.appointments = result.resultList
          this.appointment = this.appointments[0]
          console.log(JSON.stringify(this.appointments))
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
      }).catch(error => {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    }
  }
}
</script>
