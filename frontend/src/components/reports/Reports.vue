<template>
  <div>
    <li v-for="(message, index) in messages" :key="index">
    <v-alert :color="messageColor"
              :value="haveMessage"
              v-text="message"
              transition="scale-transition" />
  </li>
  <br/>
    <v-toolbar dark tabs>
      <v-layout row>
          <v-flex md2 class="pt-2">
            <v-btn
              id="submit"
              type="button"
              :color="getColors.info"
              @click="submit"
            >Consultar</v-btn>
          </v-flex>

          <v-flex md2>
            <v-menu
              :close-on-content-click="false"
              v-model="pickerInitialDate"
              :nudge-right="40"
              lazy offset-y full-width
              transition="scale-transition"
              min-width="290px">
              <v-text-field
                :label="getLabels.startDate"
                v-model="computedInitialQueryDate"
                slot="activator"
                prepend-icon="event"
                readonly>
              </v-text-field>

              <v-date-picker
                :locale="getDateConfig.locale"
                :header-color="getColors.black"
                :max="maxEndDate"
                v-model="chartFilter.initialQueryDate"
                @input="pickerInitialDate = false">
              </v-date-picker>
            </v-menu>
          </v-flex>

          <v-flex md2>
            <v-menu
              :close-on-content-click="false"
              v-model="pickerFinalDate"
              :nudge-right="40"
              lazy offset-y full-width
              transition="scale-transition"
              min-width="290px">
              <v-text-field
                :label="getLabels.endDate"
                v-model="computedFinalQueryDate"
                slot="activator"
                prepend-icon="event"
                readonly>
              </v-text-field>

              <v-date-picker
                :locale="getDateConfig.locale"
                :min="minEndDate"
                :max="maxEndDate"
                :header-color="getColors.black"
                v-model="chartFilter.finalQueryDate"
                @input="pickerFinalDate = false">
              </v-date-picker>
            </v-menu>
          </v-flex>

          <v-flex md6>
            <v-select
              :label="getLabels.employees"
              prepend-icon="supervisor_account"
              :items="employees"
              multiple small-chips
              type="Object"
              item-text="lastName"
              item-value="id"
              v-model="chartFilter.employeeNameList">
            </v-select>
          </v-flex>
        </v-layout>






      <v-tabs
        slot="extension"
        v-model="tabs"  centered
        slider-color="yellow"
      >
        <v-tab v-for="tabName in getTabsName" :key="tabName">
          {{ tabName }}
        </v-tab>
      </v-tabs>
    </v-toolbar>

    <v-tabs-items v-model="tabs">
      <v-tab-item>
        <v-card>
          <v-card-text>
            <line-chart ref="chartObject1" :download="true"
              :title="getReportTitle.workedHours"
              :stacked="false"
              :xtitle="getReportAxis.x.days"
              :ytitle="getReportAxis.y.hours"
              discrete="true"
              height="430px"
              :data="getWokedHours"
            />
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card>
          <v-card-text>
            <column-chart ref="chartObject2" :download="true"
              :stacked="false"
              :title="getReportTitle.lunchHours"
              :xtitle="getReportAxis.x.days"
              :ytitle="getReportAxis.y.hours"
              discrete="true"
              height="430px"
              :data="getLunchHours"
            />
          </v-card-text>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card>
          <v-card-text>
            <bar-chart ref="chartObject3" :download="true"
              :stacked="false"
              :title="getReportTitle.workOvertime"
              :ytitle="getReportAxis.x.days"
              :xtitle="getReportAxis.y.hours"
              discrete="true"
              height="430px"
              :data="getWorkOvertime"
            />
          </v-card-text>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>


