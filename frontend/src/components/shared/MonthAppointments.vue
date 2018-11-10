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
                <v-card-text>Carga Horaria: 00:00</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Total Faltas: 00:00</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Total Extras: 00:00</v-card-text>
              </v-flex>
              <v-flex xs12 sm9 md6 lg6 xl4>
                <v-card-text>Saldo Mensal: 00:00</v-card-text>
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
  props: [
    'employeeId',
    'editable'
  ],
  data: () => ({
    modal: false,
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
  watch: {
    employeeId () {
      this.beforeCallApi(this.monthYear)
    }
  },
  methods: {
    beforeCallApi (monthYear) {
      this.monthYear = monthYear
      if (this.employeeId) {
        this.callApi({employee: {id: this.employeeId}, monthAndYear: new Date(`${monthYear}-01`)})
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
          if (result.resultList.length !== 0) {
            this.appointment = result.resultList[0]
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
          this.callApi({monthAndYear: new Date(this.appointment.monthAndYear)})
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
    }
  }
}
</script>