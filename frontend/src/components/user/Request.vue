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
          <v-menu
                :close-on-content-click="false"
                v-model="requestEntryDate"
                :nudge-right="40"
                lazy offset-y full-width
                transition="scale-transition"
                min-width="290px">
                <v-text-field
                  :label="getLabels.startDate"
                  v-model="request.startDate"
                  :rules="$v_request.startDate(request.startDate, edit)"
                  slot="activator"
                  prepend-icon="event">
                </v-text-field>

                <v-date-picker
                  :locale="locale"
                  :header-color="getColors.black"
                  :reactive="reactive"
                  scrollable
                  v-model="request.startDate"
                  @input="requestEntryDate = false">                  
                </v-date-picker>
              </v-menu>
        </v-flex>
        <!-- Row 2 -->
        <v-flex xs6>
          <v-menu
                :close-on-content-click="false"
                v-model="requestChangeDate"
                :nudge-right="40"
                lazy offset-y full-width
                transition="scale-transition"
                min-width="290px">
                <v-text-field
                  :label="getLabels.endDate"
                  v-model="request.endDate"
                  :rules="$v_request.endDate(request.startDate, request.endDate)"
                  slot="activator"
                  prepend-icon="event">
                </v-text-field>

                <v-date-picker
                  :locale="locale"
                  :header-color="getColors.black"
                  :reactive="reactive"
                  v-model="request.endDate"
                  scrollable
                  @input="requestChangeDate = false">                  
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
import RequestService from '@/service/RequestService'
export default {
  props: {
    item: Object,
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
  computed: {
    getItems () {
      return RequestService.REQUEST
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
<<<<<<< d99dd8b43efda0dd0a72b570caff354c0e0fd123
      return this.edit ? '' : 'Realizar Solicitação'
=======
      return this.isEditing ? '' : 'Realizar Solicitação'
>>>>>>> Change datePicker type of Request view
    },
    getLabels () {
      return RequestService.LABELS
    },
    getColors () {
      return RequestService.COLORS
<<<<<<< d99dd8b43efda0dd0a72b570caff354c0e0fd123
    },
    getRequestValue () {
      return RequestService.REQUEST_VALUE
=======
>>>>>>> Change datePicker type of Request view
    }
  },
  watch: {
    item (value) {
      this.request = Object.assign({}, value)
      this.type = this.getRequestValue[value.type]
      this.edit = true
    }
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
        let i = Object.assign({}, this.request)
        delete i.employee
        i.type = this.type.id
        let updateRequest = Object.assign({'employee': {}}, i)
        updateRequest.employee.id = this.request.employee.id
        alert(JSON.stringify(updateRequest, null, ' '))
        this.$_axios.put(`${this.$_url}request`, updateRequest).then((response) => {
          let result = response.data
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              // retorno mensagem de sucesso
              // alert('Messages everything OK')
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
      this.$emit('onClose', this.messages)
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
