<template>
    <v-app>
    <h1>{{title}}</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <v-form v-model="valid" @submit.prevent="validateUser">
      <v-layout>
      <!-- Row 1 -->
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="name"
                        type="text"
                        class="px-0"
                        prepend-icon="face"
                        clearable
                        :rules="nameRules"
                        label="Nome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="lastName"
                        type="text"
                        prepend-icon="face"
                        clearable
                        :rules="nameRules"
                        label="Sobrenome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="email"
                        type="email"
                        prepend-icon="email"
                        clearable
                        :rules="emailRules"
                        label="E-mail"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 2 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="salary"
                        type="number"
                        prepend-icon="monetization_on"
                        clearable
                        :rules="salaryRules"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="pisRoles"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="workloadRules"
                        label="Carga Horária"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 3 -->
      <v-layout>
        <v-flex >

          <v-text-field v-model="manager"
                        type="text"
                        prepend-icon="supervisor_account"
                        clearable
                        label="Gestor"
                        required>
          </v-text-field>
          <!-- <v-combobox v-model="manager"
                      prepend-icon="supervisor_account"
                      :items="items.manager"
                      label="Supervisor Imediato"
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
          </v-combobox> -->
        </v-flex>
      </v-layout>
      <!-- Row 5 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field v-model="telephone1"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRules"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="telephone2"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRules"
                          label="Telefone">
          </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="telephone3"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRules"
                          label="Telefone">
            </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 4 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu ref="entryCompanyDateHelper"
                  :close-on-content-click="false"
                  v-model="entryCompanyDateHelper"
                  :nudge-right="40"
                  :return-value.sync="entryCompanyDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                          v-model="entryCompanyDate"
                          label="Data de Ingresso na Empresa"
                          prepend-icon="event"
                          readonly>
            </v-text-field>
            <v-date-picker v-model="entryCompanyDate"
                            header-color="green"
                            :reactive="reactive"
                            @input="$refs.entryCompanyDateHelper.save(entryCompanyDate)">
            </v-date-picker>
          </v-menu>
        </v-flex>
      </v-layout>
      <!-- Rown 6 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field v-model="password"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        placeholder="Informe a Senha do Funcionário"
                        loading >
            <v-progress-linear slot="progress"
                              :value="progress"
                              :color="color"
                              height="7">
            </v-progress-linear>
          </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="passwordValidator"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        :rules="passwordRules"
                        placeholder="Confirme a Senha do Funcionário"
                        loading >
            <v-progress-linear slot="progress"
                              :value="progress"
                              :color="color"
                              height="7">
            </v-progress-linear>
          </v-text-field>
        </v-flex>
      </v-layout>
      <div v-if="!this.edit">
        <v-btn type="submit" color="success">Salvar</v-btn>
        <v-btn @click="clearForm" color="error">Cancelar</v-btn>
      </div>
    </v-form>
  </v-app>
</template>

<script>
import Funcionario from '@/objects/Funcionario'
import BaseCalculoHoras from '@/objects/BaseCalculoHoras'
import Telefone from '@/objects/Telefone'
import Login from '@/objects/Login'

export default {
  props: {
    user: {
      type: Object,
      default: {}
    },
    edit: {
      type: Boolean,
      default: false
    },
    message: {
      type: String,
      default: ''
    }
  },
  data: () => ({
    valid: false,
    menu: false,
    reactive: true,
    entryCompanyDateHelper: '',
    entryCompanyDate: null,
    messages: [],
    tipoHora: -1,
    haveMessage: false,
    messageColor: '',
    name: '',
    lastName: '',
    email: '',
    salary: 0,
    pis: 0,
    workload: 0,
    manager: '',
    password: '',
    passwordValidator: '',
    telephone1: '',
    telephone2: '',
    telephone3: '',
    items: {
      manager: ['Bill Gates', 'Torvald Linux', 'Elon Musk']
    },
    nameRules: [
      v => !!v || 'Informe o Nome do Funcionário!',
      v => v.length <= 20 || 'Informe um name váilido!',
      v => /[^0-9]+[a-zA-Z]/.test(v) || 'Informe um name váilido!'
    ],
    emailRules: [
      v => !!v || 'Informe o E-mail!',
      v => /.+@.+/.test(v) || 'Informe um E-mail válido!'
    ],
    salaryRules: [
      v => v >= 0 || 'Informe um Salário!'
    ],
    pisRoles: [
      v => !!v || 'Informe um número PIS/PASESP!',
      v => v.length <= 9 || 'Informe um número PIS/PASESP válido!'
    ],
    workloadRules: [
      v => !!v || 'Informe uma Carga Horária!',
      v => v.length <= 1 || 'Informe uma Carga Horária válida!'
    ],
    phoneRules: [
      v => !!v || 'Informe pelo menos um Telefone!',
      v => v.length <= 9 || 'Informe um número de Telefone válido!',
      v => v.length >= 8 || 'Informe um número de Telefone válido!'
    ],
    passwordRules: [
      v => !!v || 'Informe uma Senha!'
      // v => /.[0-9]{1}+[a-z]{1}+[A-Z]{1}./.test(v) || 'Informe uma Senha válida!'
    ]
  }),
  created () {
  },
  computed: {
    progress () {
      return Math.min(100, this.password.length * 10)
    },
    color () {
      return ['error', 'warning', 'success'][Math.floor(this.progress / 40)]
    },
    title () {
      if (!this.edit) {
        return 'Cadastro de Funcionário'
      } else {
        return 'Alterar Funcionário'
      }
    }
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
    prepareUserObject () {
      let listaTelefone = []
      listaTelefone.push(new Telefone(null, this.telephone1), new Telefone(null, this.telephone2), new Telefone(null, this.telephone3))
      let funcionario = new Funcionario(
        this.id,
        this.name,
        this.email,
        this.lastName,
        this.,
        new Login(this.password, this.passwordValidator, this., this.ativo),
        listaTelefone,
        this.pis,
        this.entryCompanyDate,
        new Funcionario(null, this.manager),
        [new BaseCalculoHoras(null, this.tipoHora, parseFloat(this.workload), null, parseFloat(this.salary))]
      )
      return funcionario
    },
    saveUser () {
      let funcionario = this.prepareUserObject()
      if (!this.edit) {
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
        this.$emit('save', funcionario)
      }
    },
    parseUserObject (userObject) {
      this.name = userObject.name
      this.lastName = userObject.lastName
      this.email = userObject.email
      this.salary = userObject.salary
      this.pis = userObject.pis
      this.workload = userObject.workload
      this. = userObject.
      this.entryCompanyDate = userObject.entryCompanyDate
      this.manager = userObject.manager
      this.telephone1 = userObject.listaTelefone[0]
      this.telephone2 = userObject.listaTelefone[1]
      this.telephone3 = userObject.listaTelefone[2]
      this.password = userObject.login.password
      this.password2 = userObject.login.passwordValidator
      this.ativo = userObject.login.ativo
      this. = userObject.login.
    },
    clearForm () {
      this.valid = false
      this.menu = false
      this.reactive = true
      this.entryCompanyDate = false
      this. = false
      this.funcionario = {
        name: '',
        lastName: '',
        email: '',
        salary: '',
        pis: '',
        workload: '',
        : '',
        entryCompanyDate: '',
        manager: '',
        phone1: '',
        phone2: '',
        phone3: '',
        password: '',
        password2: ''
      }
    }
  }
}
</script>

<style scoped>
  h1 {
    font-size: 40px;
    text-align: center;
  }
</style>
