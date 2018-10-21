<template>
    <v-app>
    <h1>Realizar Solicitação</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <v-form ref="form" v-model="valid">
      <v-layout row wrap>
      <!-- Row 1 -->
      <v-flex xs6>
          <v-combobox v-model="type"
                      prepend-icon="check_circle"
                      :items="getItems"
                      label="Tipo de Solicitação"
                      item-text="description"
                      chips>
            <template slot="selection"
                      slot-scope="data">
              <v-chip :selected="data.selected"
                      :disabled="data.disabled"
                      :key="JSON.stringify(data.item)"
                      class="v-chip--select-multi"
                      @input="data.parent.selectItem(data.item)">
                <v-avatar class="accent white--text"
                          v-text="data.item.description.slice(0, 1).toUpperCase()">
                </v-avatar>
                  {{ data.item.description }}
              </v-chip>
            </template>
          </v-combobox>
        </v-flex>
        <v-flex xs6>
          <v-menu ref="requestEntryDate"
                  :close-on-content-click="false"
                  v-model="requestEntryDate"
                  :nudge-right="40"
                  :return-value.sync="request.startDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                        v-model="request.startDate"
                        label="Data Alvo"
                        :rules="$v_request.startDate(request.startDate)"
                        prepend-icon="event"
                        readonly>
            </v-text-field>
            <v-date-picker v-model="request.startDate"
                            :locale="locale"
                            :min="minStartDate"
                            :reactive="reactive"
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="requestEntryDate = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.requestEntryDate.save(request.startDate)">Confirmar</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
        <!-- Row 2 -->
        <v-flex xs6 v-if="showEntryDates">
          <v-menu ref="requestChangeDate"
                  :close-on-content-click="false"
                  v-model="requestChangeDate"
                  :nudge-right="40"
                  :return-value.sync="request.endDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                        v-model="request.endDate"
                        label="Data Troca"
                        prepend-icon="event"
                        :disabled="enableEndDate"
                        :rules="$v_request.endDate(request.startDate, request.endDate)"
                        readonly>
            </v-text-field>
            <v-date-picker v-model="request.endDate"
                            :reactive="reactive"
                            :locale="locale"
                            :min="minChangeDate"
                            lazy
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="requestChangeDate = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.requestChangeDate.save(request.endDate)">Confirmar</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs6 v-if="showAttachmentEntry">
          <v-text-field v-model="request.attachment"
                        type="file"
                        prepend-icon="attachment"
                        clearable
                        label="Anexar Arquivo"
                        required>
          </v-text-field>
        </v-flex>
      <!-- Row 2 -->
        <v-flex xs12>
          <v-textarea v-model="request.description"
                      box
                      outline
                      :rules="$v_request.description(request.description)"
                      name="input-7-4"
                      label="Venho por meio deste solicitar ...">
            </v-textarea>
        </v-flex>
    </v-layout>
    <v-btn type="submit" 
           color="success"
           :disabled="!valid"
           @click="saveRequest">Salvar</v-btn>
    <v-btn @click="clearForm" color="error">Cancelar</v-btn>
    </v-form>
  </v-app>
</template>

<script>
import UserService from '../../service/UserService'
export default {
  data: () => ({
    valid: false,
    requestEntryDate: false,
    requestChangeDate: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    message: '',
    request: {},
    type: ''
  }),
  created () {
  },
  computed: {
    getItems () {
      return UserService.REQUEST
    },
    showEntryDates () {
      return this.request.type === this.getItems[3] || this.request.type === this.getItems[2] || this.request.type === this.getItems[1]
    },
    showAttachmentEntry () {
      return this.request.type === this.getItems[2] || this.request.type === this.getItems[1]
    },
    parseDate () {
      if (!this.request.startDate) {
        return new Date(this.request.startDate).toLocaleDateString('pt-BR')
      } else {
        return ''
      }
    },
    minStartDate () {
      return new Date().toISOString()
    },
    minChangeDate () {
      return !this.request.startDate ? new Date().toISOString() : new Date(this.request.startDate).toISOString()
    },
    enableEndDate () {
      return !this.request.startDate
    },
    locale () {
      return 'pt-BR'
    }
  },
  watch: {
  },
  methods: {
    saveRequest () {
      // TODO: Change method to "really"
      // send the request to rest service
      // Remeber that request can be both
      // created and edited, this is the
      // meaning of the variable 'edit'
      this.request = Object.assign({'status': null, 'type': this.type.id}, this.request)
      alert(JSON.stringify(this.request, null, ' '))
      this.$_axios.post(`${this.$_url}request`, this.request).then((response) => {
        let result = response.data
        console.log(JSON.stringify(result.resultList), null, ' ')
        if (result.message) {
          // alert('Messages everything OK')
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
          // retorno mensagem de sucesso
            this.messageColor = 'info'
          } else {
            // retorno mensagem de erro
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        // erro na requisição do serviço
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    clearForm () {
      // this.$refs.form.reset()
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
