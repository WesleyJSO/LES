<template>
  <div id="app">
    <div id="calendar">
      <Calendar ref="calendar" :markers="markers" :disabledFutureDay="disabledFutureDay" 
      :hideOtherMonthDay="hideOtherMonthDay"  @month="handleMonthChange($event)" @day="handleDayChange($event)" >
      </Calendar>
    </div>
    <div class="control">
      <section>
          <h3>Change date</h3>
      <input type="date" v-model="currentDate" @change="handleDateChange">
      </section>
    <section>
      <h3>Change props</h3>
   <ul class="options">
      <li>
        <label for="disabledFutureDay">Disabled future days</label>
        <input type="checkbox" id="disabledFutureDay" v-model="disabledFutureDay">
      </li>
      <li>
        <label for="hideOtherMonthDay">Hie other month days</label>
        <input type="checkbox" id="hideOtherMonthDay" v-model="hideOtherMonthDay">
      </li>
    </ul>
    </section>
    </div>
  </div>
</template>

<script>
import Calendar from '@/components/shared/Calendar.vue'

export default {
  name: 'app',
  components: {
    Calendar
  },
  data () {
    return {
      markers: [
        {
          date: '2018-10-20',
          className: 'love'
        },
        {
          date: '2018-10-30',
          className: 'miss'
        },
        {
          date: '2018-10-01',
          className: 'dream'
        }
      ],
      disabledFutureDay: false,
      hideOtherMonthDay: false,
      currentDate: ''
    }
  },
  methods: {
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
#calendar /deep/ .love span {
  color: #fff;
  background-color: #7b1fa2;
}
#calendar /deep/ .miss span {
  color: #fff;
  background-color: #ff4081;
}

#calendar /deep/ .dream span {
  color: #fff;
  background-color: #448aff;
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
