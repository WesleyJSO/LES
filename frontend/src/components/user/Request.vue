<template>
    <div>
    <h1>{{getTitle}}</h1>
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
        <v-combobox
          v-model="type"
          :items="getItems"
          item-text="description"
          item-value="id"
          prepend-icon="check_circle"
          label="Tipo de Solicitação"
          chips> 
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
        <v-flex xs6>
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
        <v-flex xs6>
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
    <v-btn type="button" 
           color="success"
           :disabled="!valid"
           @click="saveRequest">Salvar</v-btn>
    <v-btn @click="clearForm" color="error">Cancelar</v-btn>
    </v-form>
  </div>
</template>

<script>
import UserService from '../../service/UserService'
export default {
  props: {
    editItem: {
      type: Object
    },
    edit: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    valid: false,
    requestEntryDate: false,
    requestChangeDate: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    message: '',
    request: {},
    type: '',
    isEditing: false
  }),
  mounted () {
    if (this.edit) {
      this.request = this.editItem
      this.type = this.editItem.type
    }
  },
  computed: {
    getItems () {
      return UserService.REQUEST
    },
    showEntryDates () {
      return this.request.type === this.getItems[0] || this.request.type === this.getItems[1] || this.request.type === this.getItems[3]
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
    },
    getTitle () {
      return this.isEditing ? '' : 'Realizar Solicitação'
    }
  },
  watch: {
  },
  methods: {
    saveRequest () {
      if (!this.edit) {
        this.request = Object.assign({'status': null, 'type': this.type.id}, this.request)
        console.log(JSON.stringify(this.request, null, ' '))
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
              this.clearForm()
            } else {
              // retorno mensagem de erro
              this.messageColor = 'warning'
            }
          }
        },
        (response) => {
          // erro na requisição do serviço
          console.log(response)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      } else {
        this.$_axios.update(`${this.$_url}request`, this.request).then((response) => {
          let result = response.data
          console.log(JSON.stringify(result.resultList), null, ' ')
          if (result.message) {
            // alert('Messages everything OK')
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
            // retorno mensagem de sucesso
              this.messageColor = 'info'
              this.clearForm()
            } else {
              // retorno mensagem de erro
              this.messageColor = 'warning'
            }
          }
        },
        (response) => {
          // erro na requisição do serviço
          console.log(response)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      }
    },
    clearForm () {
      this.$refs.form.reset()
      this.$emit('onClose')
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
