<template>
    <div>
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
          <v-card-text>Carga Horaria: {{workload}}</v-card-text>
        </v-flex>
			</v-toolbar>
      <v-layout>
        <v-flex center >
          <AppointTable :editable="true" :appointments="appointments" @register="takeAppointment($event)"></AppointTable>
          <v-flex class="text-xs-center">
            <v-card class="elevation-10">       
							<v-flex>
								<AppointButton v-for="button in buttons" :key="button.name" 
                  :disable="button.disable" 
                  :buttonName="button.name" 
                  @emitAppoint="appoint($event)">
                </AppointButton>
							</v-flex>
            </v-card>
          </v-flex>
        </v-flex>
      </v-layout>
    </v-form>
  </div>
</template>

<script>
import AppointButton from '@/components/shared/AppointButton.vue'
import AppointDialog from '@/components/shared/AppointDialog.vue'
import AppointTable from '@/components/shared/AppointTable.vue'
import DateHelper from '../../helpers/DateHelper'
import Authenticator from '../../service/Authenticator'

export default {
  data: () => ({
    messages: [],
    haveMessage: false,
    workload: '00:00',
    messageColor: '',
    empty: '--:--',
    tittle: 'Apontamento Rápido',
    dialog: false,
    buttons: [
      { name: 'Entrada Manhã', disable: false },
      { name: 'Saída Manhã', disable: true },
      { name: 'Entrada Tarde', disable: false },
      { name: 'Saída Tarde', disable: true },
      { name: 'Entrada Noite', disable: false },
      { name: 'Saída Noite', disable: true },
      { name: 'Saída Partícular', disable: true }
    ],
    appointment: {},
    employee: {user: {id: null}},
    appointments: []
  }),
  components: {
    AppointButton,
    AppointDialog,
    AppointTable
  },
  computed: {
  },
  watch: {
  },
  created () {
    this.employee.user.id = Authenticator.GET_AUTHENTICATED().id
    this.findWorkload(this.employee)
    this.callApi({monthAndYear: new Date(), date: new Date(), employee: this.employee})
  },
  methods: {
    takeAppointment (appointment) {
      this.appointment = appointment
      this.registerAppointments()
      this.verifyButtons()
    },
    appoint (button) {
      let date = new Date()
      let time = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
      time += (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
      if (button === 'Saída Partícular') {
        if (this.appointment.particularExit) {
          button = 'Retorno'
        }
      }
      this.mountAppointment(button, time)
    },
    mountAppointment (field, time) {
      if (field === 'Entrada Manhã') {
        this.appointment.morningEntrance = time
      } else if (field === 'Saída Manhã') {
        this.appointment.morningOut = time
      } else if (field === 'Entrada Tarde') {
        this.appointment.afternoonEntrance = time
      } else if (field === 'Saída Tarde') {
        if (!this.appointment.morningOut) {
          this.appointment.morningOut = '12:00'
          this.appointment.afternoonEntrance = '12:00'
        }
        this.appointment.afternoonOut = time
      } else if (field === 'Entrada Noite') {
        this.appointment.nightEntrance = time
      } else if (field === 'Saída Noite') {
        if (!this.appointment.morningOut && this.appointment.morningEntrance) {
          this.appointment.morningOut = '12:00'
          this.appointment.afternoonEntrance = '12:00'
        }
        if (!this.appointment.afternoonOut && !this.appointment.nightEntrance) {
          this.appointment.afternoonOut = '18:00'
          this.appointment.nightEntrance = '18:00'
        }
        this.appointment.nightOut = time
      } else if (field === 'Saída Partícular') {
        this.appointment.particularExit = time
      } else if (field === 'Retorno') {
        this.appointment.particularExitReturn = time
      }
      this.appointment.date = DateHelper.formatISODate(this.appointment.date)
      this.appointment.employee = null
      this.verifyButtons()
      this.registerAppointments()
    },
    verifyButtons () {
      if (this.appointment.particularExit) {
        this.buttons[0].disable = this.buttons[1].disable = this.buttons[2].disable = this.buttons[3].disable = this.buttons[5].disable = true
        this.buttons[6].disable = false
        if (this.appointment.particularExitReturn) {
          this.buttons[0].disable = this.buttons[1].disable = this.buttons[2].disable = this.buttons[3].disable = true
          this.buttons[5].disable = this.buttons[6].disable = false
        }
      }
      if (this.appointment.morningEntrance) {
        this.buttons[1].disable = this.buttons[3].disable = this.buttons[5].disable = this.buttons[6].disable = false
        this.buttons[2].disable = this.buttons[4].disable = true
      }
      if (this.appointment.morningOut) {
        this.buttons[2].disable = this.buttons[4].disable = false
        this.buttons[0].disable = this.buttons[3].disable = this.buttons[5].disable = this.buttons[6].disable = true
      }
      if (this.appointment.afternoonEntrance) {
        this.buttons[0].disable = this.buttons[1].disable = this.buttons[4].disable = true
        this.buttons[3].disable = this.buttons[5].disable = this.buttons[6].disable = false
      }
      if (this.appointment.afternoonOut) {
        this.buttons[0].disable = this.buttons[1].disable = this.buttons[2].disable = this.buttons[5].disable = this.buttons[6].disable = true
        this.buttons[4].disable = false
      }
      if (this.appointment.nightEntrance) {
        this.buttons[0].disable = this.buttons[1].disable = this.buttons[2].disable = this.buttons[3].disable = true
        this.buttons[5].disable = this.buttons[6].disable = false
      }
      if (this.appointment.nightOut) {
        this.buttons[0].disable = this.buttons[1].disable = this.buttons[2].disable = this.buttons[3].disable = this.buttons[6].disable = this.buttons[4].disable = true
      }
      if (this.appointment.particularExit && !this.appointment.particularExitReturn) {
        this.buttons[0].disable = this.buttons[1].disable = this.buttons[2].disable = this.buttons[3].disable = this.buttons[5].disable = true
        this.buttons[6].disable = false
      }
    },
    async registerAppointments () {
      var response = null
      var result = null
      if (!this.appointment.id) {
        try {
          response = await this.$_axios.post(`${this.$_url}appointment`, this.appointment)
          console.log('post')
          result = response.data
          if (result.resultList.length !== 0) {
            this.appointments = result.resultList
            this.appointment = this.appointments[0]
          }
          if (result.message) {
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
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        }
      } else {
        try {
          response = await this.$_axios.put(`${this.$_url}appointment`, this.appointment)
          console.log('put')
          result = response.data
          if (result.resultList.length !== 0) {
            this.appointments = result.resultList
            this.appointment = this.appointments[0]
          }
          if (result.message) {
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
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        }
      }
      this.callApi({id: this.appointment.id})
    },
    async callApi (appointment) {
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}appointment`, appointment)
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          this.appointments = result.resultList
          this.appointment = this.appointments[0]
          this.verifyButtons()
          console.log(JSON.stringify(this.appointments))
        } else {
          this.appointment = appointment
          this.registerAppointments()
        }
        this.verifyButtons()
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
    async findWorkload (employee) {
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}employee`, employee)
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          employee = result.resultList[0]
          this.workload = '0' + employee.baseHourCalculation.workload + ':00'
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

<style scoped>
  h1 {
    font-size: 40px;
    text-align: center;
  }
</style>
