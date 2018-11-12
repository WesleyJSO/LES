<template>
  <div>
     <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <h1>{{getTitles.report}}</h1>
    <v-container fluid>
      <v-layout wrap column >

        <v-form ref="form" v-model="valid">
          <v-layout >
            <v-flex xs12 d-flex>
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

          <v-layout>
            <!-- initial query date -->
            <v-flex xs6 sm6 md6>
              <v-menu
                :close-on-content-click="false"
                v-model="pickerInitialDate"
                :nudge-right="40"
                lazy offset-y full-width
                transition="scale-transition"
                min-width="290px">
                <v-text-field
                  :label="getLabels.startDate"
                  v-model="chartFilter.initialQueryDate"
                  slot="activator"
                  prepend-icon="event"
                  readonly>
                </v-text-field>

                <v-date-picker
                  :locale="getDateConfig.locale"
                  :header-color="getColors.black"
                  v-model="chartFilter.initialQueryDate"
                  @input="pickerInitialDate = false">
                </v-date-picker>
              </v-menu>
            </v-flex>

            <!-- final query date -->
            <v-flex xs6 sm6 md6>
              <v-menu
                :close-on-content-click="false"
                v-model="pickerFinalDate"
                :nudge-right="40"
                lazy offset-y full-width
                transition="scale-transition"
                min-width="290px">
                <v-text-field
                  :label="getLabels.endDate"
                  v-model="chartFilter.finalQueryDate"
                  slot="activator"
                  prepend-icon="event"
                  readonly>
                  </v-text-field>

                <v-date-picker
                  :locale="getDateConfig.locale"
                  :min="minEndDate"
                  :header-color="getColors.black"
                  v-model="chartFilter.finalQueryDate"
                  @input="pickerFinalDate = false">
                  </v-date-picker>
              </v-menu>
            </v-flex>
          </v-layout>

          <v-layout pb-5>
            <v-btn
              id="submit"
              type="button"
              :color="getColors.info"
              :disabled="!valid"
              @click="submit"
            >Consultar</v-btn>
          </v-layout>
        </v-form>
        <!-- <v-layout pb-5>
          <v-flex xs12 sm9 md6 lg6 xl4>
            <v-menu
              ref="joiningDateHelper"
              :close-on-content-click="false"
              v-model="joiningDateHelper"
              :nudge-right="40"
              :return-value.sync="joiningDate"
              lazy
              min-width="290px"
              transition="scale-transition"
              full-width
            >
            <v-text-field
              slot="activator"
              v-model="joiningDate"
              label="Data inícial"
              prepend-icon="event"
              readonly
            >
            </v-text-field>
            <v-date-picker
              v-model="joiningDate"
              header-color="black"
              :reactive="reactive"
              @input="$refs.joiningDateHelper.save(joiningDate)"
            >
            </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu
            ref="joiningDateHelper"
            :close-on-content-click="false"
            v-model="joiningDateHelper"
            :nudge-right="40"
            :return-value.sync="joiningDate"
            lazy
            min-width="290px"
            transition="scale-transition"
            full-width
          >
            <v-text-field
              slot="activator"
              v-model="joiningDate"
              label="Data final"
              prepend-icon="event"
              readonly
            >
            </v-text-field>
            <v-date-picker
              v-model="joiningDate"
              header-color="black"
              :reactive="reactive"
              @input="$refs.joiningDateHelper.save(joiningDate)"
            >
            </v-date-picker>
          </v-menu>
        </v-flex>
      </v-layout> -->

        <v-flex xs12>
          <v-tabs dark slider-color="yellow" >
            <v-tab v-for="tabName in getTabsName" :key="tabName" ripple>
              {{ tabName }}
            </v-tab>

            <v-tab-item>
              <v-form flat>
                <line-chart ref="chartObject1" :download="true"
                  :title="getReportTitle.workedHours"
                  :stacked="false"
                  :xtitle="getReportAxis.x.days"
                  :ytitle="getReportAxis.y.hours"
                  discrete="true"
                  height="400px"
                  :data="getWokedHours"
                />
              </v-form>
            </v-tab-item>

            <v-tab-item>
              <v-form flat>
                <column-chart ref="chartObject2" :download="true"
                  :stacked="false"
                  :title="getReportTitle.lunchHours"
                  :xtitle="getReportAxis.x.days"
                  :ytitle="getReportAxis.y.hours"
                  discrete="true"
                  height="400px"
                  :data="getLunchHours"
                />
              </v-form>
            </v-tab-item>

            <v-tab-item>
              <v-form flat>
                <bar-chart ref="chartObject3" :download="true"
                  :stacked="false"
                  :title="getReportTitle.workOvertime"
                  :ytitle="getReportAxis.x.days"
                  :xtitle="getReportAxis.y.hours"
                  discrete="true"
                  height="400px"
                  :data="getWorkOvertime"
                />
              </v-form>
            </v-tab-item>

          </v-tabs>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>


<script>
// import DateHelper from '../../helpers/DateHelper.js'
import ReportService from '@/service/ReportService'
export default {
  data: () => ({
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
  // async beforeMount () {
  //   this.chartData = await this.$_axios.patch(`${this.$_url}appointment`)
  //   this.chart = await this.chartData.data
  // },
  mounted () {
    this.getEmployees()
  },
  methods: {
    async submit () {
      let result = await this.$_axios.patch(`${this.$_url}chartfilter`, this.chartFilter)
      let chartData = {}
      chartData.workedHours = this.workedHours(result.data.resultList)
      chartData.workOvertime = this.workOvertime(result.data.resultList)
      chartData.lunchHours = this.lunchHours(result.data.resultList)
      this.chart = await chartData
    },
    changeFrequency (frequency) {
      // this.$children[2].destroyElement()
      // console.log(this.$children[2])
      // let today = new Date()
      // switch (frequency) {
      //   case 'Semana':
      //     for (let index in this.chart) {
      //       for (let date in this.chart[index].data) {
      //         // let name = chart[index].name
      //         // let hours = chart[index].data[date]
      //         if (DateHelper.diffDates(new Date(date), today) > 3) {
      //           console.log(`Item removed: ${this.chart[index].data[date]}`)
      //           delete this.chart[index].data[date]
      //         }
      //       }
      //     }
      //     this.$refs.chartObject1.updateChart()
      //     this.$refs.chartObject2.updateChart()
      //     this.$refs.chartObject3.updateChart()
      // }
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
        parsedDate.date = i.date.substring(0, this.getDateConfig.date)
        parsedDate.dayOvertime = i.dayOvertime.substring(0, this.getDateConfig.hour).replace(':', '.')
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
        parsedDate.date = i.date.substring(0, this.getDateConfig.date)
        parsedDate.balance = i.balance.substring(0, this.getDateConfig.hour).replace(':', '.')
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
        parsedDate.date = i.date.substring(0, this.getDateConfig.date)
        let morning = Number(i.morningOut.substring(0, this.getDateConfig.hour).replace(':', '.'))
        let afterNoon = Number(i.afternoonEntrance.substring(0, this.getDateConfig.hour).replace(':', '.'))
        parsedDate.lunch = afterNoon - morning
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
    }
  },
  computed: {
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
    }
  }
}
</script>
