<template>
    <v-app>
    <h1>Dados da Empresa</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <v-form @submit.prevent="saveUser">
      <v-layout row wrap>
      <!-- Row 1 -->
        <v-flex xs6>
          <v-text-field
                v-model="company.socialName"
                type="text"
                class="px-0"
                prepend-icon="assignment"
                clearable
                label="Razão Social"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field
                v-model="company.fantasyName"
                type="text"
                prepend-icon="supervisor_account"
                clearable
                label="Nome Fantasia"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field
                v-model="company.cpnj"
                type="text"
                prepend-icon="email"
                clearable
                label="CNPJ"
                required>
          </v-text-field>
        </v-flex>
      <!-- Row 2 -->
        <v-flex xs6>
          <v-text-field v-model="company.stateRegistration"
                        type="text"
                        prepend-icon="domain"
                        clearable
                        label="Inscrição Estadual"
                        required>
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
    },
    company: {
      type: Object,
      default () {
        return {}
      }
    }
  },
  data: () => ({
    valid: false,
    joiningDateMenu: false,
    birhtDateMenu: false,
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
    parsedDate () {
      return new Date().toLocaleString('pt-Br', {year: 'numeric', month: 'numeric', day: 'numeric'})
    },
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
        this.messages = ['Para Salvar os Dados da Empresa, os campos deve ser Válidos!']
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
