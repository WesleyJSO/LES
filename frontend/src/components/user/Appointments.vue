<template>
  <div>
    <h1>Apontamento de Horas</h1>
    <br/>

    <v-form>
      <MonthAppointments :editable="true" :employee="employee"></MonthAppointments>
    </v-form>
  </div>
</template>

<script>
import MonthAppointments from '@/components/shared/MonthAppointments.vue'
import Authenticator from '../../service/Authenticator'

export default {
  data: () => ({
    employee: {}
  }),
  components: {
    MonthAppointments
  },
  beforeMount () {
    var emp = {user: {id: Authenticator.GET_AUTHENTICATED().id}}
    this.employee = emp
  }
}
</script>