<template>
    <v-app>
			<li v-for="(message, index) in messages" :key="index">
				<v-alert :color="messageColor"
								:value="haveMessage"
								v-text="message"
								transition="scale-transition" />
			</li>
    <v-form>
			<v-toolbar flat color="white">
				<v-toolbar-title>{{ today }}</v-toolbar-title>
			</v-toolbar>
      <v-layout>
      <!-- Row 1 -->
        <v-flex center >
          <v-data-table :headers="headers"
                    :items="appointments"
                    item-key="id"
                    hide-actions
                    class="elevation-10 form-container" >
            <template slot="items" slot-scope="props">
              <tr>
                <td class="text-xs-center" >{{ props.item.morningEntrance || empty }}</td>
                <td class="text-xs-center" >{{ props.item.morningOut || empty }}</td>
                <td class="text-xs-center" >{{ props.item.afternoonEntrance || empty }}</td>
                <td class="text-xs-center" >{{ props.item.afternoonOut || empty }}</td>
                <td class="text-xs-center" >{{ props.item.nightEntrance || empty }}</td>
                <td class="text-xs-center" >{{ props.item.nightOut || empty }}</td>
                <td class="text-xs-center" >{{ props.item.particularExit || empty }}</td>
                <td class="text-xs-center" >{{ props.item.particularExitReturn || empty }}</td>
              </tr>
            </template>
          </v-data-table>
          <v-flex class="text-xs-center">
            <v-card class="elevation-10">       
							<v-flex>
								<AppointButton v-for="button in buttons" :key="button.name" :disable="button.disable" :buttonName="button.name" 
                  @emitAppoint="mountAppointment($event)">
                </AppointButton>
							</v-flex>
            </v-card>
          </v-flex>
        </v-flex>
      </v-layout>
    </v-form>
  </v-app>
</template>

<script>
import AppointButton from '@/components/shared/AppointButton.vue'

export default {
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    empty: '--:--',
    today: '',
    buttons: [
      { name: 'Entrada Manhã', disable: false },
      { name: 'Saída Manhã', disable: true },
      { name: 'Entrada Tarde', disable: false },
      { name: 'Saída Tarde', disable: true },
      { name: 'Entrada Noite', disable: false },
      { name: 'Saída Noite', disable: true },
      { name: 'Saída Partícular', disable: true }
    ],
    headers: [
      { text: 'Entrada Manhã', align: 'center', sortable: false },
      { text: 'Saída Manhã', align: 'center', sortable: false },
      { text: 'Entrada Tarde', align: 'center', sortable: false },
      { text: 'Saída Tarde', align: 'center', sortable: false },
      { text: 'Entrada Noite', align: 'center', sortable: false },
      { text: 'Saída Noite', align: 'center', sortable: false },
      { text: 'Saída Partícular', align: 'center', sortable: false },
      { text: 'Retorno', align: 'center', sortable: false }
    ],
    appointment: {},
    employee: {id: 1},
    appointments: []
  }),
  components: {
    AppointButton
  },
  computed: {
  },
  watch: {
  },
  created () {
    this.callApi()
  },
  methods: {
    mountAppointment (button) {
      let date = new Date()
      let time = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
      time += (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes())
      if (button === 'Entrada Manhã') {
        alert(button)
        this.appointment.morningEntrance = time
      } else if (button === 'morningOut') {
        this.appointment.morningOut = time
      } else if (button === 'afternoonEntrance') {
        this.appointment.afternoonEntrance = time
      } else if (button === 'afternoonOut') {
        if (!this.appointment.morningOut) {
          this.appointment.morningOut = '12:00'
          this.appointment.afternoonEntrance = '12:00'
        }
        this.appointment.afternoonOut = time
      } else if (button === 'nightEntrance') {
        this.appointment.nightEntrance = time
      } else if (button === 'nightOut') {
        if (!this.appointment.morningOut && this.appointment.morningEntrance) {
          this.appointment.morningOut = '12:00'
          this.appointment.afternoonEntrance = '12:00'
        }
        if (!this.appointment.afternoonOut && !this.appointment.nightEntrance) {
          this.appointment.afternoonOut = '18:00'
          this.appointment.nightEntrance = '18:00'
        }
        this.appointment.nightOut = time
      } else if (button === 'particularExit') {
        if (!this.appointment.particularExit) {
          this.appointment.particularExit = time
        } else {
          this.appointment.particularExitReturn = time
        }
      }
      this.verifyButtons()
      alert('j')
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
      alert()
    },
    registerAppointments () {
      alert('aa')
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
          this.today = '01 de Outubro de 2018'// this.appointment.date
          this.verifyButtons()
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

<style scoped>
  h1 {
    font-size: 40px;
    text-align: center;
    margin-top: -20px;
  }
</style>
