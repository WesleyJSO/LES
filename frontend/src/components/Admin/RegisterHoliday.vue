<template>
  <div id="app">
    <v-layout>
      <v-card class="elevation-10">
        <div id="calendar">
          <Calendar ref="calendar" :markers="markers" :disabledFutureDay="disabledFutureDay" 
          :hideOtherMonthDay="hideOtherMonthDay"  @month="handleMonthChange($event)" @day="handleDayChange($event)" >
          </Calendar>
        </div>
        <br/>
        <v-form>
          <v-layout  class="layout-text-field">
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                label="Data"
                prepend-icon="event"
                required
                type="date" 
                v-model="holiday.date"
                @change="handleDateChange()"
                persistent-hint readonly
                >
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout class="layout-text-field">
            <v-flex xs12 sm9 md14 lg14 xl8>
              <v-text-field 
                type="text"
                label="Descrição Feriado"
                v-model="holiday.name"
                prepend-icon="assignment"
              ></v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout v-if="admin">
            <v-flex 
              class="text-xs-left">
              <v-btn small
                :disabled="this.holiday.id === null"
                color="error"
                @click="deleteHoliday">
                Deletar Feriado
              </v-btn>
            </v-flex>
            <v-flex
              class="text-xs-right">
              <v-btn
                :disabled="holiday.name === ''"
                color="success"
                @click="saveHoliday">
                Salvar Feriado
              </v-btn>
            </v-flex>
          </v-layout>
        </v-form>
      </v-card>
    </v-layout>
  </div>
</template>

<script>
import Calendar from '@/components/shared/Calendar.vue'
import Authenticator from '../../service/Authenticator'
import DateHelper from '@/helpers/DateHelper'

export default {
  name: 'app',
  components: {
    Calendar,
    Authenticator,
    DateHelper
  },
  data () {
    return {
      holiday: {
        id: null,
        name: '',
        date: '',
        type: 'Cadastrado'
      },
      holidays: [],
      admin: false,
      markers: [],
      disabledFutureDay: false,
      hideOtherMonthDay: false
    }
  },
  watch: {
    holidays () {
      this.holidays.forEach(element => {
        this.markers.push(
          {
            date: DateHelper.formatISOOnlyDate(element.date),
            className: 'feriado'
          }
        )
      })
    }
  },
  beforeMount () {
    this.admin = Authenticator.HAS_ROLE('ROLE_ADMIN')
    this.callApi()
  },
  methods: {
    async saveHoliday () {
      this.holiday.date = DateHelper.formatShortDate(this.holiday.date)
      console.log('save')
      var response = null
      var result = null
      try {
        if (this.holiday.id === null) {
          response = await this.$_axios.post(`${this.$_url}holiday`, this.holiday)
          result = response.data
          this.haveMessage = false
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
        } else {
          response = await this.$_axios.put(`${this.$_url}holiday`, this.holiday)
          result = response.data
          this.haveMessage = false
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
        }
        this.callApi()
      } catch (error) {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    handleDayChange (date) {
      if (this.holidays.length > 0) {
        this.holiday.name = this.getHolidayDescription(date)
      }
      this.holiday.date = date
    },
    handleMonthChange (date) {
      this.holiday.date = date
    },
    handleDateChange () {
      this.$refs.calendar.chooseSpecifiedDate(this.holiday.date)
    },
    switchToPrevMonth () {
      this.$refs.calendar.switchToPrevMonth()
    },
    switchToNextMonth () {
      this.$refs.calendar.switchToNextMonth()
    },
    getHolidayDescription (date) {
      this.holiday = {
        id: null,
        name: '',
        date: '',
        type: 'Cadastrado'
      }
      let description = ''
      this.holidays.forEach(element => {
        if (element.date === DateHelper.formatShortDate(date)) {
          description = element.name
          this.holiday = Object.assign(this.holiday, element)
        }
      })
      return description
    },
    async callApi () {
      this.holidays = []
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}holiday`, {active: true})
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          this.holidays = result.resultList
        }
        if (result.mensagem) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
          // retorno mensagem de sucesso /
            this.messageColor = 'info'
          } else {
            // retorno mensagem de erro /
            this.messageColor = 'warning'
          }
        }
      } catch (error) {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    },
    async deleteHoliday () {
      console.log('delete')
      console.log(this.holiday.id)
      var response = null
      var result = null
      try {
        if (this.holiday.id !== null) {
          response = await this.$_axios.delete(`${this.$_url}holiday`, {params: {id: this.holiday.id, clazzName: 'Holiday'}})
          result = response.data
          this.haveMessage = false
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
          this.callApi()
        }
      } catch (error) {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }
    }
  }
}
</script>

<style>
* {
  box-sizing: border-box;
}
ul {
  padding-left: 0;
}
li {
  list-style: none;
}
body {
  background-color: #efefef;
}
#app {
  max-width: 414px;
  margin: auto;
}
#calendar {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#calendar .feriado span {
  color: #fff;
  background-color: #fa1212;
}

.control-header {
  text-align: center;
}

.options {
  padding: 10px;
}
.options li {
  margin-bottom: 10px;
}
.current-date {
  text-align: center;
  color: #232323;
}
.layout-text-field {
  margin-left: 3%;
}
</style>
