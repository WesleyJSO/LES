<template>
  <div>
    <h1>Apontamentos</h1>
    <br/>

    <v-form>
      <v-card>
        <v-flex xs12 sm6 d-flex>
          <v-select
            v-model="employeeName"
            :items="employeesNames"
            prepend-icon="person"
            label="Colaborador"
            :item-value="employeeName"
            @change="setEmployee">
          </v-select>
        </v-flex>
        <v-spacer></v-spacer>

      </v-card>
      <br>
      <MonthAppointments :editable="false" :employee="employee"></MonthAppointments>
    </v-form>
  </div>
</template>

<script>
import MonthAppointments from '@/components/shared/MonthAppointments.vue'
import Authenticator from '../../service/Authenticator'

export default {
  data: () => ({
    employeeName: '',
    employeesNames: [],
    employees: [],
    employee: null
  }),
  components: {
    MonthAppointments
  },
  beforeMount () {
    var user = {
      id: Authenticator.GET_AUTHENTICATED().id
    }
    this.callApi({manager: {user: user}})
  },
  watch: {
    employees () {
      this.employeesNames = []
      this.employees.forEach(element => {
        this.employeesNames.push(element.name + ' ' + element.lastName)
      })
    }
  },
  methods: {
    setEmployee () {
      let name = this.employeeName.split(' ')
      this.employees.forEach(element => {
        if (element.name === name[0] && element.lastName === name[1]) {
          this.employee = {id: element.id}
        }
      })
    },
    async callApi (employee) {
      var response = null
      var result = null
      try {
        response = await this.$_axios.patch(`${this.$_url}employee`, employee)
        result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          this.employees = result.resultList
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
