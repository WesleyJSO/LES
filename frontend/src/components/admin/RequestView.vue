<template>
  <div>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="white">
      <v-toolbar-title>{{ this.getTitles.requests }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field v-model="searchFilter"
                    append-icon="search"
                    label="Search"
                    single-line
                    hide-details>
      </v-text-field>
    </v-toolbar>
    <template>
    
    <v-tabs fixed-tabs
            color="black"
            dark
            v-model="active"
            class="elevation-10">
        <v-tab>{{ this.getTitles.pending }}</v-tab>
        <v-tab>{{ this.getTitles.approved }}</v-tab>
    <v-tabs-items v-model="tabs">
      <v-tab-item key="1">
        <v-data-table :headers="getHeadersAdmin"
                  :items="getRequests"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-10" >
        <template slot="items" slot-scope="props">
            <td>{{ props.item.employee.name }} {{ props.item.employee.lastName }}</td>
            <td class="text-xs-left">{{ getType (props.item.type) }}</td>
            <td class="text-xs-left">{{ getStatus(props.item.status) }}</td>
            <td class="text-xs-center" v-if="getUser.id === props.item.id">
            <v-icon
                  :key="props.item.id"
                  slot="activator"
                  light
                  @click.stop="editItem(props.item)">
                  edit
            </v-icon>
            <v-dialog v-model="dialogEdit" 
                      @keydown.esc="dialogEdit = !dialogEdit">
              <v-card>
                <v-card-title class="headline grey lighten-2"
                              primary-title >
                    {{ getTitles.edit }}
                </v-card-title>
                <v-card-text >
                  <request :item="request"
                           :edit="true"
                           @onClose="finishEdit($event)">
                  </request>
                </v-card-text>
              </v-card>
            </v-dialog>
            </td>
            <td class="text-xs-center">
                <v-dialog v-model="dialogRequests[props.item.id]" 
                          max-width="600px" 
                          max-height="300px"
                          @keydown.esc="$set(dialogRequests, props.item.id, false)">
                <v-icon
                    slot="activator"
                    :key="props.item.id"
                    light>
                    {{getIcon}}
                </v-icon>

                <v-card>
                    <v-card-title
                    class="headline grey lighten-2"
                    primary-title >
                        {{ getPrefixes.dialogTitle }} {{ getType(props.item.type) }}
                    </v-card-title>

                    <v-card-text >
                        <b>{{ getTitles.employee }} </b>
                        {{ props.item.employee.name }} {{ props.item.employee.lastName }}
                    </v-card-text>

                    <v-card-text>
                        <b>{{ getTitles.description }}</b>
                        <br>
                        {{ props.item.description }}
                    </v-card-text>

                    <!-- Work Over Time or Compensatory Time Request-->
                    <!-- All attributes has values -->
                    <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && props.item.endDate">
                        <b>{{ getPrefixes.begin }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <br>
                        <b>{{ getPrefixes.end }}</b>
                        {{ props.item.endDate}}
                    </v-card-text>

                    <!-- There is no 'endDate' -->
                    <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && !props.item.endDate">
                        <b>{{ getTitles.entryDate }}</b>
                        {{ props.item.startDate}}
                    </v-card-text>

                    <!-- Request type equals 3' -->
                    <v-card-text v-if="(props.item.type === 3)">
                        <b>{{ getTitles.appointmentDate }}</b>
                        {{ props.item.startDate}}
                        <br><br>
                        <b>{{getTitles.appointmentField}}</b>
                        <br>
                        {{getFieldToChangeName[props.item.fieldToChange]}} - <b> {{props.item.previousValue ? `de ${props.item.previousValue.substring(0, 5)} para ${props.item.replacement.substring(0, 5)}` : props.item.replacement.substring(0, 5) }}</b>
                    </v-card-text>

                    <!-- There is no File -->
                    <!-- <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && !props.item.endDate && !props.item.attachment">
                        <b>{{ getTitles.entryDate }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <br>
                        <b>{{ getTitles.endDate }}</b>
                        {{ props.item.endDate}}
                    </v-card-text> -->

                    <!-- Realocation Days Requests -->
                    <!-- All attributes -->
                    <v-card-text v-if="props.item.type === 4">
                        <b>{{ getTitles.rangeDates }}</b>
                        <br>
                        <b>{{ getTitles.tookDayOff }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <b>{{ getTitles.workDay }}</b>
                        {{ props.item.endDate}}
                    </v-card-text>

                    <!-- There is no  File -->
                    <!--<v-card-text v-if="props.item.type === 3">
                        <b>{{ getTitles.rangeDates }}</b>
                        <br>
                        <b>{{ getTitles.tookDayOff }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <b>{{ getTitles.workDay }}</b>
                        {{ props.item.endDate}}
                    </v-card-text> -->

                    <v-card-text v-if="hasRole('ROLE_MANAGER')" >
                      <b>{{ getLabels.managerMessage }}</b>
                      <v-textarea v-model="props.item.observation"
                          box
                          outline
                          name="input-7-4"
                          :label="getTitles.managerMessage">
                      </v-textarea>
                    </v-card-text>

                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn v-if="hasRole('ROLE_MANAGER')"
                        color="green"
                        flat
                        @click.stop="approveRequest(props.item, getStatusValue.approved), $set(dialogRequests, props.item.id, false)">
                        {{ getButtons.approve }}
                    </v-btn>
                    <v-btn v-if="hasRole('ROLE_MANAGER')"
                        color="red"
                        flat
                        @click.stop="approveRequest(props.item, getStatusValue.denied), $set(dialogRequests, props.item.id, false)">
                        {{ getButtons.deny }}
                    </v-btn>
                    <v-btn v-if="hasRole('ROLE_EMPLOYEE')"
                        color="green"
                        flat
                        @click.stop="$set(dialogRequests, props.item.id, false)">
                        {{ getButtons.close }}
                    </v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </td>
          </tr>
        </template>
        </v-data-table>
      </v-tab-item>
      <v-tab-item :key="2">
        <v-data-table :headers="getHeadersProccesseds"
                  :items="getProcessed"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-2" >
        <template slot="items" slot-scope="props">
          <tr @onclick="props.expanded = !props.expanded">
            <td>{{ props.item.employee.name }} {{ props.item.employee.lastName }}</td>
            <td class="text-xs-left">{{ getType(props.item.type) }}</td>
            <td class="text-xs-left" font-color="green">{{ getStatus(props.item.status) }}</td>
            <td class="text-xs-center">
                <v-dialog v-model="dialogRequests[props.item.id]"
                          max-width="600px"
                          max-height="300px"
                          @keydown.esc="$set(dialogRequests, props.item.id, false)">
                <v-icon
                    slot="activator"
                    :key="props.item.id"
                    light>
                    remove_red_eye
                </v-icon>

                <v-card>
                    <v-card-title
                    class="headline grey lighten-2"
                    primary-title>
                        {{ getType(props.item.type) }} - {{ getStatus(props.item.status) }}
                    </v-card-title>

                    <v-card-text>
                        <b>{{ getTitles.employee }} </b>
                        {{ props.item.employee.name }} {{ props.item.employee.lastName }}
                    </v-card-text>

                    <v-card-text>
                        <b>{{ getTitles.description }}</b>
                        <br>
                        {{ props.item.description }}
                    </v-card-text>

                    <!-- Work Over Time or Compensatory Time Request-->
                    <!-- All attributes has values -->
                    <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && props.item.endDate">
                        <b>{{ getPrefixes.begin }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <br>
                        <b>{{ getPrefixes.end }}</b>
                        {{ props.item.endDate}}
                    </v-card-text>

                    <!-- There is no 'endDate' -->
                    <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && !props.item.endDate">
                        <b>{{ getTitles.entryDate }}</b>
                        {{ props.item.startDate}}
                    </v-card-text>

                    <!-- Request type equals 3' -->
                    <v-card-text v-if="(props.item.type === 3)">
                        <b>{{ getTitles.appointmentDate }}</b>
                        {{ props.item.startDate}}
                        <br><br>
                        <b>{{getTitles.appointmentField}}</b>
                        <br>
                        {{getFieldToChangeName[props.item.fieldToChange]}} - <b> {{props.item.previousValue ? `de ${props.item.previousValue.substring(0, 5)} para ${props.item.replacement.substring(0, 5)}` : props.item.replacement.substring(0, 5) }}</b>
                    </v-card-text>

                    <!-- There is no File -->
                    <!-- <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && !props.item.endDate && !props.item.attachment">
                        <b>{{ getTitles.entryDate }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <br>
                        <b>{{ getTitles.endDate }}</b>
                        {{ props.item.endDate}}
                    </v-card-text> -->

                    <!-- Realocation Days Requests -->
                    <!-- All attributes -->
                    <v-card-text v-if="props.item.type === 4">
                        <b>{{ getTitles.rangeDates }}</b>
                        <br>
                        <b>{{ getTitles.tookDayOff }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <b>{{ getTitles.workDay }}</b>
                        {{ props.item.endDate}}

                    </v-card-text>

                    <!-- There is no  File -->
                    <!--<v-card-text v-if="props.item.type === 3">
                        <b>{{ getTitles.rangeDates }}</b>
                        <br>
                        <b>{{ getTitles.tookDayOff }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <b>{{ getTitles.workDay }}</b>
                        {{ props.item.endDate}}
                    </v-card-text> -->

                    <!-- Last Update -->
                     <v-card-text v-if="props.item.updatedDate">
                        <b>{{ getTitles.lastUpdate }}</b>
                        
                        {{ props.item.updatedDate}}
                    </v-card-text>

                    <!-- Manager Name -->
                     <v-card-text v-if="props.item.managerName">
                        <b>{{ getTitles.approvedManager }}</b>
                        {{ props.item.managerName}}
                    </v-card-text>

                    <!-- Manager Observation -->
                     <v-card-text v-if="props.item.observation">
                        <b>{{ getTitles.viewManagerMessage }}</b>
                        <br>
                        {{ props.item.observation}}
                    </v-card-text>

                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="green"
                        flat
                        @click.stop="$set(dialogRequests, props.item.id, false)">
                        {{ getButtons.close }}
                    </v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </td>
          </tr>
        </template>
        </v-data-table>
      </v-tab-item>
    </v-tabs-items>
        </v-tabs>
    </template>
  </div>
</template>

<script>
import RequestService from '@/service/RequestService'
import Request from '@/components/user/Request'
import Authenticator from '@/service/Authenticator'
import UserService from '@/service/UserService'

export default {
  data () {
    return {
      dialogApproved: {},
      dialogRequests: {},
      dialogEdit: false,
      messageDialog: false,
      managerMessage: '',
      messages: [],
      haveMessage: false,
      searchFilter: '',
      active: 0,
      request: {},
      tabs: 0,
      requests: [],
      processedRequests: [],
      edit: false
    }
  },
  mounted () {
    this.findRequests()
  },
  computed: {
    getHeaders () {
      return RequestService.HEADERS
    },
    getHeadersProccesseds () {
      return RequestService.HEADERS_APPROVED
    },
    getHeadersAdmin () {
      return RequestService.HEADERS_ADMIN
    },
    getSubHeaders () {
      return RequestService.SUB_HEADERS
    },
    getTitles () {
      return RequestService.TITLES
    },
    getLabels () {
      return RequestService.LABELS
    },
    getPrefixes () {
      return RequestService.PREFIXES
    },
    getButtons () {
      return RequestService.BUTTONS
    },
    getProcessed () {
      return this.processedRequests
    },
    getRequests () {
      return this.requests
    },
    getEditedItem () {
      return this.request
    },
    getIcon () {
      return this.hasRole('ROLE_MANAGER') ? 'edit' : 'remove_red_eye'
    },
    getStatusValue () {
      return RequestService.STATUS_VALUE
    },
    getMessages () {
      return RequestService.MESSAGE
    },
    getFieldToChangeName () {
      return UserService.FIELD_TO_CHANGE
    }
  },
  methods: {
    async approveRequest (item, status) {
      try {
        let i = Object.assign({}, item)
        delete i.status
        delete i.employee.user
        delete i.employee.manager
        let updatedRequest = Object.assign({'status': status, 'observation': item.observation}, i)
        let controller = updatedRequest.type === 3 ? 'appointmentRequest' : 'request'
        let result = this.$_axios.put(`${this.$_url}${controller}`, updatedRequest)
        let [response] = await Promise.all([result])
        if (response.data.message) {
          let resultMessage = !result.response.data.message ? [] : result.response.data.message
          this.messages = [...resultMessage]
          this.haveMessage = resultMessage.length > 0
          if (result.response.data.success) {
            this.messageColor = 'info'
          } else {
            this.messageColor = 'warning'
          }
        }
      } catch (err) {
        console.log(JSON.stringify(err, null, ''))
        // this.messages = [this.getMessages.approveError]
        this.haveMessage = false
        this.messageColor = 'error'
      }
      this.findRequests()
    },
    sendBack () {
    },
    getType (item) {
      return RequestService.TYPE_NAME[item]
    },
    getStatus (item) {
      return RequestService.STATUS[item]
    },
    hasRole (role) {
      return Authenticator.HAS_ROLE(role)
    },
    getUser () {
      return Authenticator.GET_AUTHENTICATED
    },
    editItem (item) {
      this.request = Object.assign({}, item)
      this.edit = true
      console.log(JSON.stringify(this.request, null, ''))
      this.dialogEdit = true
    },
    finishEdit (event) {
      this.messages = [...event]
      this.messageColor = 'info'
      this.dialogEdit = !this.dialogEdit
      this.findRequests()
    },
    async findRequests () {
      let user = {id: Authenticator.GET_AUTHENTICATED().id}
      let emp = Object.assign({}, {})
      if (this.hasRole('ROLE_MANAGER')) {
        let manager = Object.assign({'user': user}, {})
        emp = Object.assign({'manager': manager}, {})
      } else {
        emp = Object.assign({'user': user}, {})
      }
      try {
        let pending = this.$_axios.patch(`${this.$_url}request`, {'status': this.getStatusValue.sent, 'employee': emp})
        let approved = this.$_axios.patch(`${this.$_url}request`, {'status': this.getStatusValue.approved, 'employee': emp})
        let denied = this.$_axios.patch(`${this.$_url}request`, {'status': this.getStatusValue.denied, 'employee': emp})
        let [sent, approve, deny] = await Promise.all([pending, approved, denied])
        let processeds = [...approve.data.resultList, ...deny.data.resultList]
        let approveds = [...sent.data.resultList]
        this.processedRequests = processeds.length > 0 ? await this.parseRequest(processeds) : []
        this.requests = approveds.length > 0 ? await this.parseRequest(approveds) : []
        let message1 = !sent.data.message ? [] : sent.data.message
        let message2 = !approve.data.message ? [] : approve.data.message
        let message3 = !deny.data.message ? [] : deny.data.message
        let messageList = [...message1, ...message2, ...message3]
        if (messageList) {
          // this.messages = [...messageList]
          this.haveMessage = true
          if (sent.success || approve.success || deny.success) {
            this.messageColor = 'info'
          } else {
            this.messageColor = 'warning'
          }
        }
      } catch (err) {
        console.log(JSON.stringify(err, null, ''))
        this.messages = [this.getMessages.consultError]
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    async parseRequest (list) {
      return Promise.all(list.map(async n => {
        let hasUser = n.employee.user
        if (!hasUser) {
          n = await this.$_axios.patch(`${this.$_url}request`, {id: n.id})
          n = n.data.resultList[0]
        }
        return n
      }))
    }
  },
  components: {
    Request
  }
}
</script>
