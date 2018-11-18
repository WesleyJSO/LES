<template>
  <div>

    <v-item-group>
      <v-container grid-list-md>
        <v-layout wrap>

          <v-flex md3>
            <v-hover>
              <v-card v-ripple="true"
                color="#86BBD8"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="headline mb-0">Funcionários</h1>
                    <h3 class="text-xs-center headline ma-0">8</h3>
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
                color="#15B097"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="headline mb-0">Horas extras</h1>
                    <h3 class="text-xs-center headline mb-0">160</h3>
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
                color="#FFEA4F"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="headline mb-0">Saldo negativo</h1>
                    <h3 class="text-xs-center headline mb-0">81</h3>
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
                color="#A33F3F"
                slot-scope="{ hover }"
                :class="`elevation-${hover ? 12 : 2}`"
              >
                <v-card-title primary-title>
                  <div>
                    <h1 class="headline mb-0">Saldo no limite</h1>
                    <h3 class="text-xs-center headline mb-0">12</h3>
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
      :items="employees"
      class="elevation-2"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.employee }}</td>
        <td class="text-xs-center">{{ props.item.workedHoursOnTheParameterPeriod }}</td>
        <td class="text-xs-center">{{ props.item.balance }}</td>
        <td class="text-xs-center">{{ props.item.lastMonth }}</td>
        <td class="text-xs-center">{{ props.item.hoursInLastMonth }}</td>
        <td class="text-xs-center">{{ props.item.workedHourComercial }}</td>
        <td class="text-xs-center">{{ props.item.workedHourNight }}</td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  data: () => ({
    headers: [
      { text: 'Funcionários', align: 'left', sortable: true, value: 'employee' },
      { text: 'Total horas trabalhadas', align: 'center', sortable: true, value: 'workedHoursOnTheParameterPeriod' },
      { text: 'Saldo total', align: 'center', sortable: true, value: 'balance' },
      { text: 'Ultimo mês em débito', align: 'center', sortable: true, value: 'lastMonth' },
      { text: 'Horas do ultimo mês em débito', align: 'center', sortable: true, value: 'hoursInLastMonth' },
      { text: 'Horas pediodo comercial', align: 'center', sortable: true, value: 'workedHourComercial' },
      { text: 'Horas trabalhadas noite', align: 'center', sortable: true, value: 'workedHourNight' }
    ],
    employees: [
      {
        value: false,
        employee: 'Frozen Yogurt',
        workedHoursOnTheParameterPeriod: 159,
        balance: 6.0,
        lastMonth: 'janeiro',
        hoursInLastMonth: 4.0,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Ice cream sandwich',
        workedHoursOnTheParameterPeriod: 237,
        balance: 9.0,
        lastMonth: 'março',
        hoursInLastMonth: 4.3,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Eclair',
        workedHoursOnTheParameterPeriod: 262,
        balance: 16.0,
        lastMonth: 'janeiro',
        hoursInLastMonth: 6.0,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Cupcake',
        workedHoursOnTheParameterPeriod: 305,
        balance: 3.7,
        lastMonth: 'abril',
        hoursInLastMonth: 4.3,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Gingerbread',
        workedHoursOnTheParameterPeriod: 356,
        balance: 16.0,
        lastMonth: 'dezembro',
        hoursInLastMonth: 3.9,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Jelly bean',
        workedHoursOnTheParameterPeriod: 375,
        balance: 0.0,
        lastMonth: 'julho',
        hoursInLastMonth: 0.0,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Lollipop',
        workedHoursOnTheParameterPeriod: 392,
        balance: 0.2,
        lastMonth: 'julho',
        hoursInLastMonth: 0,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Honeycomb',
        workedHoursOnTheParameterPeriod: 408,
        balance: 3.2,
        lastMonth: 'julho',
        hoursInLastMonth: 6.5,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'Donut',
        workedHoursOnTheParameterPeriod: 452,
        balance: 25.0,
        lastMonth: 'agosto',
        hoursInLastMonth: 4.9,
        workedHourComercial: 12,
        workedHourNight: '5'
      },
      {
        value: false,
        employee: 'KitKat',
        workedHoursOnTheParameterPeriod: 518,
        balance: 26.0,
        lastMonth: 'fevereiro',
        hoursInLastMonth: 7,
        workedHourComercial: 12,
        workedHourNight: '5'
      }
    ]
  }),
  async beforeMount () {
    console.log('before mount trigger')
    let response = await this.$_axios.patch(`${this.$_url}dashboard`, {})
    console.log({response})
  }
}
</script>


