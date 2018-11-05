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
      <v-tab-item :key="1">
        <v-data-table :headers="getHeaders"
                  :items="getRequests"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-10" >
        <template slot="items" slot-scope="props">
          <tr @onclick="props.expanded = !props.expanded">
            <td>{{ props.item.employee.name }} {{ props.item.employee.lastName }}</td>
            <td class="text-xs-left">{{ getType(props.item.type) }}</td>
            <td class="text-xs-left">{{ getStatus(props.item.status) }}</td>
            <td class="text-xs-center" v-if="hasRole('ROLE_EMPLOYEE')">
            <v-dialog v-model="dialogEdit" 
                      @keydown.esc="dialogEdit = !dialogEdit">
              <v-icon
                    :key="props.item.id"
                    slot="activator"
                    light
                    @click.stop="editItem(props.item)">
                    edit
              </v-icon>                      
              <v-card>
                <v-card-title class="headline grey lighten-2"
                              primary-title >
                    {{ getTitles.edit }}
                </v-card-title>
                <v-card-text >
                  <request :editedItem="props.item"
                           :edit="edit"
                           @onClose="dialogEdit = !dialogEdit">
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
                        <br>
                        <br>
                        <b>{{ getTitles.file }}</b>
                        <a href="">{{ getTitles.fileName }}</a>
                    </v-card-text>

                    <!-- There is no 'endDate' -->
                    <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && !props.item.endDate">
                        <b>{{ getTitles.entryDate }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <br>
                        <b>{{ getTitles.file }}</b>
                        <a href="">{{ getTitles.fileName }}</a>
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
                        <br>
                        <br>
                        <b>{{ getTitles.file }}</b>
                        <a href="">{{ getTitles.fileName }}</a>
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

                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn v-if="hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')"
                        color="green"
                        flat
                        @click.stop="setDescription(props.item)">
                        {{ getButtons.approve }}
                    </v-btn>
                    <v-btn v-if="hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')"
                        color="red"
                        flat
                        @click.stop="$set(dialogRequests, props.item.id, false)">
                        {{ getButtons.deny }}
                    </v-btn>
                    <v-btn v-if="!hasRole('ROLE_ADMIN') || !hasRole('ROLE_MANAGER')"
                        color="green"
                        flat
                        @click.stop="$set(dialogRequests, props.item.id, false)">
                        {{ getButtons.close }}
                    </v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
                <v-dialog v-model="messageDialog" max-width="500px">
                  <v-card>
                    <v-card-title
                      class="headline grey lighten-2"
                      primary-title>
                      <span>{{getTitles.managerMessage}}</span>
                      <v-spacer></v-spacer>
                    </v-card-title>
                    <v-card-text>
                        <v-textarea v-model="managerMessage"
                          box
                          outline
                          :rules="$v_request.description(request.description)"
                          name="input-7-4"
                          :label="getLabels.managerMessage">
                        </v-textarea>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn color="primary" 
                        flat
                        @click.stop="processRequest()">{{getButtons.confirm}}</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
            </td>
          </tr>
        </template>
        </v-data-table>
      </v-tab-item>
      <v-tab-item :key="2">
        <v-data-table :headers="getHeaders"
                  :items="getApproved"
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
                <v-dialog v-model="dialogApproved[props.item.id]"
                          max-width="600px"
                          max-height="300px"
                          @keydown.esc="$set(dialogApproved, props.item.id, false)">
                <v-icon
                    slot="activator"
                    :key="props.item.id"
                    light>
                    {{getIcon}}
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
                        <br>
                        <br>
                        <b>{{ getTitles.file }}</b>
                        <a href="">{{ getTitles.fileName }}</a>
                    </v-card-text>

                    <!-- There is no 'endDate' -->
                    <v-card-text v-if="(props.item.type === 1 || props.item.type === 2) && !props.item.endDate">
                        <b>{{ getTitles.entryDate }}</b>
                        {{ props.item.startDate}}
                        <br>
                        <br>
                        <b>{{ getTitles.file }}</b>
                        <a href="">{{ getTitles.fileName }}</a>
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
                        <br>
                        <br>
                        <b>{{ getTitles.file }}</b>
                        <a href="">{{ getTitles.fileName }}</a>
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
      approvedRequests: [],
      active: 0,
      request: {},
      tabs: 0,
      requests: [],
      edit: false
    }
  },
  mounted () {
    this.$_axios.patch(`${this.$_url}request`, this.request).then((response) => {
      let resultado = response.data
      // alert(JSON.stringify(resultado, null, ' '))
      if (resultado.resultList.length >= 0) {
        // Fetch a list of cost centres from backend
        this.requests = resultado.resultList
        console.table(this.requests)
      }
      if (resultado.message) {
        this.messages = [...resultado.message]
        this.haveMessage = true
        if (resultado.sucesso) {
          // Success message
          this.messageColor = 'info'
        } else {
          // Waring message
          this.messageColor = 'warning'
        }
      }
    },
    (response) => {
      console.log(JSON.stringify(response, null, ' '))
      // Error during request
      this.messages = ['Erro durante execução do serviço!']
      this.haveMessage = true
      this.messageColor = 'error'
    })
  },
  computed: {
    getHeaders () {
      return RequestService.HEADERS
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
    getApproved () {
      return this.approvedRequests
    },
    getRequests () {
      return this.requests
    },
    getEditedItem () {
      return this.request
    },
    getIcon () {
      return this.hasRole('ROLE_ADMIN') || this.hasRole('ROLE_MANAGER') ? 'edit' : 'remove_red_eye'
    },
    getStatusValue () {
      return RequestService.STATUS_VALUE
    }
  },
  methods: {
    async processRequest () {
      try {
        this.request = Object.assign({'observation': this.managerMessage}, this.request)
        this.request.status = this.getStatusValue.approved
        let result = await this.$_axios.put(`${this.$_url}request`, this.request).data
        if (result.message) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
            this.messageColor = 'info'
          } else {
            this.messageColor = 'warning'
          }
        }
      } catch (err) {
        console.log(JSON.stringify(err))
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    setDescription (item) {
      alert(JSON.stringify(item, null, ''))
      this.request = Object.assign({}, item)
      this.messageDialog = true
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
    editItem (item) {
      this.request = Object.assign({}, item)
      this.edit = true
      // alert(JSON.stringify(this.request, null, ''))
      this.dialogEdit = true
    }
  },
  components: {
    Request
  }
}
</script>
