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
                <td class="text-xs-center" >{{ props.item.morningEntrance || empty }}
                  <v-dialog v-model="dialogs[0].value" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.morningEntrance || empty"
                      :appointment="props.item"
                      type="Entrada Manhã" @editAppoint="editAppointment($event, 0)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.morningOut || empty }}
                  <v-dialog v-model="dialogs[1].value" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.morningOut || empty"
                      :appointment="props.item"
                      type="Saída Manhã" @editAppoint="editAppointment($event, 1)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.afternoonEntrance || empty }}
                  <v-dialog v-model="dialogs[2].value" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.afternoonEntrance || empty"
                      :appointment="props.item"
                      type="Entrada Tarde" @editAppoint="editAppointment($event, 2)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.afternoonOut || empty }}
                  <v-dialog v-model="dialogs[3].value" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.afternoonOut || empty"
                      :appointment="props.item"
                      type="Saída Tarde" @editAppoint="editAppointment($event, 3)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.nightEntrance || empty }}
                  <v-dialog v-model="dialogs[4].value" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.nightEntrance || empty"
                      :appointment="props.item"
                      type="Entrada Noite" @editAppoint="editAppointment($event, 4)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.nightOut || empty }}
                  <v-dialog v-model="dialogs[5].value" max-width="300px" max-height="300px">
                    <v-icon small
                      slot="activator"
                      :key="props.item.index"
                      light>
                      edit
                    </v-icon>
                    <AppointDialog :value="props.item.nightOut || empty"
                      :appointment="props.item"
                      type="Saída Noite" @editAppoint="editAppointment($event, 5)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" >{{ props.item.particularExit ? 'SIM' : 'NÃO' }}
                </td>
              </tr>
            </template>

            <!-- details -->
            <template slot="expand" slot-scope="props" >
              <v-card flat dark >
                <v-layout class="text-xs-center">
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Saída Particular: {{props.item.particularExit || empty}}
                    <v-dialog v-model="dialogs[6].value" max-width="300px" max-height="300px">
                      <v-icon small
                        slot="activator"
                        :key="props.item.index"
                        dark>
                        edit
                      </v-icon>
                      <AppointDialog :value="props.item.particularExit || empty"
                        :appointment="props.item"
                        type="Saída Partícular" @editAppoint="editAppointment($event, 6)"></AppointDialog>
                    </v-dialog>
                  </v-card-text>
                  </v-flex>
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Retorno: {{props.item.particularExitReturn || empty}}
                      <v-dialog v-model="dialogs[7].value" max-width="300px" max-height="300px">
                        <v-icon small
                          slot="activator"
                          :key="props.item.index"
                          dark>
                          edit
                        </v-icon>
                        <AppointDialog :value="props.item.particularExitReturn || empty"
                          :appointment="props.item"
                          type="Retorno" @editAppoint="editAppointment($event, 7)"></AppointDialog>
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
    dialogs: [
      { value: false },
      { value: false },
      { value: false },
      { value: false },
      { value: false },
      { value: false },
      { value: false },
      { value: false }
    ],
    appointment: {},
    employee: {id: 1}
  }),
  components: {
    AppointDialog
  },
  computed: {
  },
  watch: {
  },
  methods: {
    editAppointment (appointment, index) {
      if (appointment !== false) {
        this.appointment = appointment
        this.$emit('register', this.appointment)
      }
      this.dialogs[index].value = false
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

