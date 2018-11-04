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
            <td class="text-xs-left">{{ getType(props.item.type).description }}</td>
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
            <td class="text-xs-center" v-if="hasRole('ROLE_ADMIN') || hasRole('ROLE_MANAGER')">
                <v-dialog v-model="dialogRequests[props.item.id]" 
                          max-width="600px" 
                          max-height="300px"
                          @keydown.esc="$set(dialogRequests, props.item.id, false)">
                <v-icon
                    slot="activator"
                    :key="props.item.id"
                    light>
                    edit
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
                    <v-btn
                        color="red"
                        flat
                        @click.stop="$set(dialogRequests, props.item.id, false)">
                        {{ getButtons.deny }}
                    </v-btn>
                    <v-btn
                        color="green"
                        flat
                        @click="$set(dialogRequests, props.item.id, false), approveRequest(props.item, true)">
                        {{ getButtons.approve }}
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
            <td class="text-xs-center" v-if="">
                <v-dialog v-model="dialogApproved[props.item.id]"
                          max-width="600px"
                          max-height="300px"
                          @keydown.esc="$set(dialogApproved, props.item.id, false)">
                <v-icon
                    slot="activator"
                    :key="props.item.id"
                    light>
                    edit
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
                        @click.stop="$set(dialogApproved, props.item.id, false)">
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
  created () {
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
    }
  },
  methods: {
    approveRequest (item, val) {
      if (val) {
        item.status = 'Aprovado'
      } else {
        item.status = 'Reprovado'
      }
      this.dialog = false
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
