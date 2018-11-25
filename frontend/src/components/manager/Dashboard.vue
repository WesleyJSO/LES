<template>
  <div>

    <v-item-group>
      <v-container fluid grid-list-md>
        <v-layout row wrap>

          <v-flex d-flex md12>
            <v-hover>
              <v-card
                @click.native="showAllEmployees"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <h1 class="headline mb-0">Cálculos realizados a partir de: {{initialDate}}</h1>
                </v-card-title>
                <v-card-text class="pt-0 ma-0">
                  <h3>Colaboradores: {{employeesAll}}</h3>
                  <!-- <v-layout row justify-space-between>
                    <v-flex>
                      <h3>Total de horas trabalhadas no período: {{hoursAll}}</h3>
                    </v-flex>
                  </v-layout> -->
                </v-card-text>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex d-flex md3>
            <v-hover>
              <v-card
                @click.native="showOvertimeEmployees"
                color="#15B097"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesInOvertime}}</h1>
                </v-card-title>
                <v-card-text class="pt-0">
                  <h3 class="text-xs-left">Saldo positivo total: {{hoursInOvertime}}</h3>
                </v-card-text>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex d-flex md3>
              <v-hover>
              <v-card
              @click.native="showAbsenceEmployees"
                color="#FFEA4F"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesInAbsence}}</h1>
                </v-card-title>
                <v-card-text class="pt-0">
                  <h3 class="text-xs-left">Saldo negativo total: {{hoursInAbsence}}</h3>
                </v-card-text>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex d-flex md6>
              <v-hover>
              <v-card
                @click.native="showPositiveAndNegativeLimitEmployees"
                color="#BA1B1D"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                    <h1 class="headline mb-0">Funcionários: {{employeesInLimitOfTowMonthsPositive}}</h1>
                </v-card-title>
                <v-card-text class="pt-0">
                  <v-layout row justify-space-between>
                    <v-flex>
                      <h3>Saldo positivo em vencimento: {{hoursInLimitOfTowMonthsPositive}}</h3>
                    </v-flex>
                    <v-flex>
                      <h3>Saldo negativo em vencimento: {{hoursInLimitOfTowMonthsNegative}}</h3>
                    </v-flex>
                  </v-layout>
                </v-card-text>
              </v-card>
            </v-hover>
          </v-flex>

        </v-layout>
      </v-container>
    </v-item-group>

    <v-data-table
      rows-per-page-text="Linhas por página"
      :headers="headers"
      :items="employeesList"
      class="elevation-2"
      item-key="employeeFullName"
    >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.employeeFullName }}</td>
          <td class="text-xs-center">{{ props.item.shouldBeWorked }}</td>
          <td class="text-xs-center">{{ props.item.workedHoursOnTheParameterPeriod }}</td>
          <td class="text-xs-center">{{ props.item.balance }}</td>
          <td class="text-xs-center">{{ props.item.countingFromMonth }}</td>
          <td class="text-xs-center">{{ props.item.lastMonth }}</td>
          <td class="text-xs-center">{{ props.item.hoursInLastMonth }}</td>
          <!-- <td class="text-xs-center">{{ props.item.workedHoursComercial }}</td>
          <td class="text-xs-center">{{ props.item.workedHoursNight }}</td> -->
        </tr>
      </template>

      <template slot="expand" slot-scope="props">
        <v-layout justify-space-between>
          <v-flex xs2 sm2 md2 lg2 xl2 v-for="monthAppointment in props.item.employeeEvaluatedBalanceList" :key="monthAppointment.id">
            <v-card flat class="subRow" color="#F7F4F3">
              <v-card-title primary-title>
                <h3 class="headline ma-0">{{ moment(monthAppointment.monthAndYear.toString().substring(5, 7), 'MM').format('MMMM') }}</h3>
              </v-card-title>
              <v-card-title>
                <v-layout row justify-space-between>
                  <v-flex>
                    Positivo<br/>{{ (monthAppointment.overtimeHours.toString().length === 1
                      ? `0${monthAppointment.overtimeHours}`
                      : monthAppointment.overtimeHours)
                      + ':'
                      + (monthAppointment.overtimeMinutes.toString().length === 1
                      ? `0${monthAppointment.overtimeMinutes}`
                      : monthAppointment.overtimeMinutes) }}
                  </v-flex>
                  <v-flex>
                    Negativo<br/>{{ (monthAppointment.abscenseHours.toString().length === 1
                      ? `0${monthAppointment.abscenseHours}`
                      : monthAppointment.abscenseHours)
                      + ':'
                      + (monthAppointment.abscenseMinutes.toString().length === 1
                      ? `0${monthAppointment.abscenseMinutes}`
                      : monthAppointment.abscenseMinutes) }}
                  </v-flex>
                </v-layout>
            </v-card-title>
            </v-card>
          </v-flex>
        </v-layout>
      </template>

      <template slot="no-data">
        Não existem dados para o item selecionado.
      </template>

      <template slot="pageText" slot-scope="props">
        Exibindo {{ props.pageStart }} - {{ props.pageStop }} de {{ props.itemsLength }}
      </template>

    </v-data-table>

  </div>
</template>

