<template>
  <div>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="white">
      <v-toolbar-title>{{ getTitles.employees }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field v-model="searchFilter"
                    append-icon="search"
                    :label="getLabels.search"
                    single-line
                    hide-details>
      </v-text-field>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
      <v-card>
        <v-toolbar dark color="black">
          <v-toolbar-title>{{getTitleEdit}}</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
              <v-btn icon dark @click="close">
                <v-icon>close</v-icon>
              </v-btn>
            </v-toolbar-items>
        </v-toolbar>
        <v-card-text>
            <register-user :editedEmployee="editedItem"
                           :edit="isEditing"
                           @messages="handleMessages($event)"
                           @closeDialog="handleCloseDialog">
            </register-user>
          </v-card-text>
      </v-card>
    </v-dialog>
    </v-toolbar>
    <v-data-table :headers="getHeaders"
                  :items="getEmployees"
                  item-key="id"
                  hide-actions
                  :no-data-text="getMessages.emptyDataTable"
                  :search="searchFilter"
                  class="elevation-2" >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.name  + ' ' + props.item.lastName }}</td>
          <td class="text-xs-center">{{ props.item.baseHourCalculation ? props.item.baseHourCalculation.workload : 'Não se aplica'}}</td>
          <td class="text-xs-center">{{ props.item.baseHourCalculation ? props.item.baseHourCalculation.salary : 'Não se aplica'}}</td>
          <td class="text-xs-center">{{ props.item.baseHourCalculation.hasOwnProperty('hourType') ? props.item.baseHourCalculation.hourType.description : 'Não se aplica'}}</td>
          <td class="text-xs-center">{{ props.item.telephoneList.length !== 0 ? props.item.telephoneList[0].number : '-'}}</td>
          <td class="text-xs-center">{{ props.item.user.email }}</td>
          <td class="justify-center layout px-0">
            <v-icon class="mr-2"
                    @click.stop="editItem(props.item)">remove_red_eye
            </v-icon>
            <v-icon class="mr-2"
                    @click.stop="deleteEmployee(props.item)">delete
            </v-icon>
          </td>
        </tr>
      </template>
      <template slot="expand" slot-scope="props">
        <v-data-table :headers="getSubHeaders"
                      :items="[...props.item].splice(0, 6)"
                      hide-actions
                      dark
                      class="elevation-1" >
          <template slot="items" slot-scope="props">
            <td class="text-xs-center">{{ props.item.pis }}</td>
            <td class="text-xs-center">{{ props.item.joiningDate.substring(0, 10).replace(/-/g, '/') }}</td>
            <td class="text-xs-center">{{ props.item.telephoneList.length > 1 ? props.item.telephoneList[1].number : '-' }}</td>
            <td class="text-xs-center">{{ props.item.telephoneList.length === 3 ? props.item.telephoneList[2].number : '-' }}</td>
          </template>
        </v-data-table>
      </template>
    </v-data-table>
  </div>
</template>

<script>
import RegisterUser from '@/components/admin/RegisterUser'
import AdminService from '@/service/AdminService'
import Authenticator from '@/service/Authenticator'
import DateHelper from '@/helpers/DateHelper'

export default {
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    searchFilter: '',
    editedIndex: -1,
    dialog: false,
    manageHoursDialog: false,
    isEditing: false,
    editedItem: {},
    editTitle: 'Colaborador',
    itemToShow: {},
    employees: [],
    isAdmin: false
  }),
  mounted () {
    this.listEmployees()
    this.isAdmin = Authenticator.HAS_ROLE('ROLE_ADMIN')
  },
  computed: {
    getHeaders () {
      return AdminService.HEADERS
    },
    getSubHeaders () {
      return AdminService.SUB_HEADERS
    },
    getMessages () {
      return AdminService.MESSAGES
    },
    getTitles () {
      return AdminService.TITLES
    },
    getLabels () {
      return AdminService.LABELS
    },
    getEmployees () {
      return this.employees
    },
    getDateFormats () {
      return AdminService.DATE_FORMATS
    },
    getTitleEdit () {
      return this.editTitle
    }
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  methods: {
    parseDate (date) {
      return DateHelper.formatShortDate(date)
    },
    async listEmployees () {
      let user = {id: Authenticator.GET_AUTHENTICATED().id}
      let manager = {'user': user}
      try {
        let result = await this.$_axios.patch(`${this.$_url}employee`, {manager})
        let resultMessage = !result.data.message ? [] : result.data.message
        if (resultMessage) {
          this.messages = [...resultMessage]
          this.haveMessage = resultMessage.length > 0
        }
        if (result.data.success) {
          this.employees = [...result.data.resultList]
          this.employees = this.parseEmployees(this.employees)
          this.messageColor = 'info'
        } else {
          this.messageColor = 'warning'
        }
      } catch (err) {
        console.log(JSON.stringify(err, null, ''))
        this.messages = ['Houve um erro ao Consultar os Colaboradores']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    editItem (item) {
      // Need to format date as 'year-month-date'
      // to avoid refactor all register user component code
      const [day, month, year] = item.joiningDate.replace(/\//g, '-').substring(0, 10).split('-')
      let parseDate = `${year}-${month}-${day}`
      this.editedItem = JSON.parse(JSON.stringify(item))
      this.editedItem.joiningDate = parseDate
      this.editedItem.user.password = ''
      this.editedItem.baseHourCalculation.hourType = {id: this.editedItem.baseHourCalculation.hourType.id}
      this.editTitle = 'Colaborador'
      this.dialog = true
      this.isEditing = true
    },
    async deleteEmployee (item) {
      try {
        let result = await this.$_axios.delete(`${this.$_url}employee/${item.id}`)
        if (result.data.messages) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.data.success) {
            this.messageColor = 'info'
          } else {
            this.messageColor = 'warning'
          }
        }
        this.listEmployees()
      } catch (error) {
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    close () {
      this.dialog = false
      this.isEditing = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },
    parseEmployees (list) {
      // Assuming that first employee has a full manager Object
      let manager = list[0].manager
      return list.map(e => {
        let newE = e
        if (!e.manager.hasOwnProperty('user')) {
          newE.manager = Object.assign(manager, {})
        }
        return newE
      })
    },
    handleMessages (value) {
      this.messages = [...value]
      this.haveMessage = true
      this.messageColor = 'info'
    },
    handleCloseDialog () {
      this.listEmployees()
      this.dialog = false
    }
  },
  components: {
    RegisterUser
  }
}
</script>
