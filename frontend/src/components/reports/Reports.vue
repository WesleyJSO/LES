<template>
  <div>
  <h1>Gráficos</h1>
    <v-container fluid>
      <v-layout wrap column >

        <v-form ref="form" v-model="valid">
          <v-layout >
            <v-flex xs12 d-flex>
              <v-select
                label="Funcionários"
                prepend-icon="supervisor_account"
                :items="employees"
                multiple small-chips
                v-model="employeeNameList"
              ></v-select>
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
                min-width="290px"
              >
                <v-text-field
                  label="Data inicial"
                  v-model="initialQueryDate"
                  slot="activator"
                  prepend-icon="event"
                  readonly
                ></v-text-field>

                <v-date-picker
                  locale="pt-br"
                  header-color="black"
                  v-model="initialQueryDate"
                  @input="pickerInitialDate = false"
                ></v-date-picker>
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
                min-width="290px"
              >
                <v-text-field
                  label="Data final"
                  v-model="finalQueryDate"
                  slot="activator"
                  prepend-icon="event"
                  readonly
                ></v-text-field>

                <v-date-picker
                  locale="pt-br"
                  header-color="black"
                  v-model="finalQueryDate"
                  @input="pickerFinalDate = false"
                ></v-date-picker>
              </v-menu>
            </v-flex>
          </v-layout>

          <v-layout pb-5>
            <v-btn
              id="submit"
              type="submit"
              color="info"
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
            <v-tab v-for="tabName in tabs" :key="tabName" ripple>
              {{ tabName }}
            </v-tab>

            <v-tab-item>
              <v-form flat>
                <line-chart ref="chartObject1" :download="true"
                  :stacked="false"
                  title="Horas Trabalhadas"
                  xtitle="Dias"
                  ytitle="Horas"
                  discrete="true"
                  height="400px"
                  :data="chart"
                />
              </v-form>
            </v-tab-item>

            <v-tab-item>
              <v-form flat>
                <column-chart ref="chartObject2" :download="true"
                  :stacked="false"
                  title="Horas de Almoço"
                  xtitle="Dias"
                  ytitle="Horas"
                  discrete="true"
                  height="400px"
                  :data="chartFinal"
                />
              </v-form>
            </v-tab-item>

            <v-tab-item>
              <v-form flat>
                <bar-chart ref="chartObject3" :download="true"
                  :stacked="false"
                  title="Horas Extras"
                  xtitle="Horas"
                  ytitle="Dias"
                  discrete="true"
                  height="400px"
                  :data="chartFinal"
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
export default {
  data: () => ({
    valid: false,
    pickerInitialDate: false,
    pickerFinalDate: false,
    initialQueryDate: null,
    finalQueryDate: null,
    employeeNameList: [],
    chartFilter: {},
    tabs: [ 'Horas Trabalhadas', 'Horas de Almoço', 'Horas Extras' ],
    frequency: [ 'Semana', 'Mês', 'Ano' ],
    employees: [ 'Wesley', 'Zeller', 'Bruno', 'João', 'Maria', 'Zé' ],
    selectedEmployesList: [],
    chartData: null,
    chart: null
  }),
  // async beforeMount () {
  //   this.chartData = await this.$_axios.patch(`${this.$_url}appointment`)
  //   this.chart = await this.chartData.data
  // },
  methods: {
    async submit () {
      this.chartFilter.initialQueryDate = new Date(this.initialQueryDate)
      this.chartFilter.finalQueryDate = new Date(this.finalQueryDate)
      this.chartFilter.employeeNameList = this.employeeNameList
      this.chartData = await this.$_axios.patch(`${this.$_url}chartfilter`, this.chartFilter)
      this.chart = await this.chartData.data
      console.log(this.chart)
    },
    selectedEmployes (employee) {
      console.log(employee.indexOf('todos') > -1)
      if (employee.indexOf('todos') > -1) {
        this.selectedEmployesList = []
        console.log(this.selectedEmployesList)
        this.selectedEmployesList.push(...this.employees)
      }
      console.log(this.selectedEmployesList)
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
    }
  },
  computed: {
    chartFinal () {
      return this.chart
    }
  }
}
</script>
