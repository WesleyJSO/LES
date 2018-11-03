<template>
    <div>
    <h1>Parametros do Sistema</h1>
    <v-alert color="success"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    <br/>
    <v-form>
      <v-layout>
      <!-- Row 1 -->
        <v-flex center >
          <v-data-table :headers="headers"
                    :items="parameters"
                    item-key="id"
                    hide-actions
                    class="elevation-10" >
            <template slot="items" slot-scope="props">
              <tr>
                <td v-if="props.item.show">{{ props.item.parameterName }}</td>
                <td class="text-xs-left" v-if="props.item.show">{{ props.item.value }}</td>
              </tr>
            </template>
          </v-data-table>
          <v-flex class="text-xs-right">
            <v-card class="elevation-10">
              <v-btn
                    color="success"
                    router-link :to="{ name: 'RegisterSystemParameters', params: { parameter }}">
                    Alterar Parametros
              </v-btn>
            </v-card>
          </v-flex>
        </v-flex>
      </v-layout>
    </v-form>
  </div>
</template>

<script>
// import Parameters from '../../objects/Parameters'

export default {
  data: () => ({
    parameter: {},
    headers: [
      {
        text: 'Parametro',
        align: 'left',
        sortable: false,
        value: 'parameterName'
      },
      { text: 'Valor',
        sortable: false,
        value: 'value' }
    ],
    parameters: [],
    message: '',
    haveMessage: false
  }),
  created () {
    this.$vuetify.goTo(0)
    this.callApi()
  },
  computed: {
  },
  watch: {
  },
  methods: {
    callApi () {
      this.$_axios.patch(`${this.$_url}parameter`, this.parameter).then(response => {
        var result = response.data
        if (result.resultList.length !== 0) {
          // retorno ok /
          this.parameter = result.resultList[0]
          this.parameterId = this.parameter.id
          this.parameters = [
            {
              parameterName: 'Adicional de Hora Extra',
              value: this.parameter.overtimePercentage + '%',
              show: true
            },
            {
              parameterName: 'Adicional Noturno',
              value: this.parameter.nightOvertimePercentage + '%',
              show: true
            },
            {
              parameterName: 'Adicional de Domingo e Feriado',
              value: this.parameter.weekEndOvertimePercentage + '%',
              show: true
            },
            {
              parameterName: 'Prazo Limite para Apontamento Retroativo',
              value: this.parameter.retroactiveAppointmentLimitTime + ' hora(s)',
              show: true
            },
            {
              parameterName: 'Prazo Limite para Solicitação de Remanejamento',
              value: this.parameter.relocationRequestLimitTime + ' hora(s)',
              show: true
            },
            {
              parameterName: 'Prazo Limite Compensação do Banco de Horas',
              value: this.parameter.bankCompensationLimitTime + ' meses',
              show: true
            },
            {
              parameterName: 'Tipo de Hora',
              value: this.parameter.hourType,
              show: true
            },
            {
              parameterName: 'Primeiro Tipo Aplicado',
              value: this.parameter.firstTypeApplied,
              show: false
            },
            {
              parameterName: 'Limite Diário de Horas Extras',
              value: this.parameter.overtimeTypeLimit + ' hora(s)',
              show: false
            },
            {
              parameterName: 'Limite Diário de Horas no Banco',
              value: this.parameter.bankedHourTypeLimit + ' hora(s)',
              show: false
            }
          ]
          if (this.parameter.hourType === 'Hora Extra e Banco de Horas') {
            this.parameters[7].show = true
          }
          if (this.parameter.hourType === 'Hora Extra' || this.parameter.hourType === 'Hora Extra e Banco de Horas') {
            this.parameters[8].show = true
            if (!this.parameter.overtimeTypeLimit) {
              this.parameters[8].value = 'N/A'
            }
          }
          if (this.parameter.hourType === 'Banco de Horas' || this.parameter.hourType === 'Hora Extra e Banco de Horas') {
            this.parameters[9].show = true
            if (!this.parameter.bankedHourTypeLimit) {
              this.parameters[9].value = 'N/A'
            }
          }
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
      }).catch(error => {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    }
  }
}
</script>

<style scoped>
  h1 {
    font-size: 40px;
    text-align: center;
    margin-top: -20px;
    margin-bottom: 20px;
  }
</style>
