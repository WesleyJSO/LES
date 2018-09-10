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
    <v-form @submit.prevent="saveUser">
      <v-layout>
      <!-- Row 1 -->
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="name"
                type="text"
                class="px-0"
                prepend-icon="face"
                clearable
                :rules="$v_user.nameRules(name)"
                label="Nome"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="lastName"
                type="text"
                prepend-icon="face"
                clearable
                :rules="$v_user.lastNameRules(lastName)"
                label="Sobrenome"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="email"
                type="email"
                prepend-icon="email"
                clearable
                :rules="$v_user.emailRules(email)"
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
                        :rules="$v_user.salaryRules(salary)"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="$v_user.pisRules(pis)"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="$v_user.workloadRules(workload)"
                        label="Carga Horária"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 3 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="number1"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(number1, number2, number3)"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="number2"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule2(number1, number2, number3)"
                          label="Telefone">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="number3"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule3(number1, number2, number3)"
                          label="Telefone">
            </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 4 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
              label="Gestor"
              v-model="managerName"
              type="text"
              prepend-icon="supervisor_account"
              clearable
              required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu
              ref="joiningDateHelper"
              :close-on-content-click="false"
              v-model="joiningDateHelper"
              :nudge-right="40"
              :return-value.sync="joiningDate"
              lazy
              transition="scale-transition"
              full-width>
            <v-text-field
                slot="activator"
                v-model="joiningDate"
                label="Data de Ingresso na Empresa"
                prepend-icon="event"
                readonly>
            </v-text-field>
            <v-date-picker
                v-model="joiningDate"
                header-color="black"
                :reactive="reactive"
                @input="$refs.joiningDateHelper.save(joiningDate)">
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="hourType"
                type="number"
                prepend-icon="timer"
                clearable
                :rules="$v_baseHour.hourTypeRules(hourType)"
                label="Tipo de Hora">
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 5 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field
              v-model="password"
              color="cyan darken"
              label="Senha"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordRules(password)"
              placeholder="Informe a Senha do Funcionário"
              loading >
            <v-progress-linear
                slot="progress"
                :value="progress()"
                :color="color"
                height="7">
            </v-progress-linear>
          </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field
              v-model="passwordValidator"
              color="cyan darken"
              label="Senha de Confirmação"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordValidationRules(password, passwordValidator)"
              placeholder="Confirme a Senha do Funcionário"
              loading >
            <v-progress-linear
                slot="progress"
                :value="progressValidation()"
                :color="colorValidation"
                height="7">
            </v-progress-linear>
          </v-text-field>
        </v-flex>
      </v-layout>
      <div v-if="!edit">
        <v-btn type="submit" @click.prevent="saveUser" color="success">Salvar</v-btn>
        <v-btn @click="clearForm" color="error">Cancelar</v-btn>
      </div>
    </v-form>
  </v-app>
</template>

<script>
import BaseHourCalculation from '../../objects/BaseHourCalculation'
import Telephone from '../../objects/Telephone'
import Employee from '../../objects/Employee'
import Login from '../../objects/Login'
import User from '../../objects/User'

export default {
  data: () => ({
    employee: [],
    menu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    messageColor: '',
    edit: false,
    employeeId: 0,
    name: '',
    lastName: '',
    email: '',
    pis: '',
    joiningDateHelper: false,
    joiningDate: '',
    // telephones
    number1: '',
    number2: '',
    number3: '',
    // manager
    managerId: '',
    managerMail: '',
    managerName: '',
    // base hour calculation
    baseHourCalculationId: 0,
    hourType: '',
    workload: '',
    expirationDate: null,
    salary: 0,
    // login
    password: '',
    passwordValidator: ''
  }),
  created () {
  },
  computed: {
    color () { return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)] },
    colorValidation () { return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)] },
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
    progress () { return Math.min(100, this.password.length * 10) },
    progressValidation () { return Math.min(100, this.passwordValidator.length * 10) },
    createUser () {
      let telephoneList = [new Telephone(0, this.number1), new Telephone(0, this.number2), new Telephone(0, this.number3)]
      let managerLogin = new Login()
      let employee =
        new Employee(
          this.employeeId,
          this.name,
          this.email,
          this.lastName,
          new Login(this.password, this.passwordValidator, true),
          telephoneList,
          this.pis,
          this.joiningDate,
          new User(this.managerId, managerLogin, this.managerMail), // manager
          new BaseHourCalculation(this.baseHourCalculationId, this.hourType, this.workload, this.expirationDate, this.salary)
        )
      return employee
    },
    validateUser () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_user.emailRules(this.email)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.nameRules(this.name)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.lastNameRules(this.lastName)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.pisRules(this.pis)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.joiningDateRules(this.joiningDate)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.phoneRule1(this.number1, this.number2, this.number3)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.phoneRule2(this.number1, this.number2, this.number3)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.phoneRule3(this.number1, this.number2, this.number3)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      // error = this.$v_user.emailRules(this.managerMail)
      // if (error) {
      //   this.messages = [...this.messages, ...error]
      // }
      error = this.$v_baseHour.hourTypeRules(this.hourType)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_baseHour.workloadRules(this.workload)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_baseHour.salaryRules(this.salary)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.passwordRules(this.password)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_user.passwordValidationRules(this.password, this.passwordValidator)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      if (this.messages.length === 0) {
        return true
      } else if (this.messages.length <= 3) {
        this.haveMessage = true
        this.messageColor = 'warning'
        return false
      } else {
        this.messages = ['Para realizar o cadastro de funcionário todos os campos abaixo devem ser preenchidos!']
        this.haveMessage = true
        this.messageColor = 'warning'
        return false
      }
    },
    *callApi (employee) {
      if (!this.edit) {
        yield this.$_axios.post(`${this.$_url}funcionario`, employee).then(response => {
          var result = response.data
          if (result.listaResultado.length !== 0) {
            // retorno ok /
            employee = result.listaResultado
          }
          if (result.mensagem) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
            // retorno mensagem de sucesso /
              this.messageColor = 'info'
            } else {
              // retorno mensagem de erro /
              this.messageColor = 'warning'
            }
          }
        },
        response => {
          // erro na requisição do serviço /
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      } else {
        yield this.$_axios.put(`${this.$_url}funcionario`, employee).then(response => {
          var result = response.data
          if (result.listaResultado.length !== 0) {
            // retorno ok /
            employee = result.listaResultado
          }
          if (result.mensagem) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
            // retorno mensagem de sucesso /
              this.messageColor = 'info'
            } else {
              // retorno mensagem de erro /
              this.messageColor = 'warning'
            }
          }
        },
        response => {
          // erro na requisição do serviço /
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      }
    },
    saveUser () {
      if (this.validateUser()) {
        // let employee = this.createUser()
        // this.callApi(employee).next().value
      }
    },
    clearForm () {
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
