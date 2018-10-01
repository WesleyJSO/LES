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
                    class="elevation-10" >
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
								<v-btn small
                  :disabled="button1"
									outline
									flat
									color="primary" 
                  @click="mountAppointment('morningEntrance')">
									Entrada Manhã
								</v-btn>
								<v-btn small
                  :disabled="button2"
									outline
									flat
									color="primary"
                  @click="mountAppointment('morningOut')" >
									Saida Manhã
								</v-btn>
								<v-btn small
                  :disabled="button3"
									outline
									flat
									color="primary"
                  @click="mountAppointment('afternoonEntrance')" >
									Entrada Tarde
								</v-btn>
								<v-btn small
                  :disabled="button4"
									outline
									flat
									color="primary"
                  @click="mountAppointment('afternoonOut')" >
									Saida Tarde
								</v-btn>
								<v-btn small
                  :disabled="button5"
									outline
									flat
									color="primary"
                  @click="mountAppointment('nightEntrance')" >
									Entrada Noite
								</v-btn>
								<v-btn small
                  :disabled="button6"
									outline
									flat
									color="primary"
                  @click="mountAppointment('nightOut')" >
									Saida Noite
								</v-btn>
								<!--<v-btn small
									outline
									flat
									color="primary" >
									<v-icon>touch_app</v-icon>
								</v-btn>-->
								<v-btn small
                  :disabled="button7"
									outline
									flat
									color="primary"
                  @click="mountAppointment('particularExit')" >
									Saida Particular
								</v-btn>
							</v-flex>
            </v-card>
          </v-flex>
        </v-flex>
      </v-layout>
    </v-form>
  </v-app>
</template>

<script>

export default {
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    empty: '--:--',
    today: '',
    button1: false,
    button2: true,
    button3: false,
    button4: true,
    button5: false,
    button6: true,
    button7: true,
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
      if (button === 'morningEntrance') {
        this.appointment.morningEntrance = time
        this.button2 = this.button4 = this.button6 = this.button7 = false
        this.button3 = this.button5 = true
      } else if (button === 'morningOut') {
        this.appointment.morningOut = time
        this.button3 = this.button5 = false
        this.button1 = this.button4 = this.button6 = this.button7 = true
      } else if (button === 'afternoonEntrance') {
        this.appointment.afternoonEntrance = time
        this.button1 = this.button2 = this.button5 = true
        this.button4 = this.button6 = this.button7 = false
      } else if (button === 'afternoonOut') {
        if (!this.appointment.morningOut) {
          this.appointment.morningOut = '12:00'
          this.appointment.afternoonEntrance = '12:00'
        }
        this.appointment.afternoonOut = time
        this.button1 = this.button2 = this.button3 = this.button6 = this.button7 = true
        this.button5 = false
      } else if (button === 'nightEntrance') {
        this.appointment.nightEntrance = time
        this.button1 = this.button2 = this.button3 = this.button4 = true
        this.button6 = this.button7 = false
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
        this.button1 = this.button2 = this.button3 = this.button4 = this.button7 = this.button5 = true
      } else if (button === 'particularExit') {
        if (!this.appointment.particularExit) {
          this.appointment.particularExit = time
        } else {
          this.appointment.particularExitReturn = time
        }
      }
      this.registerAppointments()
    },
    verifyButton (button) {
      if (this.appointment.id) {
        this.button1 = true
      }
      if (this.appointment.morningEntrance) {
        this.button2 = this.button4 = this.button6 = this.button7 = false
      }
      if (this.appointment.afternoonOut) {
        this.button3 = true
      }
      if (this.appointment.afternoonEntrance) {
        this.button2 = true
        this.button4 = this.button6 = false
      }
      if (this.appointment.nightOut) {
        this.button5 = this.button7 = true
      }
      if (this.appointment.nightEntrance) {
        this.button6 = false
        this.button4 = true
      }
      if (this.appointment.particularExit) {
        this.button6 = true
      }
      if (this.appointment.particularExitReturn) {
        this.button6 = false
      }
    },
    verifyButtons () {
      if (this.appointment.id) {
        this.button1 = true
      }
      if (this.appointment.morningEntrance) {
        this.button2 = this.button4 = this.button6 = this.button7 = false
      }
      if (this.appointment.afternoonOut) {
        this.button3 = true
      }
      if (this.appointment.afternoonEntrance) {
        this.button2 = true
        this.button4 = this.button6 = false
      }
      if (this.appointment.nightOut) {
        this.button5 = this.button7 = true
      }
      if (this.appointment.nightEntrance) {
        this.button6 = false
        this.button4 = true
      }
      if (this.appointment.particularExit) {
        this.button6 = true
      }
      if (this.appointment.particularExitReturn) {
        this.button6 = false
      }
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
          this.today = this.appointment.date
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
    margin-bottom: 20px;
  }
</style>
