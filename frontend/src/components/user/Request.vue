<template>
    <v-app>
    <h1>{{getItems.title}}</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <v-form v-model="valid" @submit.prevent="validateUser">
      <v-layout row wrap>
      <!-- Row 1 -->
        <v-flex xs6>
          <v-menu ref="requestMenu"
                  :close-on-content-click="false"
                  v-model="requestMenu"
                  :nudge-right="40"
                  :return-value.sync="request.date"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                        v-model="request.date"
                        label="Data"
                        prepend-icon="event"
                        readonly>
            </v-text-field>
            <v-date-picker v-model="request.date"
                            :reactive="reactive"
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="requestMenu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.requestMenu.save(request.date)">Confirmar</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
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
          <v-combobox v-model="request.manager"
                      prepend-icon="supervisor_account"
                      :items="getItems.managers"
                      label="Enviar Para"
                      multiple
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
  props: {
    request: {
      type: Object,
      default () {
        return {
        }
      }
    }
  },
  data: () => ({
    valid: false,
    requestMenu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    message: ''
  }),
  created () {
  },
  computed: {
    getItems () {
      return UserService.REQUEST
    }
  },
  watch: {
  },
  methods: {
    validateUser () {
      this.valid = true
      if (this.valid) {
        this.saveUser()
      } else {
        this.messages = ['Para realizar o cadastro de funcionário todos os campos abaixo devem ser preenchidos!']
        this.haveMessage = true
        this.messageColor = 'warning'
      }
    },
    saveUser () {
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
