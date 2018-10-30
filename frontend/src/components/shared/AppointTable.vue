<template>
    <v-form>
      <v-layout>
      <!-- Row 1 -->
        <v-flex center >
          <v-data-table :headers="headers"
                    :items="appointments"
                    item-key="id"
                    hide-actions
                    class="elevation-10 form-container" >

            <template slot="items" slot-scope="props">
              <tr @click="props.expanded = !props.expanded">
                <td class="text-xs-center" >{{ props.item.date }}</td>
                <td class="text-xs-center" >
                  <v-dialog v-model="dialogs[props.index * 8 + 0].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.morningEntrance || empty }}</a>
                    <AppointDialog :value="props.item.morningEntrance || empty"
                      :appointment="props.item"
                      type="Entrada Manhã" @editAppoint="editAppointment($event, props.index * 8 + 0)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >
                  <v-dialog v-model="dialogs[props.index * 8 + 1].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.morningOut || empty }}</a>
                    <AppointDialog :value="props.item.morningOut || empty"
                      :appointment="props.item"
                      type="Saída Manhã" @editAppoint="editAppointment($event, props.index * 8 + 1)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >
                  <v-dialog v-model="dialogs[props.index * 8 + 2].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.afternoonEntrance || empty }}</a>
                    <AppointDialog :value="props.item.afternoonEntrance || empty"
                      :appointment="props.item"
                      type="Entrada Tarde" @editAppoint="editAppointment($event, props.index * 8 + 2)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >
                  <v-dialog v-model="dialogs[props.index * 8 + 3].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.afternoonOut || empty }}</a>
                    <AppointDialog :value="props.item.afternoonOut || empty"
                      :appointment="props.item"
                      type="Saída Tarde" @editAppoint="editAppointment($event, props.index * 8 + 3)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >
                  <v-dialog v-model="dialogs[props.index * 8 + 4].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.nightEntrance || empty }}</a>
                    <AppointDialog :value="props.item.nightEntrance || empty"
                      :appointment="props.item"
                      type="Entrada Noite" @editAppoint="editAppointment($event, props.index * 8 + 4)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >
                  <v-dialog v-model="dialogs[props.index * 8 + 5].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.nightOut || empty }}</a>
                    <AppointDialog :value="props.item.nightOut || empty"
                      :appointment="props.item"
                      type="Saída Noite" @editAppoint="editAppointment($event, props.index * 8 + 5)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.particularExit ? 'SIM' : 'NÃO' }}
                </td>
              </tr>
            </template>

            <!-- details -->
            <template slot="expand" slot-scope="props" >
              <v-card flat class="subRow" >
                <v-layout class="text-xs-center">
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Saída Particular: 
                    <v-dialog v-model="dialogs[props.index * 8 + 6].value" max-width="300px" max-height="300px">
                      <a slot="activator">{{props.item.particularExit || empty}}</a>
                      <AppointDialog :value="props.item.particularExit || empty"
                        :appointment="props.item"
                        type="Saída Partícular" @editAppoint="editAppointment($event, props.index * 8 + 6)"></AppointDialog>
                    </v-dialog>
                  </v-card-text>
                  </v-flex>
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Retorno: 
                      <v-dialog v-model="dialogs[props.index * 8 + 7].value" max-width="300px" max-height="300px">
                        <a slot="activator">{{props.item.particularExitReturn || empty}}</a>
                        <AppointDialog :value="props.item.particularExitReturn || empty"
                          :appointment="props.item"
                          type="Retorno" @editAppoint="editAppointment($event, props.index * 8 + 7)"></AppointDialog>
                      </v-dialog>
                    </v-card-text>
                  </v-flex>
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Faltas: {{props.item.hoursLeft || '00:00'}}</v-card-text>
                  </v-flex>
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Extras: {{props.item.dayOvertime || '00:00'}}</v-card-text>
                  </v-flex>
                </v-layout>
              </v-card>
            </template>

            <!-- empty message -->
            <template slot="no-data">
              <v-alert :value="true" color="error" icon="warning">
                Não existem valores para o mês selecionado.
              </v-alert>
            </template>

          </v-data-table>
        </v-flex>
      </v-layout>
    </v-form>
</template>

<script>
import AppointDialog from '@/components/shared/AppointDialog.vue'

export default {
  props: {
    appointments: Array
  },
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    empty: '--:--',
    headers: [
      { text: 'Data', align: 'center', sortable: false },
      { text: 'Entrada Manhã', align: 'center', sortable: false },
      { text: 'Saída Manhã', align: 'center', sortable: false },
      { text: 'Entrada Tarde', align: 'center', sortable: false },
      { text: 'Saída Tarde', align: 'center', sortable: false },
      { text: 'Entrada Noite', align: 'center', sortable: false },
      { text: 'Saída Noite', align: 'center', sortable: false },
      { text: 'Saída Partícular', align: 'center', sortable: false }
    ],
    tableItemDialogs: [],
    dialogs: [],
    appointment: {},
    employee: {id: 1}
  }),
  components: {
    AppointDialog
  },
  computed: {
  },
  watch: {
    appointments () {
      this.createDialogs()
    }
  },
  created () {
    this.tableItemDialogs = [this.dialogs]
  },
  methods: {
    createDialogs () {
      this.tableItemDialogs = []
      if (this.appointments.length !== 0) {
        this.appointments.forEach(element => {
          this.dialogs.push({ value: false },
            { value: false },
            { value: false },
            { value: false },
            { value: false },
            { value: false },
            { value: false },
            { value: false })
        })
      }
    },
    editAppointment (appointment, index) {
      if (appointment !== false) {
        this.appointment = appointment
        this.$emit('register', this.appointment)
      }
      this.dialogs[index].value = false
    },
    closeDialog (appointment, index) {
      this.appointments.forEach(element => {
        if (element.id === appointment.id) {
          alert(JSON.stringify(element))
          element.dialogs[index].value = false
          alert(JSON.stringify(element))
        }
      })
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
      this.$emit('register', this.appointment)
    }
  }
}
</script>

<style scoped>
  a {
    color: black
  }
  .subRow {
    background-color: lightgrey
  }
</style>

