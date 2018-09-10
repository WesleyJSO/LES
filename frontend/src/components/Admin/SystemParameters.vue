<template>
    <v-app>
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
                <td>{{ props.item.parameterName }}</td>
                <td class="text-xs-left">{{ props.item.value }}</td>            
              </tr>
            </template>
          </v-data-table>
          <v-flex class="text-xs-right">
            <v-card class="elevation-10">
              <v-btn
                    color="success"
                    router-link to="CadastroParametros">
                    Alterar Parametros
              </v-btn>
            </v-card>
          </v-flex>
        </v-flex>
      </v-layout>
    </v-form>
  </v-app>
</template>

<script>
import Parameters from '../../objects/Parameters'

export default {
  data: () => ({
    parameter: null,
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
    parameters: [
      {
        parameterName: 'Adicional de Hora Extra',
        value: '50%'
      },
      {
        parameterName: 'Adicional Noturno',
        value: '20%'
      },
      {
        parameterName: 'Adicional de Domingo e Feriado',
        value: '100%'
      },
      {
        parameterName: 'Prazo Limite para Apontamento Retroativo',
        value: '1 hora'
      },
      {
        parameterName: 'Prazo Limite para Solicitação de Remanejamento',
        value: '12 horas'
      },
      {
        parameterName: 'Prazo Limite Compensação do Banco de Horas',
        value: '6 meses'
      },
      {
        parameterName: 'Tipo de Hora',
        value: 'Hora Extra'
      },
      {
        parameterName: 'Limite Diário de Horas',
        value: '3 horas'
      }
    ],
    message: '',
    haveMessage: true
  }),
  created () {
    let parameter =
      new Parameters(
        1,
        50,
        20,
        100,
        1,
        12,
        6,
        'Hora Extra',
        3,
        0,
        0
      )
    this.parameter = parameter
  },
  computed: {
  },
  watch: {
  },
  methods: {
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
