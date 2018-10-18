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
    <v-form ref="form" v-model="valid">
      <!-- Row 0 -->
      <v-layout>
        <v-flex xs4 sm3 md2 lg2
                v-for="v in employee.roleList" :key="v.id">
          <v-checkbox :id="`checkBox${v.id}`"
            v-model="v.active"
            :label="v.roleName"
            :rules="$v_role.validateSelectedRoles(employee.roleList)"
          ></v-checkbox>
        </v-flex>
      </v-layout>

      <v-layout>
      <!-- Row 1 -->
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="name"
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
          <v-text-field id="lastName"
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
          <v-text-field id="email"
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
          <v-text-field id="salary"
                        v-model="employee.baseHourCalculation.salary"
                        type="number"
                        prepend-icon="monetization_on"
                        clearable
                        :rules="$v_user.salaryRules(employee.baseHourCalculation.salary)"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="pis"
                        v-model="employee.pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="$v_user.pisRules(employee.pis)"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="workload"
                        v-model="employee.baseHourCalculation.workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="$v_user.workloadRules(employee.baseHourCalculation.workload)"
                        label="Carga Horária diaria"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 3 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="phoneNumber1"
                          v-model="employee.telephoneList[0].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="phoneNumber2"
                          v-model="employee.telephoneList[1].number"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="phoneNumber3"
                          v-model="employee.telephoneList[2].number"
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
          <v-combobox v-if="managerList" id="supervisor"
            v-model="select"
            :items="managerList.map(x => x.name)"
            chips
            label="Gestor">
            <template slot="selection" slot-scope="data">
              <v-chip
                :selected="data.selected"
                :disabled="data.disabled"
                :key="JSON.stringify(data.item)"
                class="v-chip--select-multi"
                @input="data.parent.selectItem(data.item)">
                <v-avatar class="accent white--text">
                  {{ data.item.slice(0, 1).toUpperCase() }}
                </v-avatar>
                {{ data.item }}
              </v-chip>
            </template>
          </v-combobox>
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
            <v-text-field id="joiningDate"
                slot="activator"
                v-model="employee.joiningDate"
                label="Data de Ingresso na Empresa"
                prepend-icon="event"
                ><!-- add readonly -->
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
          <v-text-field id="hourType"
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
          <v-text-field id="password"
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
          <v-text-field id="passwordValidator"
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
        <v-btn id="submit" type="submit"
               @click="submit"
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
  data: () => ({
    select: '',
    employee: {
      manager: {
        login: new Login('', '', true),
        email: ''
      },
      roleList: null,
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
    valid: false,
    managerList: null
  }),
  beforeMount () {
    this.$_axios.get(`${this.$_url}role`)
      .then(response => {
        this.employee.roleList = response.data.resultList
        this.$_axios.get(`${this.$_url}user`)
          .then(response => {
            this.managerList = response.data.resultList
            console.log(JSON.stringify(this.managerList))
          })
      })
      .catch(error => {
        console.log(JSON.stringify(error))
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
  },
  computed: {
    color () { return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)] },
    colorValidation () { return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)] },
    title () { return this.edit ? 'Alterar Usuário' : 'Cadastro de Usuário' }
  },
  methods: {
    progress () { return Math.min(100, this.employee.login.password.length * 10) },
    progressValidation () { return Math.min(100, this.employee.login.passwordValidator.length * 10) },
    submit () {
      this.$_axios.post(`${this.$_url}employee`, this.employee).then((response) => {
        let result = response.data
        if (result.resultList.length !== 0) {
          this.employee = result.resultList[0]
        }
        if (result.message) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
            this.messageColor = 'info'
            this.clearForm()
          } else {
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    clearForm () { this.$refs.form.reset() }
  },
  props: {
    item: {
      type: Object,
      default () {
        return {}
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
