<template>
  <v-app>
    <h1>{{getAppointments.title}}</h1>
    <br/>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="white">
      <v-toolbar-title>{{getAppointments.table}}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field v-model="searchFilter"
                    append-icon="search"
                    label="Search"
                    single-line
                    hide-details>
      </v-text-field>
      <v-dialog v-model="dialog" max-width="1000px" max-height="300px" >
        <v-card>
          <v-card-text>
            <h3>{{getAppointments.addAppointments}}</h3>
            <br/>
            <!--Add validate method <v-form v-model="valid" @submit.prevent="validateUser"> -->
            <v-form>
              <v-layout>
              <!-- Row 1 -->
                <v-flex xs12 sm9 md6 lg6 xl4>
                  <v-text-field v-model="appointments.entryDate"
                                type="date"
                                class="px-0"
                                prepend-icon="event"
                                clearable
                                label="Data"
                                required>
                  </v-text-field>
                </v-flex>
                <v-flex xs12 sm9 md6 lg6 xl4>
                  <v-text-field v-model="appointments.entryTime"
                                type="time"
                                class="px-0"
                                prepend-icon="alarm"
                                clearable
                                label="Hora"
                                required>
                  </v-text-field>
                </v-flex>
              </v-layout>
              <v-layout>
                <v-flex>
                  <v-combobox v-model="appointments.type"
                              prepend-icon="check_circle"
                              :items="getAppointments.types"
                              label="Tipo"
                              chips>
                    <template slot="selection"
                              slot-scope="data">
                      <v-chip :selected="data.selected"
                              :disabled="data.disabled"
                              :key="JSON.stringify(data.item)"
                              class="v-chip--select-multi"
                              @input="data.parent.selectItem(data.item)">
                        <v-avatar class="accent white--text"
                                  v-text="data.item.slice(0, 1).toUpperCase()">
                        </v-avatar>
                          {{ data.item }}
                      </v-chip>
                    </template>
                  </v-combobox>
                </v-flex>
              </v-layout>
            </v-form>
            <v-spacer></v-spacer>
            <v-btn @click="addAppoint" type="submit" color="success">Salvar</v-btn>
            <v-btn color="error">Cancelar</v-btn>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table :headers="getHeaders"
                  :items="items"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-3" >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.userSystemAccessDate }}</td>
          <td class="text-xs-center">{{ props.item.userAppointmentDate.entry ? props.item.userAppointmentDate.entry : '- - -' }}</td>
          <td class="text-xs-center">{{ props.item.userAppointmentDate.lauch_break ? props.item.userAppointmentDate.lauch_break : '- - -' }}</td>
          <td class="text-xs-center">{{ props.item.userAppointmentDate.return_launch_break ?  props.item.userAppointmentDate.return_launch_break : '- - -' }}</td>
          <td class="text-xs-center">{{ props.item.userAppointmentDate.exit ? props.item.userAppointmentDate.exit : '- - -'}}</td>
          <td class="text-xs-center">{{ props.item.abscence ? 'Sim' : 'Não' }}</td>
          <td class="text-xs-center">{{ props.item.amount_hours }}</td>
        </tr>
      </template>
      <template slot="expand" slot-scope="props">
        <v-data-table :headers="getSubHeaders"
                      :items="props.item.abscence"
                      hide-actions
                      dark
                      class="elevation-1" >
          <template slot="items" slot-scope="props">
            <td class="text-xs-center">{{ props.item.abscence_date }}</td>
            <td class="text-xs-center">{{ props.item.abscente_return ? props.item.abscente_return : '- - -' }}</td>
            <td class="text-xs-center">{{ props.item.description }}</td>
            <td class="text-xs-center">{{ props.item.amount_abscence }}</td>
          </template>
        </v-data-table>
      </template>
    </v-data-table>
        <v-card>
          <v-card-title></v-card-title>
          <v-card-text style="height: 100px; position: absolute">
            <v-fab-transition>
              <v-btn @click="addAppointment()"
                     color="blue"
                     dark
                     absolute
                     top
                     right
                     fab>
                <v-icon>add</v-icon>
              </v-btn>
            </v-fab-transition>
          </v-card-text>
        </v-card>
  </v-app>
