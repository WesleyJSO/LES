<template>
  <div id="calendar">
    <div class="calendar-header">
        <slot name="month-switch" v-if="$slots['month-switch']"></slot>
        <div class="month-switch" v-else>
        <span class="prev" @click="handleMonthSwitch('prev')"></span>
        <span> {{current.year}}-{{current.month}} </span>
        <span class="next" @click="handleMonthSwitch('next')"></span>
      </div>
    
    </div>
    <div class="calendar-content">
      <ul class="week">
        <li v-for=" (day,index) in weekText" :key="index">{{day}}</li>
      </ul>
      <ul class="month">
        <li v-for="(item,index) in days" :key="index" @click="handleDayChoose(item,index)" :class="[dayClasses(item,index),item.className]">
          <span>{{item.day}}</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>

import CalendarHelper from '../../helpers/CalendarHelper'
export default {
  props: {
    markers: Array,
    weekText: {
      type: Array,
      default: () => ['D', 'S', 'T', 'Q', 'Q', 'S', 'S']
    },
    disabledFutureDay: {
      type: Boolean,
      default: false
    },
    hideOtherMonthDay: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      days: [],
      current: {
        date: null,
        year: new Date().getFullYear(),
        month: new Date().getMonth() + 1,
        day: new Date().getDate()
      }
    }
  },
  watch: {
    'current.month' () {
      this.initCalendar()
    },
    markers () {
      this.initCalendar()
      this.current.date = CalendarHelper.splicingDate(this.current)
      this.$emit('day', CalendarHelper.splicingDate(this.current))
    }
  },
  created () {
  },
  methods: {
    initCalendar () {
      this.days = CalendarHelper.initCalendar(this.current, this.markers)
    },
    dayClasses (item) {
      return {
        'choose-day': item.date === this.current.date,
        'disabled-day': this.disabledFutureDay && item.isFutureDay,
        'other-month-day': item.isOtherMonthDay,
        'other-month-day--hide': this.hideOtherMonthDay && item.isOtherMonthDay
      }
    },

    /**
     * @description switch month
     * @param {String} type prev or next
     */
    handleMonthSwitch (type) {
      // prev month
      let { year, month } = this.current
      if (type === 'prev') {
        if (month > 1) {
          month--
        } else {
          year--
          month = 12
        }
      }
      // next month
      if (type === 'next') {
        if (month < 12) {
          month++
        } else {
          year++
          month = 1
        }
      }
      const day = new Date().getDate()
      const switchAfterMonthTotalDays = CalendarHelper.getTotalDays(year, month)
      this.current.year = year
      this.current.month = month
      // avoid cross-border
      if (day > switchAfterMonthTotalDays) {
        this.current.day = switchAfterMonthTotalDays
      }

      this.current.date = CalendarHelper.splicingDate(this.current)
      this.$emit('month', CalendarHelper.splicingDate(this.current))
    },
    /**
     * @description choose one day
     * @param {Object} item
     */
    handleDayChoose (item) {
      if (
        (this.disabledFutureDay && item.isFutureDay) ||
        (this.hideOtherMonthDay && item.isOtherMonthDay)
      ) {
        return
      }

      this.current.day = item.day
      this.current.date = item.date
      this.$emit('day', CalendarHelper.splicingDate(this.current))

      item.isOtherMonthDay &&
        (item.day > 7
          ? this.handleMonthSwitch('prev')
          : this.handleMonthSwitch('next'))
    },
    // External method
    switchToPrevMonth () {
      this.handleMonthSwitch('prev')
    },
    switchToNextMonth () {
      this.handleMonthSwitch('next')
    },
    chooseSpecifiedDate (date) {
      var message = 'Missing required parameters'
      if (!date) throw message

      const [year, month, day] = date.split('-') || date.split('/')
      const { year: currentYear, month: currentMonth } = this.current

      this.current.day = day
      this.current.date = date
      // not init this month
      if (year !== currentYear && month !== currentMonth) {
        this.current.year = year
        this.current.month = month
        this.initCalendar()
      }
    }
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
  -webkit-tap-highlight-color: transparent;
}
ul {
  margin: 0;
  padding-left: 0;
}
li {
  list-style: none;
}

#calendar {
  min-width: 320px;
  color: #fff;
}
.calendar-header {
  position: relative;
}

.month-switch {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 40px;
  padding: 0 20px;
  background-color: #232323;
}
.month-switch .prev,
.month-switch .next {
  width: 12px;
  height: 12px;
  border-top: 1px solid #fff;
  border-right: 1px solid #fff;
  cursor: pointer;
}

.month-switch .prev {
  transform: rotate(-135deg);
}

.month-switch .next {
  transform: rotate(45deg);
}
.calendar-content {
  color: #232323;
  background-color: rgb(238, 237, 237);
}
.week {
  display: flex;
  padding: 10px 0;
}
.week li {
  flex: 1;
  text-align: center;
  cursor: pointer;
}
.month {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 0;
}
.month li {
  width: 14.28%;
  margin-bottom: 10px;
}
.month span {
  display: inline-block;
  width: 40px;
  height: 40px;
  line-height: 40px;
  border-radius: 50%;
  text-align: center;
  cursor: pointer;
}

#calendar .choose-day span {
  color: #fff;
  background-color: #232323;
}

#calendar .other-month-day span {
  color: #ccc;
}
#calendar .other-month-day.other-month-day--hide span {
  color: transparent;
  background-color: transparent;
}
#calendar .disabled-day span {
  color: #ccc;
  background-color: transparent;
}
</style>

