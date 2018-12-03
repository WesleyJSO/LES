<template>
    <div>
    <h1>{{title}}</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-form ref="form" v-model="valid">
      <v-layout>
      <!-- Row 0 -->
        <v-flex xs12 sm4 md4 v-if="edit">
          <v-switch id="toggleEdit"
            v-model="isEditing"
            label="Editar"
            color="success"
            :value="true"
            hide-details>
          </v-switch>
        </v-flex>
        <v-flex xs4 sm3 md2 lg2 v-for="v in roleList" :key="v.id">
          <v-checkbox
            v-model="v.active"
            :readonly="isEditing ? false : true"
            :label="v.roleName"
            @change="checkboxToggle(v)"
            :rules="$v_role.validateSelectedRoles(roleList)"
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
                :clearable="isEditing"
                :rules="$v_user.nameRules(employee.name)"
                label="Nome"
                required
                :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="lastName"
                v-model="employee.lastName"
                type="text"
                prepend-icon="face"
                :clearable="isEditing"
                :rules="$v_user.lastNameRules(employee.lastName)"
                label="Sobrenome"
                required
                :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="email"
                v-model="employee.user.email"
                type="email"
                prepend-icon="email"
                :clearable="isEditing"
                :rules="$v_user.emailRules(employee.user.email)"
                label="E-mail"
                required
                :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 2 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="phoneNumber1"
                          v-model="employee.telephoneList[0].number"
                          type="phone"
                          prepend-icon="phone"
                          :clearable="isEditing"
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone"
                          required
                          :readonly="isEditing ? false : true">
            </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="phoneNumber2"
                          v-model="employee.telephoneList[1].number"
                          type="phone"
                          prepend-icon="phone"
                          :clearable="isEditing"
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone"
                          :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="phoneNumber3"
                          v-model="employee.telephoneList[2].number"
                          type="phone"
                          prepend-icon="phone"
                          :clearable="isEditing"
                          :rules="$v_user.phoneRule1(employee.telephoneList[0].number, employee.telephoneList[1].number, employee.telephoneList[2].number)"
                          label="Telefone"
                          :readonly="isEditing ? false : true">
            </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 3 -->
      <v-layout v-if="isEmployee">
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="salary"
                        v-model="employee.baseHourCalculation.salary"
                        type="text" :clearable="isEditing" required
                        prepend-icon="monetization_on"
                        :rules="$v_user.salaryRules(employee.baseHourCalculation.salary)"
                        label="Salário"
                        :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="pis"
                        max="11"
                        v-model="employee.pis"
                        type="text" :clearable="isEditing" required
                        prepend-icon="credit_card"
                        :rules="$v_user.pisRules(employee.pis)"
                        label="PIS/PASESP"
                        :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="workload"
                        v-model="employee.baseHourCalculation.workload"
                        type="text" :clearable="isEditing" required
                        prepend-icon="timer"
                        :rules="$v_user.workloadRules(employee.baseHourCalculation.workload)"
                        label="Carga Horária diaria"
                        :readonly="isEditing ? false : true">
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 4 -->
      <v-layout v-if="isEmployee">

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-select v-if="managerList"
            v-model="employee.manager.id"
            :items="managerList"
            item-text="name"
            item-value="id"
            prepend-icon="person"
            no-data-text="Não existem gestores cadastrados"
            label="Gestor"
            :readonly="isEditing ? false : true">
            <template slot="selection" slot-scope="data">
              {{ data.item.name }} {{ data.item.lastName }}
            </template>
            <template slot="item" slot-scope="data">
              {{ data.item.name }} {{ data.item.lastName }}
            </template>
          </v-select>
        </v-flex>

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-select v-if="hourTypeList" id="hourType"
            v-model="employee.baseHourCalculation.hourType.id"
            :items="hourTypeList"
            item-text="description"
            item-value="id"
            prepend-icon="timer"
            no-data-text="Não existem tipos de horas cadastrados"
            label="Tipo de Hora"
            :rules="$v_baseHour.hourTypeRules(employee.baseHourCalculation.hourType)"
            :readonly="isEditing ? false : true">
          </v-select>
        </v-flex>

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu ref="joiningDateHelper"
            v-model="joiningDateHelper"
            :close-on-content-click="false"
            :nudge-right="40"
            offset-y full-width
            transition="scale-transition"
            max-width="290px"
            min-width="290px">
            <v-text-field id="joiningDate"
              slot="activator"
              v-model="dateFormatted"
              label="Data Ingresso"
              persistent-hint readonly
              prepend-icon="event"
            ></v-text-field>
            <v-date-picker
              v-model="employee.joiningDate"
              header-color="black"
              @input="joiningDateHelper = false"
              locale="pt-br"
              :readonly="isEditing ? readonly : ''">
              </v-date-picker>
          </v-menu>
        </v-flex>

      </v-layout>

      <!-- Row 5 -->
      <v-layout v-if="isEmployee">
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
            v-model="employee.baseHourCalculation.overtimePercentage"
            label="Porcentagem hora extra"
            prepend-icon="percent"
            type="number"
            :clearable="isEditing"
            required
            :rules="$v_baseHour.a(employee.baseHourCalculation.overtimePercentage)"
            :readonly="isEditing ? false : true">
          ></v-text-field>
        </v-flex>

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
            v-model="employee.baseHourCalculation.nightOvertimePercentage"
            label="Porcentagem hora extra noturna"
            prepend-icon="percent"
            type="number"
            :clearable="isEditing"
            required
            :rules="$v_baseHour.b(employee.baseHourCalculation.nightOvertimePercentage)"
            :readonly="isEditing ? false : true">
          ></v-text-field>
        </v-flex>

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
            v-model="employee.baseHourCalculation.weekendOvertimePercentage"
            label="Porcentagem hora extra fim de semana"
            prepend-icon="percent"
            type="number"
            :clearable="isEditing"
            required
            :rules="$v_baseHour.c(employee.baseHourCalculation.weekendOvertimePercentage)"
            :readonly="isEditing ? false : true">
          ></v-text-field>

        </v-flex>
      </v-layout>

      <!-- Row 6 -->
      <v-layout xs12 sm9 md6 lg6 xl4 v-if="isEditing">
        <v-flex>
          <v-text-field id="password"
              v-model="employee.user.password"
              color="cyan darken"
              label="Senha"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordRules(employee.user.password, edit)"
              placeholder="Informe a Senha do Colaborador"
              loading
              :readonly="isEditing ? false : true">
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
              v-model="passwordValidator"
              color="cyan darken"
              label="Senha de Confirmação"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordValidationRules(employee.user.password, passwordValidator, edit)"
              placeholder="Confirme a Senha do Colaborador"
              loading
              :readonly="isEditing ? false : true">
            <v-progress-linear
                slot="progress"
                :value="progressValidation()"
                :color="colorValidation"
                height="7">
            </v-progress-linear>
          </v-text-field>
        </v-flex>
      </v-layout>

      <v-btn v-if="isEditing"
        id="submit"
        @click="submit"
        :disabled="!valid"
        color="success"
      >Salvar</v-btn>
      <v-btn v-if="isEditing"
        @click="clearForm"
        color="error"
      >Cancelar</v-btn>
    </v-form>
  </div>
