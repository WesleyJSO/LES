<template>
  <div>

    <v-item-group>
      <v-container grid-list-md>
        <v-layout wrap>

          <v-flex md12>
            <v-hover>
              <v-card
                @click.native="showAllEmployees"
                color="#86BBD8"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesAll}}</h1>
                    <h3 class="text-xs-left headline ma-0">Total de horas trabalhadas: {{hoursAll}}</h3>
                    <h3 class="text-xs-left headline ma-0">A partir de: {{initialDate}}</h3>
                  </div>
                </v-card-title>

                <v-card-actions>
                  <div class="white--text">Visualizar</div>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex md3>
            <v-hover>
              <v-card
                @click.native="showOvertimeEmployees"
                color="#15B097"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesInOvertime}}</h1>
                    <h3 class="text-xs-left headline ma-0">Horas extras: {{hoursInOvertime}}</h3>
                  </div>
                </v-card-title>

                <v-card-actions>
                  <div class="white--text">Visualizar</div>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex md3>
              <v-hover>
              <v-card
              @click.native="showAbsenceEmployees"
                color="#FFEA4F"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesInAbsence}}</h1>
                    <h3 class="text-xs-left headline ma-0">Negativo: {{hoursInAbsence}}</h3>
                  </div>
                </v-card-title>

                <v-card-actions>
                  <div class="white--text">Visualizar</div>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex md3>
              <v-hover>
              <v-card
                @click.native="showPositiveLimitEmployees"
                color="#CC5A71"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesInLimitOfTowMonthsPositive}}</h1>
                    <h3 class="text-xs-left headline ma-0">Limite: {{hoursInLimitOfTowMonthsPositive}}</h3>
                  </div>
                </v-card-title>

                <v-card-actions>
                  <div class="white--text">Visualizar</div>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-flex>

          <v-flex md3>
              <v-hover>
              <v-card
                @click.native="showNegativeLimitEmployees"
                color="#80475E"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="text-xs-left headline mb-0">Funcionários: {{employeesInLimitOfTowMonthsNegative}}</h1>
                    <h3 class="text-xs-left headline ma-0">Limite: {{hoursInLimitOfTowMonthsNegative}}</h3>
                  </div>
                </v-card-title>

                <v-card-actions>
                  <div class="white--text">Visualizar</div>
                </v-card-actions>
              </v-card>
            </v-hover>
          </v-flex>

        </v-layout>
      </v-container>
    </v-item-group>

    <v-data-table
      :headers="headers"
      :items="employeesList"
      class="elevation-2"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.employeeFullName }}</td>
        <td class="text-xs-center">{{ props.item.shouldBeWorked }}</td>
        <td class="text-xs-center">{{ props.item.workedHoursOnTheParameterPeriod }}</td>
        <td class="text-xs-center">{{ props.item.balance }}</td>
        <td class="text-xs-center">{{ props.item.countingFromMonth }}</td>
        <td class="text-xs-center">{{ props.item.lastMonth }}</td>
        <td class="text-xs-center">{{ props.item.hoursInLastMonth }}</td>
        <td class="text-xs-center">{{ props.item.workedHoursComercial }}</td>
        <td class="text-xs-center">{{ props.item.workedHoursNight }}</td>
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
      { sortable: true, align: 'center', text: 'Horas ultimo mês', value: 'hoursInLastMonth' },
      { sortable: true, align: 'center', text: 'Horas comerciais', value: 'workedHoursComercial' },
      { sortable: true, align: 'center', text: 'Horas noturnas', value: 'workedHoursNight' }
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
    limitOfTwoMonthsNegative: []
  }),
  async beforeMount () {
    try {
      let response = await this.$_axios.patch(`${this.$_url}dashboard`, {})
      // console.table(response.data.resultList)

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
        return dashboard.countingFromMonthNumber >= dashboard.lastMonthNumber - 2 && dashboard.hoursInLastMonthMinutes > 0
      })
      this.employeesInLimitOfTowMonthsPositive = this.limitOfTwoMonthsPositive.length
      this.limitOfTwoMonthsPositive.forEach(element => {
        this.hoursInLimitOfTowMonthsPositive += element.hoursInLastMonthMinutes
      })
      this.hoursInLimitOfTowMonthsPositive = this.convertMinutesInHours(this.hoursInLimitOfTowMonthsPositive)

      this.limitOfTwoMonthsNegative = this.employees.filter(dashboard => {
        return dashboard.countingFromMonthNumber >= dashboard.lastMonthNumber - 2 && dashboard.hoursInLastMonthMinutes < 0
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
    showPositiveLimitEmployees () {
      this.employeesList = this.limitOfTwoMonthsPositive
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


