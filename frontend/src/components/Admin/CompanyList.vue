<template>
  <v-app>

     <v-toolbar class="elevation-10" dark>
      <v-toolbar-title color="white">Lista de empresas cadastradas</v-toolbar-title>

      <div class="text-xs-center">
        <v-dialog v-model="dialog" max-width="500px">
          <v-card>
            <v-card-title primary-title class="headline grey lighten-2">
              <span>Excluir</span>
            </v-card-title>

            <v-card-text>
              <span>Deseja realmente deletar este item?</span>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red darken-1" flat @click="deleteItem">Deletar</v-btn>
              <v-btn color="blue darken-1" flat @click.native="close">Cancelar</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </div>

    </v-toolbar>

    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>

    <v-data-table
      :headers="headers"
      :items="companyList"
      hide-actions
      class="elevation-10"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.tradingName }}</td>
        <td class="text-xs-right">{{ props.item.socialName }}</td>
        <td class="text-xs-right">{{ props.item.cnpj }}</td>
        <td class="text-xs-right">{{ props.item.stateRegistration }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(props.item)" >
            edit
          </v-icon>
          <v-icon small @click="deleteDialog(props.item)" >
            delete
          </v-icon>
        </td>
      </template>
      <template slot="no-data">
        <v-alert :value="true" :color="errorColor" icon="warning">
          {{ errorMessage }}
        </v-alert>
        <v-btn color="primary" @click="initialize">Refazer consulta</v-btn>
      </template>
    </v-data-table>

  </v-app>
</template>

<script>
  export default {
    data: function () {
      return {
        haveMessage: false,
        messages: [],
        messageColor: '',
        errorColor: '',
        errorMessage: '',
        dialog: false,
        headers: [
          {
            text: 'Empresas',
            align: 'left',
            sortable: false,
            value: 'name'
          },
          { text: 'Razão Social', value: 'socialName', align: 'right' },
          { text: 'CNPJ', value: 'zipCode', align: 'right' },
          { text: 'Inscrição Estadual', value: 'stateRegistration', align: 'right' }
        ],
        companyList: [],
        selectedItem: {}
      }
    },
    watch: {
      dialog (val) {
        val || this.close()
      }
    },

    created () {
      this.initialize()
    },
    methods: {
      initialize () {
        this.fillDataTable()
        this.errorColor = 'warning'
        this.errorMessage = 'Não existem registros na base de dados!'
        this.haveMessage = false
        this.messages = []
        this.messageColor = ''
      },
      editItem (item) {
        const index = this.companyList.indexOf(item)
        confirm('Are you sure you want to delete this item?') && this.companyList.splice(index, 1)
      },
      deleteDialog (item) {
        this.deleteIndex = this.companyList.indexOf(item)
        this.selectedItem = item
        this.dialog = true
      },
      deleteItem () {
        this.close()
        console.log(JSON.stringify(this.selectedItem.id))
        this.$_axios.delete(`${this.$_url}company/${this.selectedItem.id}`).then(reponse => {
          let result = reponse.data
          if (result.message) {
            this.messages = result.message
            this.haveMessage = true
            if (result.success) {
              this.companyList.splice(this.deleteIndex, 1)
              this.messageColor = 'info'
            } else {
              this.messageColor = 'warning'
            }
          }
        }).catch(error => {
          console.log(error)
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      },
      close () {
        this.dialog = false
      },
      save () {
        if (this.deleteIndex > -1) {
          Object.assign(this.companyList[this.deleteIndex], this.editedItem)
        } else {
          this.companyList.push(this.editedItem)
        }
        this.close()
      },
      fillDataTable () {
        this.$_axios.get(`${this.$_url}company`).then(response => {
          let result = response.data
          if (result.resultList.length !== 0) {
            this.companyList = result.resultList
          } else {
            this.errorColor = 'warning'
            this.errorMessage = 'Não existem registros na base de dados!'
          }
        }).catch(error => {
          console.log(error)
          this.errorColor = 'error'
          this.errorMessage = 'Nenhuma informação encontrada!'
        })
      }
    }
  }
</script>
