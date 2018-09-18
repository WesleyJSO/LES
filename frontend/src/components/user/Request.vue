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
    <v-form v-model="valid" @submit.prevent="validateRequest">
      <v-layout row wrap>
      <!-- Row 1 -->
      <v-flex xs6>
          <v-combobox v-model="request.type"
                      prepend-icon="check_circle"
                      :items="getItems.types"
                      label="Tipo de Solicitação"
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
        <v-flex xs6>
          <v-menu ref="requestEntryDate"
                  :close-on-content-click="false"
                  v-model="requestEntryDate"
                  :nudge-right="40"
                  :return-value.sync="request.entryDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                        v-model="request.entryDate"
                        label="Data Alvo"
                        :rules="$v_request.validateDates()"
                        prepend-icon="event"
                        readonly>
            </v-text-field>
            <v-date-picker v-model="request.entryDate"
                            :reactive="reactive"
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="requestEntryDate = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.requestEntryDate.save(request.entryDate)">Confirmar</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
        <!-- Row 2 -->
        <v-flex xs6 v-if="showEntryDates">
          <v-menu ref="requestChangeDate"
                  :close-on-content-click="false"
                  v-model="requestChangeDate"
                  :nudge-right="40"
                  :return-value.sync="request.dateChange"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                        v-model="request.dateChange"
                        label="Data Troca"
                        prepend-icon="event"
                        readonly>
            </v-text-field>
            <v-date-picker v-model="request.dateChange"
                            :reactive="reactive"
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="requestChangeDate = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.requestChangeDate.save(request.dateChange)">Confirmar</v-btn>
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
                      name="input-7-4"
                      label="Venho por meio deste solicitar ...">
            </v-textarea>
        </v-flex>
    </v-layout>
    <v-btn type="submit" color="success">Salvar</v-btn>
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
    request: {}
  }),
  created () {
  },
  computed: {
    getItems () {
      return UserService.REQUEST
    },
    showEntryDates () {
      return this.request.type === this.getItems.types[5] || this.request.type === this.getItems.types[4] || this.request.type === this.getItems.types[3]
    },
    showAttachmentEntry () {
      return this.request.type === this.getItems.types[3] || this.request.type === this.getItems.types[1] || this.request.type === this.getItems.types[0]
    }
  },
  watch: {
  },
  methods: {
    validateRequest () {
      // Need to validate the fields
      // adding in the rules in the userValidator file
      this.valid = true
      if (this.valid) {
        this.saveRequest()
      } else {
        this.messages = ['Para realizar o cadastro de funcionário todos os campos abaixo devem ser preenchidos!']
        this.haveMessage = true
        this.messageColor = 'warning'
      }
    },
    saveRequest () {
      // TODO: Change method to "really"
      // send the request to rest service
      // Remeber that request can be both
      // created and edited, this is the
      // meaning of the variable 'edit'
      if (!this.edit) {
        let funcionario = this.user
        this.$_axios.post(`${this.$_url}funcionario`, funcionario).then((response) => {
          let resultado = response.data
          if (resultado.listaResultado.length !== 0) {
            // retorno ok /
            this.funcionario = resultado.listaResultado
          }
          if (resultado.mensagem) {
            this.messages = [...resultado.mensagem]
            this.haveMessage = true
            if (resultado.sucesso) {
            // retorno mensagem de sucesso /
              this.messageColor = 'info'
            } else {
              // retorno mensagem de erro /
              this.messageColor = 'warning'
            }
          }
        },
        (response) => {
          // erro na requisição do serviço /
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      } else {
        this.$emit('save', this.user)
      }
    },
    clearForm () {
      this.valid = false
      this.menu = false
      this.urequest = {}
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
