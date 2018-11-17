<template>
  <div id="app">
    <v-layout>
      <v-card class="elevation-10">
        <div id="calendar">
          <Calendar ref="calendar" :markers="markers" :disabledFutureDay="disabledFutureDay" 
          :hideOtherMonthDay="hideOtherMonthDay"  @month="handleMonthChange($event)" @day="handleDayChange($event)" >
          </Calendar>
        </div>
        <v-form>
          <v-layout>
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                label="Data"
                prepend-icon="event"
                required
                type="date" 
                v-model="currentDate"
                @change="handleDateChange()"
                persistent-hint readonly
                >
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout>
            <v-flex xs12 sm9 md14 lg14 xl4>
              <v-text-field 
                type="text"
                label="Descrição Feriado"
                prepend-icon="assignment"
                clearable=""
              ></v-text-field>
            </v-flex>
          </v-layout>
          <v-layout v-if="admin">
            <v-flex 
              class="text-xs-left">
              <v-btn small
                color="error"
                @click="saveHoliday">
                Deletar Feriado
              </v-btn>
            </v-flex>
            <v-flex
              class="text-xs-right">
              <v-btn
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

export default {
  name: 'app',
  components: {
    Calendar,
    Authenticator
  },
  data () {
    return {
      holidays: [],
      admin: false,
      markers: [],
      disabledFutureDay: false,
      hideOtherMonthDay: false,
      currentDate: ''
    }
  },
  watch: {
    holidays () {
      this.holidays.forEach(element => {
        this.markers.push(
          {
            id: element.id,
            date: element.date,
            className: 'feriado',
            name: element.name
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
    saveHoliday () {
      console.log('save')
    },
    handleDayChange (date) {
      this.currentDate = date
    },
    handleMonthChange (date) {
      this.currentDate = date
    },
    handleDateChange () {
      this.$refs.calendar.chooseSpecifiedDate(this.currentDate)
    },
    switchToPrevMonth () {
      this.$refs.calendar.switchToPrevMonth()
    },
    switchToNextMonth () {
      this.$refs.calendar.switchToNextMonth()
    },
    async callApi () {
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}holiday`, {active: true})
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          console.log(JSON.stringify(result.resultList))
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
#calendar /deep/ .feriado span {
  color: #fff;
  background-color: #ff4081;
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
</style>