</template>

<script>
import DateHelper from '@/helpers/DateHelper'
import Authenticator from '@/service/Authenticator'
export default {
  props: {
    edit: Boolean,
    editedEmployee: Object
  },
  data: () => ({
    hourTypeList: [],
    isManager: false,
    isEmployee: false,
    joiningDateHelper: false,
    dateFormatted: null,
    select: '',
    selectedType: '',
    passwordValidator: '',
    employee: {
      joiningDate: null,
      manager: {},
      baseHourCalculation: {
        hourType: {},
        overtimePercentage: 0,
        nightOvertimePercentage: 0,
        weekendOvertimePercentage: 0},
      user: {authorities: [], password: ''},
      telephoneList: [ {type: '', number: ''}, {type: '', number: ''}, {type: '', number: ''} ]
    },
    menu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    messageColor: '',
    valid: false,
    managerList: [],
    roleList: null,
    title: 'Cadastro de Usuário',
    isEditing: true,
    isAdmin: true
  }),
  async beforeMount () {
    try {
      let response = await this.$_axios.patch(`${this.$_url}role`, {})
      for (let role of response.data.resultList) {
        role.active = false
      }
      let parameter = await this.$_axios.patch(`${this.$_url}parameter`, {})
      parameter = parameter.data.resultList[0]
      this.employee.baseHourCalculation.overtimePercentage = parameter.overtimePercentage
      this.employee.baseHourCalculation.nightOvertimePercentage = parameter.nightOvertimePercentage
      this.employee.baseHourCalculation.weekendOvertimePercentage = parameter.weekendOvertimePercentage
      this.roleList = response.data.resultList
      response = await this.$_axios.patch(`${this.$_url}hourtype`, {})
      this.hourTypeList = response.data.resultList
      response = await this.$_axios.patch(`${this.$_url}employee`, {})
      let loggedUser = await this.$_axios.patch(`${this.$_url}employee`, {user: {id: Authenticator.GET_AUTHENTICATED().id}})
      let user = loggedUser.data.resultList[0]
      response.data.resultList.map(employee => {
        if (employee.hasOwnProperty('user')) {
          employee.user.authorities.map(role => {
            if (role.authority === 'ROLE_MANAGER') {
              // delete role.authority
              this.managerList.push(employee)
            }
          })
        } else {
          this.managerList.push(user)
        }
      })
    } catch (err) {
      console.log({err})
      this.messages = ['Erro durante execução do serviço!']
      this.haveMessage = true
      this.messageColor = 'error'
    }
  },
  mounted () {
    if (Authenticator.HAS_ROLE('ROLE_ADMIN')) {
      this.isAdmin = true
    } else {
      this.isAdmin = false
    }
  },
  computed: {
    computedDateFormatted () { return this.formatDate(this.employee.joiningDate) },
    colorValidation () { return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)] },
    color () { return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)] }
  },
  watch: {
    'employee.joiningDate' (val) {
      if (val.indexOf('/') === -1) {
        this.dateFormatted = this.formatDate(this.employee.joiningDate.substring(0, 10))
      } else {
        this.dateFormatted = val
      }
    },
    editedEmployee (value) {
      this.employee = Object.assign(this.employee, value)
      this.employee.user.authorities.forEach(r => {
        this.roleList.forEach(rl => {
          if (r.roleName === rl.roleName) rl.active = true
        })
        if (!this.isEmployee) {
          this.isEmployee = r.roleName === 'Colaborador'
        }
        if (!this.isManager) {
          this.isManager = r.roleName === 'Gestor'
        }
      })
      for (let i = this.employee.telephoneList.length; i < 3; i++) {
        this.employee.telephoneList = [...this.employee.telephoneList, {type: '', number: ''}]
      }
      this.title = `${this.employee.name} ${this.employee.lastName}`
      this.isEditing = false
    }
  },
  methods: {
    formatISODate (value) { return DateHelper.formatISODate(value) },
    progress () { return Math.min(100, this.employee.user.password.length * 10) },
    progressValidation () { return Math.min(100, this.passwordValidator.length * 10) },
    formatDate (date) {
      if (!date) return null
      const [year, month, day] = date.split('-')
      return `${day}/${month}/${year}`
    },
    parseDate (date) {
      if (!date) return null
      const [day, month, year] = date.split('/')
      return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
    },
    checkboxToggle (item) {
      let index = -1
      this.employee.user.authorities.forEach((r, ind) => {
        if (item.id === r.id) {
          index = ind
        }
      })
      if (item.active === false && index !== -1) {
        this.employee.user.authorities.splice(index, 1)
      } else if (item.active === true && index === -1) {
        let role = item
        this.employee.user.authorities.push(role)
      }
      if (item.active === true && item.roleName === 'Gestor') {
        this.isManager = true
      } else if (item.active === false && item.roleName === 'Gestor') {
        this.isManager = false
      }
      if (item.active === true && item.roleName === 'Colaborador') {
        this.isEmployee = true
      } else if (item.active === false && item.roleName === 'Colaborador') {
        this.isEmployee = false
      }
    },
    async submit () {
      try {
        this.messages = []
        this.haveMessage = false
        this.messageColor = ''
        let date = ''
        if (this.employee.joiningDate.indexOf('undefined')) {
          date = this.dateFormatted
        } else {
          this.formatDate(this.employee.joiningDate).replace(/-/g, '/')
        }
        this.employee.joiningDate = date
        this.employee.user.username = this.employee.user.email
        let response = ''
        if (!this.edit) {
          response = await this.$_axios.post(`${this.$_url}employee`, this.employee)
        } else {
          response = await this.$_axios.put(`${this.$_url}employee`, this.employee)
        }
        let result = response.data
        if (!this.edit) {
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
        } else {
          if (result.success) {
            this.$emit('closeDialog')
            this.clearForm()
          } else {
            this.messageColor = 'warning'
            this.messages = [...result.message]
            this.haveMessage = true
          }
        }
      } catch (err) {
        console.log(JSON.stringify(err), null, ' ')
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    clearForm () {
      if (!this.edit) {
        this.$refs.form.reset()
        this.passwordValidator = ''
        this.employee = {
          joiningDate: '',
          manager: {},
          baseHourCalculation: {hourType: {}},
          user: {roleList: [], password: ''},
          telephoneList: [ {type: '', number: ''}, {type: '', number: ''}, {type: '', number: ''} ]
        }
      } else {
        this.$emit('closeDialog')
      }
    }
  }
}
</script>
