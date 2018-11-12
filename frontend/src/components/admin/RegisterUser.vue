<template>
    <div>
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
        <v-flex xs4 sm3 md2 lg2 v-for="v in roleList" :key="v.id">
          <v-checkbox :id="`checkBox${v.id}`"
            v-model="v.active"
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
                v-model="employee.user.email"
                type="email"
                prepend-icon="email"
                clearable
                :rules="$v_user.emailRules(employee.user.email)"
                label="E-mail"
                required>
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

      <!-- Row 3 -->
      <v-layout v-if="isEmployee">
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="salary"
                        v-model="employee.baseHourCalculation.salary"
                        type="number" clearable required
                        prepend-icon="monetization_on"
                        :rules="$v_user.salaryRules(employee.baseHourCalculation.salary)"
                        label="Salário" >
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="pis"
                        v-model="employee.pis"
                        type="number" clearable required
                        prepend-icon="credit_card"
                        :rules="$v_user.pisRules(employee.pis)"
                        label="PIS/PASESP" >
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field id="workload"
                        v-model="employee.baseHourCalculation.workload"
                        type="number" clearable required
                        prepend-icon="timer"
                        :rules="$v_user.workloadRules(employee.baseHourCalculation.workload)"
                        label="Carga Horária diaria" >
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- Row 4 -->
      <v-layout v-if="isEmployee">

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-select v-if="managerList" id="supervisor"
            v-model="employee.manager.id"
            :items="managerList"
            item-text="name"
            item-value="id"
            prepend-icon="person"
            no-data-text="Não existem gestores cadastrados"
            label="Gestor"
          >
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
            :rules="$v_baseHour.hourTypeRules(employee.baseHourCalculation.hourType)">
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
              @blur="employee.joiningDate = parseDate(dateFormatted)"
            ></v-text-field>
            <v-date-picker
              v-model="employee.joiningDate"
              header-color="black"
              @input="joiningDateHelper = false"
              locale="pt-br"
            ></v-date-picker>
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
            clearable
            required
            :rules="$v_baseHour.a(employee.baseHourCalculation.overtimePercentage)">
          ></v-text-field>
        </v-flex>

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
            v-model="employee.baseHourCalculation.nightOvertimePercentage"
            label="Porcentagem hora extra noturna"
            prepend-icon="percent"
            type="number"
            clearable
            required
            :rules="$v_baseHour.b(employee.baseHourCalculation.nightOvertimePercentage)">
          ></v-text-field>
        </v-flex>

        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field
            v-model="employee.baseHourCalculation.weekendOvertimePercentage"
            label="Porcentagem hora extra fim de semana"
            prepend-icon="percent"
            type="number"
            clearable
            required
            :rules="$v_baseHour.c(employee.baseHourCalculation.weekendOvertimePercentage)">
          ></v-text-field>

        </v-flex>
      </v-layout>

      <!-- Row 6 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field id="password"
              v-model="employee.user.password"
              color="cyan darken"
              label="Senha"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordRules(employee.user.password)"
              placeholder="Informe a Senha do Colaborador"
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
              v-model="passwordValidator"
              color="cyan darken"
              label="Senha de Confirmação"
              type="password"
              prepend-icon="fingerprint"
              :rules="$v_user.passwordValidationRules(employee.user.password, passwordValidator)"
              placeholder="Confirme a Senha do Colaborador"
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
        <v-btn
          id="submit"
          type="submit"
          @click="submit"
          :disabled="!valid"
          color="success"
        >Salvar</v-btn>
        <v-btn
          @click="clearForm"
          color="error"
        >Cancelar</v-btn>
      </div>
    </v-form>
  </div>
</template>

<script>
export default {
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
      user: {roleList: [], password: ''},
      telephoneList: [ {type: '', number: ''}, {type: '', number: ''}, {type: '', number: ''} ]
    },
    menu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    messageColor: '',
    edit: false,
    valid: false,
    managerList: [],
    roleList: null
  }),
  async beforeMount () {
    try {
      let response = await this.$_axios.patch(`${this.$_url}role`, {})
      for (let role of response.data.resultList) {
        role.active = false
      }
      let parameter = await this.$_axios.patch(`${this.$_url}parameter`, {})
      parameter = parameter.data.resultList[0]
      console.log(JSON.stringify(parameter))
      this.employee.baseHourCalculation.overtimePercentage = parameter.overtimePercentage
      this.employee.baseHourCalculation.nightOvertimePercentage = parameter.nightOvertimePercentage
      this.employee.baseHourCalculation.weekendOvertimePercentage = parameter.weekendOvertimePercentage
      this.roleList = response.data.resultList
      response = await this.$_axios.patch(`${this.$_url}hourtype`, {})
      this.hourTypeList = response.data.resultList
      response = await this.$_axios.patch(`${this.$_url}employee`, {})
      response.data.resultList.map(employee => {
        employee.user.authorities.map(authority => {
          if (authority.role === 'ROLE_MANAGER') {
            delete authority.authority
            this.managerList.push(employee)
          }
        })
      })
    } catch (err) {
      console.log({err})
      this.messages = ['Erro durante execução do serviço!']
      this.haveMessage = true
      this.messageColor = 'error'
    }
  },
  computed: {
    computedDateFormatted () { return this.formatDate(this.employee.joiningDate) },
    colorValidation () { return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)] },
    color () { return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)] },
    title () { return this.edit ? 'Alterar Usuário' : 'Cadastro de Usuário' }
  },
  watch: {
    'employee.joiningDate' (val) {
      this.dateFormatted = this.formatDate(this.employee.joiningDate)
    }
  },
  methods: {
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
      let index = this.employee.user.roleList.indexOf(item)
      if (item.active === false && index !== -1) {
        this.employee.user.roleList.splice(index, 1)
      } else if (item.active === true && index === -1) {
        let role = {id: item.id}
        this.employee.user.roleList.push(role)
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
        console.log(JSON.stringify(this.employee))
        let response = await this.$_axios.post(`${this.$_url}employee`, this.employee)
        console.log(JSON.stringify(response))
        let result = response.data
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
      } catch (err) {
        console.log(JSON.stringify(err))
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    clearForm () {
      this.$refs.form.reset()
      this.passwordValidator = ''
      this.employee = {
        joiningDate: null,
        manager: {},
        baseHourCalculation: {hourType: {}},
        user: {roleList: [], password: ''},
        telephoneList: [ {type: '', number: ''}, {type: '', number: ''}, {type: '', number: ''} ]
      }
    }
  }
}
</script>
