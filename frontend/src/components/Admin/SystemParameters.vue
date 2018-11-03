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
                <td v-if="!props.item.title">{{ props.item.parameterName }}</td>                
                <td class="title" v-if="props.item.title">{{ props.item.parameterName }}</td>
                <td class="text-xs-left">{{ props.item.value }}</td>
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
    noLimit: 'Sem limite de horas',
    notRegistered: 'N/A',
    message: '',
    haveMessage: false
  }),
  created () {
    this.$vuetify.goTo(0)
    this.parameters = [
      {
        parameterName: 'Adicional de Hora Extra',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Adicional Noturno',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Adicional de Domingo e Feriado',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Prazo Limite para Apontamento Retroativo',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Prazo Limite para Solicitação de Remanejamento',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Prazo Limite Compensação do Banco de Horas',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Horas Extras',
        value: null,
        title: true
      },
      {
        parameterName: 'Limite diário',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Banco de Horas',
        value: null,
        title: true
      },
      {
        parameterName: 'Limite diário',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Horas Extras e Banco de Horas',
        value: null,
        title: true
      },
      {
        parameterName: 'Limite diário de Horas Extras',
        value: this.notRegistered,
        title: false
      },
      {
        parameterName: 'Limite diário de Banco de Horas',
        value: this.notRegistered,
        title: false
      }
    ]
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
          this.parameters[0].value = this.parameter.overtimePercentage + '%'
          this.parameters[1].value = this.parameter.nightOvertimePercentage + '%'
          this.parameters[2].value = this.parameter.weekEndOvertimePercentage + '%'
          this.parameters[3].value = this.parameter.retroactiveAppointmentLimitTime.substring(0, 5) + ' hora(s)'
          this.parameters[4].value = this.parameter.relocationRequestLimitTime + ' hora(s)'
          this.parameters[5].value = this.parameter.bankCompensationLimitTime + ' meses'
          this.parameters[7].value = this.parameter.overTime.quantityFirst ? this.parameter.overTime.quantityFirst + ' hora(s)' : this.noLimit
          this.parameters[9].value = this.parameter.compTime.quantityFirst ? this.parameter.compTime.quantityFirst + ' hora(s)' : this.noLimit
          if (this.parameter.bothTypes.first === 'Hora Extra') {
            this.parameters[11].value = this.parameter.bothTypes.quantityFirst ? this.parameter.bothTypes.quantityFirst + ' hora(s)' : this.noLimit
            this.parameters[12].value = this.parameter.bothTypes.quantitySecond ? this.parameter.bothTypes.quantitySecond + ' hora(s)' : this.noLimit
          } else {
            this.parameters[12].value = this.parameter.bothTypes.quantityFirst ? this.parameter.bothTypes.quantityFirst + ' hora(s)' : this.noLimit
            this.parameters[11].value = this.parameter.bothTypes.quantitySecond ? this.parameter.bothTypes.quantitySecond + ' hora(s)' : this.noLimit
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
  .title {
    font-weight: bold;
  }
</style>
