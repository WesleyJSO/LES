<template>
    <v-app>
        <v-container fluid>
            <v-flex xs12 sm12>
                <v-card>
                    <v-flex xs12>
                    <v-card-text>
                    <h2 class="grey--text">Calendário</h2>
                    <v-container fluid>
                        <v-calendar :attributes="calendarAttributes"
                                    is-expanded>
                        </v-calendar>
                    </v-container>
                    </v-card-text>
                    </v-flex>
                    <v-divider light></v-divider>
                    <v-card-text>
                        <v-data-table :headers="getTableHeaders"
                                      :items="parsedData"
                                      hide-actions
                                      class="elevation-1">
                            <template slot="items" slot-scope="props">
                                <td class="text-xs-center">{{ props.item.amountHours }}</td>
                                <td class="text-xs-center">{{ props.item.amountDays }}</td>
                                <td class="text-xs-center">{{ props.item.vacation }}</td>
                                <td class="text-xs-center">{{ props.item.amountMoney }}</td>
                                </template>
                            </v-data-table>
                    </v-card-text>
                </v-card>
            </v-flex>
        </v-container>
    </v-app>
</template>
<script>
import AdminService from '@/service/AdminService'
export default {
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  data: () => ({
    attrs: [
      {
        key: 'today',
        highlight: {
          backgroundColor: '#0000ff'
          // Other properties are available too, like `height` & `borderRadius`
        },
        dates: [
          { start: new Date().setDate(10), span: 5 } // Span is number of days
        ],
        popover: {
          label: 'Dias de Banco de Horas'
        },
        locale: 'pt-BR',
        navVisibility: 'focus'
        // dot: { backgroundColor: 'red' }
      }
    ]
  }),
  created () {
    // Fetch data from rest service
  },
  computed: {
    getTableHeaders () {
      return AdminService.MANAGE_HOURS_TABLE_HEADERS
    },
    parsedData () {
      let item = [{
        amountHours: 80,
        amountDays: 10,
        vacation: '23/10/2018',
        amountMoney: 80 * 13
      }]
      /* if (this.item) {
        let data = {
          amountHours: this.item.amountHours,
          amountDays: parseInt(this.item.amountHours) / 8,
          vacation: this.item.nextVacation,
          amountMoney: 2000 * parseInt(this.item.amountHours)
        }
        item.push(data)
      } */
      return item
    },
    userDates () {
      return [
        {
          start: new Date(),
          span: 10
        }
      ]
    },
    calendarAttributes () {
      return [
        {
          key: 'today',
          highlight: { backgroundColor: '#ff8080' },
          dates: this.userDates,
          popover: {
            label: 'Dias de Banco de Horas'
          },
          locale: 'pt-BR',
          navVisibility: 'focus'
        // dot: { backgroundColor: 'red' }
        }
      ]
    }
  }
}
</script>
<style scoped>
</style>

