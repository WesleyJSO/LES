<template>
    <v-form>
      <v-layout>
      <!-- Row 1 -->
        <v-flex center >
          <v-data-table :headers="headers"
                    :items="appointments"
                    no-data-text="Nenhum registro foi econtrados"
                    item-key="id"
                    hide-actions
                    class="elevation-10 form-container" >

            <template slot="items" slot-scope="props">
              <tr @click="props.expanded = !props.expanded">
                <td class="text-xs-center" >{{ props.item.date }}</td>
                <td class="text-xs-center" v-if="editable">
                  <v-dialog v-model="dialogs[props.index * 8 + 0].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.morningEntrance || empty }}</a>
                    <AppointDialog id="fieldMorningEntrance"
                      :value="props.item.morningEntrance || empty"
                      :appointment="props.item"
                      @replacementValue="emitTime($event)"
                      type="Entrada Manhã" @editAppoint="editAppointment($event, props.index * 8 + 0)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" v-else>{{ props.item.morningEntrance || empty }}
                <td class="text-xs-center" v-if="editable">
                  <v-dialog v-model="dialogs[props.index * 8 + 1].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.morningOut || empty }}</a>
                    <AppointDialog d="fieldMorningOut"
                      :value="props.item.morningOut || empty"
                      :appointment="props.item"
                      @replacementValue="emitTime($event)"
                      type="Saída Manhã" @editAppoint="editAppointment($event, props.index * 8 + 1)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" v-else>{{ props.item.morningOut || empty }}
                <td class="text-xs-center" v-if="editable">
                  <v-dialog v-model="dialogs[props.index * 8 + 2].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.afternoonEntrance || empty }}</a>
                    <AppointDialog d="fieldAfternoonEntrance"
                      :value="props.item.afternoonEntrance || empty"
                      :appointment="props.item"
                      @replacementValue="emitTime($event)"
                      type="Entrada Tarde" @editAppoint="editAppointment($event, props.index * 8 + 2)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" v-else>{{ props.item.afternoonEntrance || empty }}
                <td class="text-xs-center" v-if="editable">
                  <v-dialog v-model="dialogs[props.index * 8 + 3].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.afternoonOut || empty }}</a>
                    <AppointDialog :value="props.item.afternoonOut || empty"
                      :appointment="props.item"
                      @replacementValue="emitTime($event)"
                      type="Saída Tarde" @editAppoint="editAppointment($event, props.index * 8 + 3)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" v-else>{{ props.item.afternoonOut || empty }}
                <td class="text-xs-center" v-if="editable">
                  <v-dialog v-model="dialogs[props.index * 8 + 4].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.nightEntrance || empty }}</a>
                    <AppointDialog :value="props.item.nightEntrance || empty"
                      :appointment="props.item"
                      @replacementValue="emitTime($event)"
                      type="Entrada Noite" @editAppoint="editAppointment($event, props.index * 8 + 4)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" v-else>{{ props.item.nightEntrance || empty }}
                <td class="text-xs-center" v-if="editable">
                  <v-dialog v-model="dialogs[props.index * 8 + 5].value" max-width="300px" max-height="300px">
                    <a slot="activator">{{ props.item.nightOut || empty }}</a>
                    <AppointDialog :value="props.item.nightOut || empty"
                      :appointment="props.item"
                      @replacementValue="emitTime($event)"
                      type="Saída Noite" @editAppoint="editAppointment($event, props.index * 8 + 5)"></AppointDialog>
                  </v-dialog>
                </td>
                <td class="text-xs-center" v-else>{{ props.item.nightOut || empty }}
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
                    <v-dialog v-if="editable" v-model="dialogs[props.index * 8 + 6].value" max-width="300px" max-height="300px">
                      <a slot="activator">{{props.item.particularExit || empty}}</a>
                      <AppointDialog :value="props.item.particularExit || empty"
                        :appointment="props.item"
                        @replacementValue="emitTime($event)"
                        type="Saída Partícular" @editAppoint="editAppointment($event, props.index * 8 + 6)"></AppointDialog>
                    </v-dialog>
                    <span class="text-xs-center" v-else>{{props.item.particularExit || empty}}</span>
                  </v-card-text>
                  </v-flex>
                  <v-flex xs12 sm9 md6 lg6 xl4>
                    <v-card-text>Retorno: 
                      <v-dialog v-if="editable" v-model="dialogs[props.index * 8 + 7].value" max-width="300px" max-height="300px">
                        <a slot="activator">{{props.item.particularExitReturn || empty}}</a>
                        <AppointDialog :value="props.item.particularExitReturn || empty"
                          :appointment="props.item"
                          @replacementValue="emitTime($event)"
                          type="Retorno" @editAppoint="editAppointment($event, props.index * 8 + 7)"></AppointDialog>
                      </v-dialog>
                      <span class="text-xs-center" v-else>{{props.item.particularExit || empty}}</span>
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

          </v-data-table>
        </v-flex>
      </v-layout>
    </v-form>
</template>

<script>
import AppointDialog from '@/components/shared/AppointDialog.vue'
import DateHelper from '../../helpers/DateHelper'
import TimeHelper from '../../helpers/TimeHelper'

export default {
  props: {
    appointments: Array,
    editable: true
  },
  data: () => ({
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
    appointment: {}
  }),
  components: {
    AppointDialog
  },
  computed: {
  },
  watch: {
    appointments () {
      this.createDialogs()
      this.formatDatesAndTimes()
    }
  },
  methods: {
    formatDatesAndTimes () {
      if (this.appointments.length !== 0) {
        this.appointments.forEach(element => {
          element.date = DateHelper.formatShortDate(element.date)
          element.morningEntrance = TimeHelper.formatTime(element.morningEntrance)
          element.morningOut = TimeHelper.formatTime(element.morningOut)
          element.afternoonEntrance = TimeHelper.formatTime(element.afternoonEntrance)
          element.afternoonOut = TimeHelper.formatTime(element.afternoonOut)
          element.nightEntrance = TimeHelper.formatTime(element.nightEntrance)
          element.nightOut = TimeHelper.formatTime(element.nightOut)
          element.particularExit = TimeHelper.formatTime(element.particularExit)
          element.particularExitReturn = TimeHelper.formatTime(element.particularExitReturn)
          element.hoursLeft = TimeHelper.formatTime(element.hoursLeft)
          element.dayOvertime = TimeHelper.formatTime(element.dayOvertime)
        })
      }
    },
    createDialogs () {
      this.dialogs = []
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
        appointment.date = DateHelper.formatISODate(appointment.date)
        this.appointment = appointment
        this.appointment.employee = null
        this.$emit('register', this.appointment)
      }
      this.dialogs[index].value = false
    },
    emitTime (value) {
      this.$emit('replacement', value)
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

