<template>
  <div>
    <h1>Apontamento de Horas</h1>
    <br/>

    <v-form>
      <MonthAppointments :editable="true" :employeeId="employeeId"></MonthAppointments>
    </v-form>
  </div>
</template>

<script>
import MonthAppointments from '@/components/shared/MonthAppointments.vue'
import Authenticator from '../../service/Authenticator'

export default {
  data: () => ({
    employeeId: ''
  }),
  components: {
    MonthAppointments
  },
  beforeMount () {
    this.employeeId = Authenticator.GET_AUTHENTICATED().id
  }
}
</script>