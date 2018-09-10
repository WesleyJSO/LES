<template>
    <v-container fluid>
      <v-layout wrap column py-5 my-5>

        <v-layout >
          <v-combobox
            label="Selecione os funcionários"
            v-model="selectedEmployesList"
            :items="employees"
            hide-selected
            multiple
            persistent-hint
            small-chips
            @change="selectedEmployes"
            chips>
            <template slot="selection" slot-scope="data">
              <v-chip
                multiple="true"
                :selected="data.selected"
                :disabled="data.disabled"
                :key="JSON.stringify(data.item)"
                class="v-chip--select-multi "
                @input="data.parent.selectItem(data.item)"
              >
                <v-avatar class="accent white--text">
                  {{ data.item.slice(0, 1).toUpperCase() }}
                </v-avatar>
                {{ data.item }}
              </v-chip>
            </template>
          </v-combobox>
        </v-layout>

        <v-layout py-5 my-5>
          <v-combobox
            label="Selecione a periocidade"
            :items="frequency"
            @change="changeFrequency">
          </v-combobox>
        </v-layout>

        <v-flex xs12>
          <v-tabs dark slider-color="yellow" >
            <v-tab v-for="tabName in tabs" :key="tabName" ripple>
              {{ tabName }}
            </v-tab>

            <v-tab-item>
              <v-form flat>
                <v-form-text>
                  <line-chart ref="chartObject1" :download="true"
                    :stacked="false"
                    title="Horas Trabalhadas"
                    xtitle="Dias"
                    ytitle="Horas"
                    discrete="true"
                    height="400px"
                    :data="chart"/>
                </v-form-text>
              </v-form>
            </v-tab-item>

            <v-tab-item>
              <v-form flat>
                <v-form-text>
                  <column-chart ref="chartObject2" :download="true"
                    :stacked="false"
                    title="Horas de Almoço"
                    xtitle="Dias"
                    ytitle="Horas"
                    discrete="true"
                    height="400px"
                    :data="chartFinal">
                  </column-chart>
                </v-form-text>
              </v-form>
            </v-tab-item>

            <v-tab-item>
              <v-form flat>
                <v-form-text>
                 <bar-chart ref="chartObject3" :download="true"
                    :stacked="false"
                    title="Horas Extras"
                    xtitle="Horas"
                    ytitle="Dias"
                    discrete="true"
                    height="400px"
                    :data="chartFinal">
                 </bar-chart>
                </v-form-text>
              </v-form>
            </v-tab-item>

          </v-tabs>
        </v-flex>

      </v-layout>

  </v-container>
</template>


<script>
import axios from 'axios'
import DateHelper from '../../helpers/DateHelper.js'
export default {
  data: () => ({
    tabs: ['Horas Trabalhadas', 'Horas de Almoço', 'Horas Extras'],
    frequency: [
      'Semana',
      'Mês',
      'Ano'
    ],
    employees: [
      'Wesley',
      'Zeller',
      'Bruno',
      'João',
      'Maria',
      'Zé',
      'todos'
    ],
    selectedEmployesList: [],
    chartData: null,
    chart: null
  }),
  async beforeMount () {
    this.chartData = await axios.get('http://localhost:3000/Relatorios')
    this.chart = await this.chartData.data
  },
  methods: {
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
      console.log(this.$children[2])
      let today = new Date()
      switch (frequency) {
        case 'Semana':
          for (let index in this.chart) {
            for (let date in this.chart[index].data) {
              // let name = chart[index].name
              // let hours = chart[index].data[date]
              if (DateHelper.diffDates(new Date(date), today) > 3) {
                console.log(`Item removed: ${this.chart[index].data[date]}`)
                delete this.chart[index].data[date]
              }
            }
          }
          this.$refs.chartObject1.updateChart()
          this.$refs.chartObject2.updateChart()
          this.$refs.chartObject3.updateChart()
      }
    }
  },
  computed: {
    chartFinal () {
      console.log(`Computed: ${JSON.stringify(this.chart)}`)
      return this.chart
    }
  }
}
</script>
