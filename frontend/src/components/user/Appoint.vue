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
                <td class="text-xs-center" >{{ props.item.morningEntrance || empty }} 
                  <v-dialog v-model="headers[0].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.morningEntrance || empty"
                      type="Entrada Manhã" @editAppoint="editAppointment($event, 0)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.morningOut || empty }}
                  <v-dialog v-model="headers[1].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.morningOut || empty"
                      type="Saída Manhã" @editAppoint="editAppointment($event, 1)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.afternoonEntrance || empty }}
                  <v-dialog v-model="headers[2].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.afternoonEntrance || empty"
                      type="Entrada Tarde" @editAppoint="editAppointment($event, 2)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.afternoonOut || empty }}
                  <v-dialog v-model="headers[3].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.afternoonOut || empty"
                      type="Saída Tarde" @editAppoint="editAppointment($event, 3)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.nightEntrance || empty }}
                  <v-dialog v-model="headers[4].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.nightEntrance || empty"
                      type="Entrada Noite" @editAppoint="editAppointment($event, 4)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.nightOut || empty }}
                  <v-dialog v-model="headers[5].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.nightOut || empty"
                      type="Saída Noite" @editAppoint="editAppointment($event, 5)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.particularExit || empty }}
                  <v-dialog v-model="headers[6].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.particularExit || empty"
                      type="Saída Partícular" @editAppoint="editAppointment($event, 6)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.particularExitReturn || empty }}
                  <v-dialog v-model="headers[7].dialog" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.particularExitReturn || empty"
                      type="Retorno" @editAppoint="editAppointment($event, 7)"></AppointDialog>
                  </v-dialog>
                </td>
              </tr>
            </template>
          </v-data-table>
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
  </v-app>
</template>

<script>
import AppointButton from '@/components/shared/AppointButton.vue'
import AppointDialog from '@/components/shared/AppointDialog.vue'

export default {
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    empty: '--:--',
    today: '',
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
    headers: [
      { text: 'Entrada Manhã', align: 'center', sortable: false, dialog: false },
      { text: 'Saída Manhã', align: 'center', sortable: false, dialog: false },
      { text: 'Entrada Tarde', align: 'center', sortable: false, dialog: false },
      { text: 'Saída Tarde', align: 'center', sortable: false, dialog: false },
      { text: 'Entrada Noite', align: 'center', sortable: false, dialog: false },
      { text: 'Saída Noite', align: 'center', sortable: false, dialog: false },
      { text: 'Saída Partícular', align: 'center', sortable: false, dialog: false },
      { text: 'Retorno', align: 'center', sortable: false, dialog: false }
    ],
    appointment: {},
    employee: {id: 1},
    appointments: []
  }),
  components: {
    AppointButton,
    AppointDialog
  },
  computed: {
  },
  watch: {
  },
  created () {
    this.callApi()
  },
  methods: {
    editAppointment (value, index) {
      if (value !== false) {
        this.mountAppointment(this.headers[index].text, value)
      }
      this.headers[index].dialog = false
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
