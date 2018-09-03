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
                        :rules="$v_user.nameRules(this.user.name)"
                        label="Nome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.lastName"
                        type="text"
                        prepend-icon="face"
                        clearable
                        :rules="$v_user.lastNameRules(this.user.lastName)"
                        label="Sobrenome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.email"
                        type="email"
                        prepend-icon="email"
                        clearable
                        :rules="$v_user.emailRules(user.email)"
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
                        :rules="$v_user.salaryRules(user.salary)"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="$v_user.pisRules(user.pis)"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="$v_user.workloadRules(user.workload)"
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
          <v-text-field v-model="user.thelephoneList[1].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(user.thelephoneList)"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.thelephoneList[1].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule2(user.thelephoneList)"
                          label="Telefone">
          </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.thelephoneList[2].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule3(user.thelephoneList)"
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
                        :rules="$v_user.loginRules(this.user.login)"
                        label="Login"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4 >
          <v-menu ref="birhtDateMenu"
                  :close-on-content-click="false"
                  v-model="birhtDateMenu"
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
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="birhtDateMenu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.birhtDateMenu.save(user.birthdate)">Confirmar</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu ref="joiningDateMenu"
                  :close-on-content-click="false"
                  v-model="joiningDateMenu"
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
                            no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="joiningDateMenu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.joiningDateMenu.save(user.joiningDate)">Confirmar</v-btn>
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
                        :rules="$v_user.passwordRule(user.password, user.passwordValidation)"
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
          <v-text-field v-model="user.passwordValidation"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        :rules="$v_user.passwordValidationRule(user.password, user.passwordValidation)"
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
import AdminService from '@/service/AdminService'
export default {
  props: {
    user: {
      type: Object,
      default () {
        return {
          thelephoneList: [
            { type: 'Fixo', number: '46771435' },
            { type: 'Móvel', number: '998679124' },
            { type: 'Móvel', number: '998679124' }
          ],
          password: '',
          passwordValidation: '',
          name: 'José',
          lastName: 'Zeller',
          email: 'jose@zeller.com',
          salary: 1200,
          pis: 123456789,
          workload: 6,
          login: 'Zeller',
          manager: 'Bill Gates'
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
    joiningDateMenu: false,
    birhtDateMenu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    message: ''
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
    },
    getItems () {
      return AdminService.ITEMS
    }
  },
  watch: {
  },
  methods: {
    parsedDate () {
      return new Date().toLocaleString('pt-Br', {year: 'numeric', month: 'numeric', day: 'numeric'})
    },
    progress () {
      return Math.min(100, this.user.password.length * 10)
    },
    progressValidation () {
      return Math.min(100, this.user.passwordValidation.length * 10)
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
    margin-top: -20px;
    margin-bottom: 20px;
  }
</style>