<script>
// import DateHelper from '../../helpers/DateHelper.js'
import ReportService from '@/service/ReportService'
export default {
  data: () => ({
    tabName1: 'HORAS TRABALHADAS',
    tabName2: 'HORAS DE ALMOÇO',
    tabName3: 'HORAS EXTRAS',
    tabs: null,
    dasa: 'fds',
    text: 'nfasohfodsanhfbadsfnhuicjnhuadsbfui',
    messages: [],
    valid: false,
    pickerInitialDate: false,
    pickerFinalDate: false,
    startDate: null,
    endDate: null,
    employeesList: [],
    employees: [],
    a: null,
    chartFilter: {},
    selectedEmployesList: [],
    chartData: null,
    chart: {
      workOvertime: {},
      workedHours: {},
      lunchHours: {}
    }
  }),
  mounted () {
    this.getEmployees()
  },
  methods: {
    async submit () {
      let result = await this.$_axios.patch(`${this.$_url}chartfilter`, this.chartFilter)
      result = result.data.resultList[0].appointmentList
      let chartData = {}
      chartData.workedHours = this.workedHours(result)
      chartData.workOvertime = this.workOvertime(result)
      chartData.lunchHours = this.lunchHours(result)
      this.chart = await chartData
    },
    getEmployees () {
      let employee = {}
      this.$_axios.patch(`${this.$_url}employee`, employee).then((response) => {
        let resultado = response.data
        if (resultado.resultList.length >= 0) {
          this.employees = resultado.resultList
        }
        if (resultado.message) {
          this.messages = [...resultado.message]
          this.haveMessage = true
          if (resultado.sucesso) {
            // Success message
            this.messageColor = 'info'
          } else {
            // Waring message
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        console.log(JSON.stringify(response, null, ' '))
        // Error during request
        this.messages = ['Houve um erro ao carregar a lista de Colaboradores!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    workOvertime (list) {
      let dates = list.map(i => {
        let parsedDate = {}
        if (!this.a || this.a.id !== i.employee) {
          this.a = i.employee
        }
        parsedDate.employee = this.a.id
        parsedDate.name = this.a.name + ' ' + this.a.lastName
        parsedDate.date = this.formatDate(i.date.substring(0, this.getDateConfig.date))
        parsedDate.dayOvertime = i.dayOvertime.substring(0, 5).replace(':', '.')
        return parsedDate
      })
      let parsedItem = []
      let dataItem = {}
      dataItem.data = {}
      dataItem.name = ''
      for (let i = 0; i < dates.length; i++) {
        if (!dataItem.name) {
          dataItem.name = dates[i].name
        }
        if (dataItem.name === dates[i].name) {
          dataItem.data[dates[i].date] = dates[i].dayOvertime
        } else if (dataItem.name !== dates[i].name) {
          parsedItem.push(Object.assign({}, dataItem))
          dataItem.name = dates[i].name
          dataItem.data = {}
          dataItem.data[dates[i].date] = dates[i].dayOvertime
        }
        if (i === (dates.length - 1)) {
          parsedItem.push(Object.assign({}, dataItem))
        }
      }
      return parsedItem
    },
    workedHours (list) {
      let dates = list.map(i => {
        let parsedDate = {}
        if (!this.a || this.a.id !== i.employee) {
          this.a = i.employee
        }
        parsedDate.employee = this.a.id
        parsedDate.name = this.a.name + ' ' + this.a.lastName
        parsedDate.date = this.formatDate(i.date.substring(0, this.getDateConfig.date))
        parsedDate.balance = i.balance.substring(0, 5).replace(':', '.')
        return parsedDate
      })
      let parsedItem = []
      let dataItem = {}
      dataItem.data = {}
      dataItem.name = ''
      for (let i = 0; i < dates.length; i++) {
        if (!dataItem.name) {
          dataItem.name = dates[i].name
        }
        if (dataItem.name === dates[i].name) {
          dataItem.data[dates[i].date] = dates[i].balance
        } else if (dataItem.name !== dates[i].name) {
          parsedItem.push(Object.assign({}, dataItem))
          dataItem.name = dates[i].name
          dataItem.data = {}
          dataItem.data[dates[i].date] = dates[i].balance
        }
        if (i === (dates.length - 1)) {
          parsedItem.push(Object.assign({}, dataItem))
        }
      }
      return parsedItem
    },
    lunchHours (list) {
      let dates = list.map(i => {
        let parsedDate = {}
        if (!this.a || this.a.id !== i.employee) {
          this.a = i.employee
        }
        parsedDate.employee = this.a.id
        parsedDate.name = this.a.name + ' ' + this.a.lastName
        parsedDate.date = this.formatDate(i.date.substring(0, this.getDateConfig.date))
        let morning = Number(i.morningOut.substring(0, this.getDateConfig.hour).replace(':', '.'))
        let afterNoon = Number(i.afternoonEntrance.substring(0, this.getDateConfig.hour).replace(':', '.'))
        parsedDate.lunch = (afterNoon - morning).toString().substring(0, 5)
        return parsedDate
      })
      let parsedItem = []
      let dataItem = {}
      dataItem.data = {}
      dataItem.name = ''
      for (let i = 0; i < dates.length; i++) {
        if (!dataItem.name) {
          dataItem.name = dates[i].name
        }
        if (dataItem.name === dates[i].name) {
          dataItem.data[dates[i].date] = dates[i].lunch
        } else if (dataItem.name !== dates[i].name) {
          parsedItem.push(Object.assign({}, dataItem))
          dataItem.name = dates[i].name
          dataItem.data = {}
          dataItem.data[dates[i].date] = dates[i].lunch
        }
        if (i === (dates.length - 1)) {
          parsedItem.push(Object.assign({}, dataItem))
        }
      }
      return parsedItem
    },
    formatDate (date) {
      return this.$_moment(new Date(date)).add(1, 'days').format('DD/MM/YY')
    }
  },
  computed: {
    computedInitialQueryDate () {
      if (this.chartFilter.initialQueryDate) {
        return this.formatDate(this.chartFilter.initialQueryDate)
      } else {
        return ''
      }
    },
    computedFinalQueryDate () {
      if (this.chartFilter.finalQueryDate) {
        return this.formatDate(this.chartFilter.finalQueryDate)
      } else {
        return ''
      }
    },
    getWorkOvertime () {
      return this.chart.workOvertime
    },
    getWokedHours () {
      return this.chart.workedHours
    },
    getLunchHours () {
      return this.chart.lunchHours
    },
    getTitles () {
      return ReportService.TITLES
    },
    getLabels () {
      return ReportService.LABELS
    },
    getDateConfig () {
      return ReportService.DATE_CONFIG
    },
    getColors () {
      return ReportService.COLORS
    },
    getTabsName () {
      return ReportService.TABS_NAME
    },
    getFrequency () {
      return ReportService.FREQUECY
    },
    getReportTitle () {
      return ReportService.REPORT_TITLE
    },
    getReportAxis () {
      return ReportService.REPORT_AXIS
    },
    minEndDate () {
      let endDate = !this.chartFilter.initialQueryDate ? new Date() : new Date(this.chartFilter.initialQueryDate)
      return endDate.toISOString()
    },
    maxEndDate () {
      return this.$_moment(new Date()).toISOString()
    }
  }
}
</script>