<script>
export default {
  data: () => ({
    headers: [
      { sortable: true, align: 'left', text: 'Funcionários', value: 'employeeFullName' },
      { sortable: true, align: 'center', text: 'Carga horária', value: 'shouldBeWorked' },
      { sortable: true, align: 'center', text: 'Horas trabalhadas', value: 'workedHoursOnTheParameterPeriod' },
      { sortable: true, align: 'center', text: 'Saldo', value: 'Balance' },
      { sortable: true, align: 'center', text: 'A partir de', value: 'countingFromMonth' },
      { sortable: true, align: 'center', text: 'Ultimo mês débito', value: 'lastMonth' },
      { sortable: true, align: 'center', text: 'Horas ultimo mês', value: 'hoursInLastMonth' }
      // { sortable: true, align: 'center', text: 'Horas comerciais', value: 'workedHoursComercial' },
      // { sortable: true, align: 'center', text: 'Horas noturnas', value: 'workedHoursNight' }
    ],
    initialDate: 0,
    employeesAll: 0,
    hoursAll: 0,
    employeesInOvertime: 0,
    hoursInOvertime: 0,
    employeesInAbsence: 0,
    hoursInAbsence: 0,
    employeesInLimitOfTowMonthsPositive: 0,
    hoursInLimitOfTowMonthsPositive: 0,
    employeesInLimitOfTowMonthsNegative: 0,
    hoursInLimitOfTowMonthsNegative: 0,
    employeesList: [],
    employees: [],
    overtimeTotal: [],
    absenceTotal: [],
    limitOfTwoMonthsPositive: [],
    limitOfTwoMonthsNegative: [],
    moment: {}
  }),
  async beforeMount () {
    this.moment = this.$_moment
    try {
      let response = await this.$_axios.patch(`${this.$_url}dashboard`, {})
      console.table(response.data.resultList)

      this.employeesList = response.data.resultList
      this.employees = this.employeesList
      this.initialDate = this.employees[0].countingFromMonth
      this.employeesAll = this.employees.length
      this.employees.forEach(element => {
        this.hoursAll += element.workedHoursOnTheParameterPeriodMinutes
      })
      this.hoursAll = this.convertMinutesInHours(this.hoursAll)

      this.overtimeTotal = this.employees.filter(dashboard => dashboard.balanceInMinutes > 0)
      this.employeesInOvertime = this.overtimeTotal.length
      this.overtimeTotal.forEach(element => {
        this.hoursInOvertime += element.balanceInMinutes
      })
      this.hoursInOvertime = this.convertMinutesInHours(this.hoursInOvertime)

      this.absenceTotal = this.employees.filter(dashboard => dashboard.balanceInMinutes < 0)
      this.employeesInAbsence = this.absenceTotal.length
      this.absenceTotal.forEach(element => {
        this.hoursInAbsence += element.balanceInMinutes
      })
      this.hoursInAbsence = this.convertMinutesInHours(this.hoursInAbsence)

      this.limitOfTwoMonthsPositive = this.employees.filter(dashboard => {
        return dashboard.countingFromMonthNumber >= dashboard.lastMonthNumber && dashboard.hoursInLastMonthMinutes > 0
      })
      this.employeesInLimitOfTowMonthsPositive = this.limitOfTwoMonthsPositive.length
      this.limitOfTwoMonthsPositive.forEach(element => {
        this.hoursInLimitOfTowMonthsPositive += element.hoursInLastMonthMinutes
      })
      this.hoursInLimitOfTowMonthsPositive = this.convertMinutesInHours(this.hoursInLimitOfTowMonthsPositive)

      this.limitOfTwoMonthsNegative = this.employees.filter(dashboard => {
        return dashboard.countingFromMonthNumber >= dashboard.lastMonthNumber && dashboard.hoursInLastMonthMinutes < 0
      })
      this.employeesInLimitOfTowMonthsNegative = this.limitOfTwoMonthsNegative.length
      this.limitOfTwoMonthsNegative.forEach(element => {
        this.hoursInLimitOfTowMonthsNegative += element.hoursInLastMonthMinutes
      })
      this.hoursInLimitOfTowMonthsNegative = this.convertMinutesInHours(this.hoursInLimitOfTowMonthsNegative)
    } catch (err) {
      console.log({err})
    }
  },
  methods: {
    showAllEmployees () {
      this.employeesList = this.employees
    },
    showOvertimeEmployees () {
      this.employeesList = this.overtimeTotal
    },
    showAbsenceEmployees () {
      this.employeesList = this.absenceTotal
    },
    showPositiveAndNegativeLimitEmployees () {
      this.employeesList = this.limitOfTwoMonthsPositive
      this.employees.push(...this.limitOfTwoMonthsNegative)
    },
    showNegativeLimitEmployees () {
      this.employeesList = this.limitOfTwoMonthsNegative
    },
    convertMinutesInHours (minutes) {
      if (JSON.stringify(minutes) === '[]') {
        return 0
      }
      let hour
      if (minutes > 0) hour = Math.floor(minutes / 60)
      else hour = Math.ceil(minutes / 60)
      let helper = ''
      for (let i = 0; i < hour.toString().length; i++) {
        let charAt = hour.toString().charAt(i)
        if (i < hour.toString().length - 3 && i % 3 === 0) helper = `${charAt}.`
        else helper += charAt
      }
      hour = helper
      let minute = minutes - (hour).toString().replace('.', '') * 60
      minute = minute.toString().replace('-', '')
      if (minute.length < 2) {
        minute = `0${minute}`
      }
      return `${hour}:${minute}`
    }
  }
}
</script>


