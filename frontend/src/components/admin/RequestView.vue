<template>
  <v-app>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="white">
      <v-toolbar-title>Solicitações</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field v-model="searchFilter"
                    append-icon="search"
                    label="Search"
                    single-line
                    hide-details>
      </v-text-field>
    </v-toolbar>
    <template>
        <v-tabs fixed-tabs
                color="black"
                dark
                v-model="active"
                class="elevation-10">
            <v-tab>Pendentes</v-tab>
            <v-tab>Avaliados</v-tab>
    <v-tabs-items v-model="tabs">
      <v-tab-item :key="1">
        <v-data-table :headers="headers"
                  :items="requests"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-10" >
        <template slot="items" slot-scope="props">
          <tr @onclick="props.expanded = !props.expanded">
            <td>{{ props.item.employeeName }}</td>
            <td class="text-xs-left">{{ props.item.type }}</td>
            <td class="text-xs-left">{{ props.item.status }}</td>
            <td class="text-xs-center">
                <v-dialog v-model="dialog" max-width="600px" max-height="300px">
                <v-icon
                    slot="activator"
                    :key="props.item.index"
                    light>
                    edit
                </v-icon>

                <v-card>
                    <v-card-title
                    class="headline grey lighten-2"
                    primary-title
                    >
                    {{ props.item.type }}
                    </v-card-title>

                    <v-card-text>
                        {{ props.item.employeeName }}
                    </v-card-text>

                    <v-card-text>
                        Descrição:<br>
                        {{ props.item.description }}
                    </v-card-text>

                    <v-card-text v-if="props.item.type === 'Ausência'">
                        Data Afetada - 
                        {{ props.item.affectedDate}}<br><br>

                        Documento: 
                        <a href="">Download</a>

                    </v-card-text>

                    <v-card-text v-if="props.item.type === 'Correção de Apontamento'">
                        Data Afetada - 
                        {{ props.item.affectedDate}}<br><br>

                        Nova Data - 
                        {{ props.item.newDate}}

                    </v-card-text>
                    
                    <v-card-text v-if="props.item.type === 'Remanejamento'">
                        Datas Afetadas:<br>
                        Folgar em -
                        {{ props.item.affectedDate}}<br>

                        Trabalhar em - 
                        {{ props.item.newDate}}

                    </v-card-text>
                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="red"
                        flat
                        @click="dialog = false, approveRequest(props.item, false)">
                        Reprovar
                    </v-btn>
                    <v-btn
                        color="green"
                        flat
                        @click="dialog = false, approveRequest(props.item, true)">
                        Aprovar
                    </v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </td>
          </tr>
        </template>
        </v-data-table>
      </v-tab-item>
      <v-tab-item :key="2">
        <v-data-table :headers="headers"
                  :items="approvedRequests"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-2" >
        <template slot="items" slot-scope="props">
          <tr @onclick="props.expanded = !props.expanded">
            <td>{{ props.item.employeeName }}</td>
            <td class="text-xs-left">{{ props.item.type }}</td>
            <td class="text-xs-left" font-color="green">{{ props.item.status }}</td>
            <td class="text-xs-center">
                <v-dialog v-model="dialog" max-width="600px" max-height="300px">
                <v-icon
                    slot="activator"
                    :key="props.item.index"
                    light>
                    edit
                </v-icon>

                <v-card>
                    <v-card-title
                    class="headline grey lighten-2"
                    primary-title
                    >
                    {{ props.item.type }} - {{ props.item.status}}
                    </v-card-title>

                    <v-card-text>
                        {{ props.item.employeeName }}
                    </v-card-text>

                    <v-card-text>
                        Descrição:<br>
                        {{ props.item.description }}
                    </v-card-text>

                    <v-card-text v-if="props.item.type === 'Ausência'">
                        Data Afetada - 
                        {{ props.item.affectedDate}}<br><br>

                        Documento: 
                        <a href="">Download</a>

                    </v-card-text>

                    <v-card-text v-if="props.item.type === 'Correção de Apontamento'">
                        Data Afetada - 
                        {{ props.item.affectedDate}}<br><br>

                        Nova Data - 
                        {{ props.item.newDate}}

                    </v-card-text>
                    
                    <v-card-text v-if="props.item.type === 'Remanejamento'">
                        Datas Afetadas:<br>
                        Folgar em -
                        {{ props.item.affectedDate}}<br>

                        Trabalhar em - 
                        {{ props.item.newDate}}

                    </v-card-text>
                    <v-divider></v-divider>

                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="green"
                        flat
                        @click="dialog = false">
                        OK
                    </v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </td>
          </tr>
        </template>
        </v-data-table>
      </v-tab-item>
    </v-tabs-items>
        </v-tabs>
    </template>
  </v-app>
</template>

<script>
export default {
  data () {
    return {
      dialog: false,
      headers: [
        {
          text: 'Solicitante',
          align: 'left',
          sortable: false,
          value: 'employeeName'
        },
        { text: 'Tipo', value: 'type' },
        { text: 'Status', value: 'status' },
        { text: '' }
      ],
      subHeaders: [
        { text: 'Descrição', value: 'description' }
      ],
      requests: [
        {
          employeeName: 'Jõao Souza',
          type: 'Remanejamento',
          description: 'Troca do feriado da quinta para sexta',
          status: 'Pendente',
          affectedDate: '07/08/2018',
          newDate: '06/08/2018'
        },
        {
          employeeName: 'Ricardo Silva',
          type: 'Correção de Apontamento',
          description: 'Apontamento incorreto de entrada do dia 05/08',
          status: 'Pendente',
          affectedDate: '05/08/2018 - 09:00pm',
          newDate: '05/08/2018 - 09:00am'
        },
        {
          employeeName: 'Maria Franco',
          type: 'Ausência',
          description: 'Consulta medica às 10:00am',
          status: 'Pendente',
          affectedDate: '06/08/2018'
        }
      ],
      approvedRequests: [
        {
          employeeName: 'Carlos Augusto',
          type: 'Remanejamento',
          description: 'Troca do feriado da quinta para sexta',
          status: 'Aprovado',
          affectedDate: '07/08/2018',
          newDate: '06/08/2018'
        },
        {
          employeeName: 'Laura Silva',
          type: 'Correção de Apontamento',
          description: 'Apontamento incorreto de entrada do dia 05/08',
          status: 'Recusado',
          affectedDate: '05/08/2018 - 09:00pm',
          newDate: '05/08/2018 - 09:00am'
        },
        {
          employeeName: 'Maria Franco',
          type: 'Correção de Apontamento',
          description: 'Apontamento incorreto de entrada do dia 06/08',
          status: 'Aprovado',
          affectedDate: '06/08/2018 - 09:00pm',
          newDate: '06/08/2018 - 09:00am'
        }
      ]
    }
  },
  methods: {
    approveRequest (item, val) {
      if (val) {
        item.status = 'Aprovado'
      } else {
        item.status = 'Reprovado'
      }
      this.dialog = false
    }
  }
}
</script>