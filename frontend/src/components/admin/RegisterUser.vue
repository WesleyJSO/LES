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
    <v-form ref="form" v-model="valid" @submit.prevent="saveUser">
      <v-layout>
      <!-- Row 1 -->
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="employee.name"
                type="text"
                class="px-0"
                prepend-icon="face"
                clearable
                :rules="$v_user.nameRules(employee.name)"
                label="Nome"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="employee.lastName"
                type="text"
                prepend-icon="face"
                clearable
                :rules="$v_user.lastNameRules(employee.lastName)"
                label="Sobrenome"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="employee.email"
                type="email"
                prepend-icon="email"
                clearable
                :rules="$v_user.emailRules(employee.email)"
                label="E-mail"
                required>
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 2 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="employee.baseHourCalculation.salary"
                        type="number"
                        prepend-icon="monetization_on"
                        clearable
                        :rules="$v_user.salaryRules(employee.baseHourCalculation.salary)"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="employee.pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="$v_user.pisRules(employee.pis)"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="employee.baseHourCalculation.workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="$v_user.workloadRules(employee.baseHourCalculation.workload)"
                        label="Carga Horária"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 3 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="employee.telephoneList[0].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="employee.telephoneList[1].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="employee.telephoneList[2].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone">
            </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 4 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
              label="Gestor"
              v-model="employee.manager.name"
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
              :return-value.sync="employee.joiningDate"
              lazy
              transition="scale-transition"
              full-width>
            <v-text-field
                slot="activator"
                v-model="employee.joiningDate"
                label="Data de Ingresso na Empresa"
                prepend-icon="event"
                readonly>
            </v-text-field>
            <v-date-picker
                v-model="employee.joiningDate"
                header-color="black"
                :reactive="reactive"
                @input="$refs.joiningDateHelper.save(employee.joiningDate)">
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
                v-model="employee.baseHourCalculation.hourType"
                type="number"
                prepend-icon="timer"
                clearable
                :rules="$v_baseHour.hourTypeRules(employee.baseHourCalculation.hourType)"
                label="Tipo de Hora">
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 5 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field
              v-model="employee.login.password"
              color="cyan darken"
              label="Senha"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordRules(employee.login.password)"
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
              v-model="employee.login.passwordValidator"
              color="cyan darken"
              label="Senha de Confirmação"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordValidationRules(employee.login.password, employee.login.passwordValidator)"
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
        <v-btn type="submit" 
               @click.prevent="saveUser" 
               :disabled="!valid"
               color="success">Salvar</v-btn>
        <v-btn @click="clearForm" color="error">Cancelar</v-btn>
      </div>
    </v-form>
  </v-app>
</template>

<script>
import Login from '../../objects/Login'

export default {
  props: {
    item: {
      type: Object,
      default () {
        return {}
      }
    }
  },
  data: () => ({
    employee: {
      manager: {
        login: new Login('', '', true),
        email: ''
      },
      login: {
        password: '',
        passwordValidator: ''
      },
      baseHourCalculation: { expirationDate: null, salary: '' },
      telephoneList: [ {type: '', number: ''}, {type: '', number: ''}, {type: '', number: ''} ]
    },
    menu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    messageColor: '',
    edit: false,
    joiningDateHelper: false,
    valid: false
  }),
  created () {
  },
  computed: {
    color () { return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)] },
    colorValidation () { return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)] },
    title () { return this.edit ? 'Alterar Funcionário' : 'Cadastro de Funcionário' }
  },
  methods: {
    progress () { return Math.min(100, this.employee.login.password.length * 10) },
    progressValidation () { return Math.min(100, this.employee.login.passwordValidator.length * 10) },
    saveUser () {
      alert(JSON.stringify(this.employee))
      this.$_axios.post(`${this.$_url}funcionario`, this.employee).then((response) => {
        let result = response.data
        alert(JSON.stringify(result))
        if (result.resultList.length !== 0) {
          // retorno ok
          alert(JSON.stringify(result))
          this.employee = result.resultList[0]
        }
        if (result.message) {
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
        alert(JSON.stringify(response))
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    clearForm () {
      this.menu = false
      this.$refs.form.reset()
      this.employee = {
        manager: {
          login: new Login('', '', true),
          email: ''
        },
        login: {
          password: '',
          passwordValidator: ''
        },
        baseHourCalculation: { expirationDate: null, salary: '' },
        telephoneList: [ {type: '', number: ''}, {type: '', number: ''}, {type: '', number: ''} ]
      }
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
