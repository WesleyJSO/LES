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
          <v-text-field v-model="user.name"
                        type="text"
                        class="px-0"
                        prepend-icon="face"
                        clearable
                        :rules="nameRules()"
                        label="Nome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.lastName"
                        type="text"
                        prepend-icon="face"
                        clearable
                        :rules="lastNameRules()"
                        label="Sobrenome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.email"
                        type="email"
                        prepend-icon="email"
                        clearable
                        :rules="emailRules()"
                        label="E-mail"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 2 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.salary"
                        type="number"
                        prepend-icon="monetization_on"
                        clearable
                        :rules="salaryRules()"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="pisRules()"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="workloadRules()"
                        label="Carga Horária"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 3 -->
      <v-layout>
        <v-flex >

          <v-text-field v-model="user.manager"
                        type="text"
                        prepend-icon="supervisor_account"
                        clearable
                        label="Gestor"
                        required>
          </v-text-field>
          <!-- <v-combobox v-model="gestor"
                      prepend-icon="supervisor_account"
                      :items="items.gestor"
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
          <v-text-field v-model="user.phone1"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRule1()"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.phone2"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRule2()"
                          label="Telefone">
          </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.phone3"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRule3()"
                          label="Telefone">
            </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 4 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.login"
                        type="text"
                        class="px-0"
                        prepend-icon="face"
                        clearable
                        :rules="loginRules()"
                        label="Login"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4 >
          <v-menu ref="user.birthDateHelper"
                  :close-on-content-click="false"
                  v-model="user.birthDateHelper"
                  :nudge-right="40"
                  :return-value.sync="user.birthdate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                        v-model="user.birthdate"
                        label="Data de Nascimento"
                        prepend-icon="event"
                        readonly>
            </v-text-field>
            <v-date-picker v-model="user.birthdate"
                            :reactive="reactive"
                            header-color="primary"
                            @input="$refs.user.birthDateHelper.save(user.birthdate)">
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu ref="user.joiningDateHelper"
                  :close-on-content-click="false"
                  v-model="user.joiningDateHelper"
                  :nudge-right="40"
                  :return-value.sync="user.joiningDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
            <v-text-field slot="activator"
                          v-model="user.joiningDate"
                          label="Data de Ingresso na Empresa"
                          prepend-icon="event"
                          readonly>
            </v-text-field>
            <v-date-picker v-model="user.joiningDate"
                            header-color="green"
                            :reactive="reactive"
                            @input="$refs.user.joiningDateHelper.save(user.joiningDate)">
            </v-date-picker>
          </v-menu>
        </v-flex>
      </v-layout>
      <!-- Rown 6 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field v-model="user.password"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        :rules="passwordRule()"
                        placeholder="Informe a Senha do Funcionário"
                        loading >
            <v-progress-linear slot="progress"
                              :value="progress()"
                              :color="color"
                              height="7">
            </v-progress-linear>
          </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.password2"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        :rules="passwordValidationRule()"
                        placeholder="Confirme a Senha do Funcionário"
                        loading >
            <v-progress-linear slot="progress"
                              :value="progressValidation()"
                              :color="colorValidation"
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
// import AdminService from '@/service/AdminService'
export default {
  props: {
    user: {
      type: Object,
      default () {
        return {
          password: '',
          password2: ''
        }
      }
    },
    edit: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    valid: false,
    menu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    message: '',
    items: {
      gestor: ['Bill Gates', 'Torvald Linux', 'Elon Musk']
    }
  }),
  created () {
  },
  computed: {
    color () {
      return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)]
    },
    colorValidation () {
      return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)]
    },
    title () {
      if (!this.edit) {
        return 'Cadastro de Funcionário'
      } else {
        return 'Alterar Funcionário'
      }
    }
  },
  watch: {
  },
  methods: {
    progress () {
      return Math.min(100, this.user.password.length * 10)
    },
    progressValidation () {
      return Math.min(100, this.user.password2.length * 10)
    },
    nameRules () {
      if (!this.user.name) {
        return ['Informe um Nome!']
      } else if (!/[^0-9]+[a-zA-Z]/.test(this.user.name)) {
        return ['Informe um Nome Válido!']
      } else if (this.user.name.length <= 3 || this.user.name.length >= 20) {
        return ['Informe um nome Válido!']
      }
      return []
    },
    lastNameRules () {
      if (!this.user.lastName) {
        return ['Informe um Sobrenome!']
      } else if (!/[^0-9]+[a-zA-Z]/.test(this.user.lastName)) {
        return ['Informe um Sobrenome Válido!']
      } else if (this.user.lastName.length <= 3 || this.user.lastName.length >= 20) {
        return ['Informe um Sobrenome Válido!']
      }
      return []
    },
    emailRules () {
      if (!this.user.email) {
        return ['Informe um e-mail!']
      } else if (!/.+@.+/.test(this.user.email)) {
        return ['Informe um e-mail válido!!']
      } else if (!/[^0-9]+[a-zA-Z]/.test(this.user.lastName)) {
        return ['Informe um Sobrenome Válido!']
      } else if (this.user.lastName.length <= 3 || this.user.lastName.length >= 20) {
        return ['Informe um SObrenome Válido!']
      }
      return []
    },
    salaryRules () {
      if (!this.user.salary) {
        return ['Informe um Salário!']
      } else if (this.user.salary.length <= 0) {
        return ['Informe um salário Válido!']
      }
      return []
    },
    workloadRules () {
      if (!this.user.workload) {
        return ['Informe uma Carga Horária!']
      } else if (this.user.workload > 8) {
        return ['Informe uma Carga Horária Válida!']
      }
      return []
    },
    managerRules () {
      if (!this.user.manager) {
        return ['Informe um Gestor!']
      } else if (this.user.manager.length <= 2 || this.user.manager.length >= 20) {
        return ['Informe um Gestor Válido!']
      }
      return []
    },
    phoneRule1 () {
      if (!this.user.phone1 && !this.user.phone2 && !this.user.phone3) {
        return ['Informe pelo menos um Telefone!']
      } else if (this.user.phone2 || this.user.phone3) {
        return []
      } else if (this.user.phone1.length >= 10) {
        return ['Informe um Telefone Válido!']
      } else if (this.user.phone1.length <= 7) {
        return ['Informe um Telefone Válido']
      }
      return []
    },
    phoneRule2 () {
      if (!this.user.phone1 && !this.user.phone2 && !this.user.phone3) {
        return ['Informe pelo menos um Telefone!']
      } else if (this.user.phone1 || this.user.phone3) {
        return []
      } else if (this.user.phone2.length >= 10) {
        return ['Informe um Telefone Válido!']
      } else if (this.user.phone2.length <= 7) {
        return ['Informe um Telefone Válido']
      }
      return []
    },
    phoneRule3 () {
      if (!this.user.phone1 && !this.user.phone2 && !this.user.phone3) {
        return ['Informe pelo menos um Telefone!']
      } else if (this.user.phone1 || this.user.phone2) {
        return []
      } else if (this.user.phone3.length >= 10) {
        return ['Informe um Telefone Válido!']
      } else if (this.user.phone3.length <= 7) {
        return ['Informe um Telefone Válido']
      }
      return []
    },
    loginRules () {
      if (!this.user.login) {
        return ['Informe um Login!']
      } else if (!/[^0-9]+[a-zA-Z]/.test(this.user.login)) {
        return ['Informe um Login Válido!']
      } else if (this.user.login.length <= 2 || this.user.login.length > 20) {
        return ['Informe um Login com até 20 Carácteres!']
      }
      return []
    },
    pisRules () {
      if (!this.user.pis) {
        return ['Informe um número PIS/PASESP!']
      } else if (this.user.pis.length !== 9) {
        return ['Informe um número PIS/PASESP Válido!']
      }
      return []
    },
    passwordRule () {
      if (!this.user.password && !this.user.password2) {
        return ['Informe uma Senha!']
      } else if ((!this.user.password && this.user.password2) || (this.user.password && !this.user.password2)) {
        return ['As Senhas informadas são divergentes!']
      }
      return []
    },
    passwordValidationRule () {
      if (!this.user.password && !this.user.password2) {
        return ['Informe uma Senha!']
      } else if ((!this.user.password && this.user.password1) || (this.user.password && !this.user.password2)) {
        return ['As Senhas informadas são divergentes!']
      } else if (this.user.password !== this.user.password2) {
        return ['As Senhas informadas são divergentes!']
      }
      return []
    },
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
      this.user = {}
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