</template>

<script>
import UserService from '../../service/UserService'
export default {
  props: {
    items: {
      type: Array,
      default () {
        return []
      }
    },
    appointments: {
      type: Object,
      default () {
        return {}
      }
    }
  },
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    searchFilter: '',
    editedIndex: -1,
    dialog: false,
    isEditing: false,
    searchDate: false
  }),
  computed: {
    getHeaders () {
      return UserService.HEADERS
    },
    getSubHeaders () {
      return UserService.SUB_HEADERS
    },
    getAppointments () {
      return UserService.APPOINTMENTS
    }
    /* itemsToShow () {
      return this.items.map(item => {
        return {
          userSystemAccessDate: item.userSystemAccessDate,
          userAppointmentDate: this.items.filter(filter => {
            return filter.userAppointmentDate === item.userAppointmentDate
          })
        }
      })
    } */
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  created () {
    /* this.$_axios.get(`${this.$_url}funcionario`).then((response) => {
      let resultado = response.data
      if (resultado.listaResultado.length !== 0) {
        // retorno ok
        this.users = resultado.listaResultado
      }
      if (resultado.mensagem) {
        this.messages = [...resultado.mensagem]
        this.haveMessage = true
        if (resultado.sucesso) {
          // retorno mensagem de sucesso
          this.messageColor = 'info'
        } else {
          // retorno mensagem de erro
          this.messageColor = 'warning'
        }
      }
    },
    (response) => {
      console.log(JSON.stringify(response, null, ' '))
      // erro na requisição do serviço
      this.messages = ['Erro durante execução do serviço!']
      this.haveMessage = true
      this.messageColor = 'error'
    }) */

    this.items = [
      {
        id: 1,
        userSystemAccessDate: '22/01/2018',
        userAppointmentDate: {
          entry: '09:00',
          lauch_break: '12:00',
          return_launch_break: '13:00',
          exit: '18:00'
        },
        abscence: [ { abscence_date: '14:00', abscente_return: '17:00', description: 'Médico', amount_abscence: '4:00' } ],
        amount_hours: '5:00'
      },
      {
        id: 2,
        userSystemAccessDate: '23/01/2018',
        userAppointmentDate: {
          entry: '09:00',
          lauch_break: '11:30',
          return_launch_break: '12:30',
          exit: '18:00'
        },
        abscence: null,
        amount_hours: '8:00'
      },
      {
        id: 3,
        userSystemAccessDate: '30/03/2018',
        userAppointmentDate: {
          entry: '09:00',
          lauch_break: '11:00',
          return_launch_break: '12:00',
          exit: ''
        },
        abscence: [ { abscence_date: '13:00', abscente_return: '', description: 'Falta', amount_abscence: '5:00' } ],
        amount_hours: '3:00'
      }
    ]
  },
  methods: {
    initialize () {
    },
    addAppointment () {
      this.dialog = true
    },
    close () {
      this.dialog = false
      this.isEditing = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },
    addAppoint (funcionario) {
      alert('Parent Save', JSON.stringify(funcionario, null, ' '))
      /* this.$_axios.post(`${this.$_url}funcionario`, funcionario).then((response) => {
        let resultado = response.data
        if (resultado.listaResultado.length !== 0) {
          /* retorno ok
          this.funcionario = resultado.listaResultado
        }
        if (resultado.mensagem) {
          this.messages = [...resultado.mensagem]
          this.haveMessage = true
          if (resultado.sucesso) {
          /* retorno mensagem de sucesso
            this.messageColor = 'info'
          } else {
            /* retorno mensagem de erro
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        /* erro na requisição do serviço
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }) */
      if (this.editedIndex > -1) {
        Object.assign(this.users[this.editedIndex], funcionario)
      } else {
        this.users.push(this.funcionario)
      }
      this.close()
    }
  },
  components: {
    // Consultar,
    // Listar
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
  h3 {
    font-size: 35px;
    text-align: center;
    margin-bottom: 20px;
  }
</style>
