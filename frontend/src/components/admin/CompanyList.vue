<template>
  <div>

     <v-toolbar class="elevation-10" dark>
      <v-toolbar-title color="white">Lista de empresas cadastradas</v-toolbar-title>

      <div class="text-xs-right">
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
      no-data-text="Nenhum registro foi econtrado"
      class="elevation-10"
    >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.tradingName }}</td>
          <td class="text-xs-right">{{ props.item.socialName }}</td>
          <td class="text-xs-right">{{ props.item.cnpj }}</td>
          <td class="text-xs-right">{{ props.item.stateRegistration }}</td>
          <td class="justify-center layout px-0">
            <v-icon class="justify-center" @click="editItem(props.item)" >
              edit
            </v-icon>
            <v-icon @click="deleteDialog(props.item)" >
              delete
            </v-icon>
          </td>
        </tr>
      </template>

      <template slot="expand" slot-scope="props">
        <v-data-table :headers="subHeaders"
                      :items="companyList"
                      hide-actions dark
                      no-data-text="Nenhum registro foi econtrados"
                      class="elevation-10" >
          <template slot="items" slot-scope="props">
            <td class="text-xs-right">{{ props.item.address.zipCode }}</td>
            <td class="text-xs-right">{{ props.item.address.number }}</td>
            <td class="text-xs-right">{{ props.item.address.street }}</td>
            <td class="text-xs-right">{{ props.item.address.city }}</td>
            <td class="text-xs-right">{{ props.item.address.state }}</td>
          </template>
        </v-data-table>
      </template>

      <template slot="footer">
        <v-btn color="primary" @click="initialize">Refazer consulta</v-btn>
      </template>

    </v-data-table>

  </div>
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
          { text: 'Empresas', value: 'tradingName', align: 'left' },
          { text: 'Razão Social', value: 'socialName', align: 'right' },
          { text: 'CNPJ', value: 'cnpj', align: 'right' },
          { text: 'Inscrição Estadual', value: 'stateRegistration', align: 'right' },
          { text: 'Opções', align: 'center' }
        ],
        subHeaders: [
          { text: 'CEP', value: 'zipCode', align: 'right' },
          { text: 'Número', value: 'number', align: 'right' },
          { text: 'Rua', value: 'street', align: 'right' },
          { text: 'Cidade', value: 'city', align: 'right' },
          { text: 'Estado', value: 'state', align: 'right' }
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
        console.log(JSON.stringify(item))
        this.$emit('emitCompany', item)
      },
      deleteDialog (item) {
        this.deleteIndex = this.companyList.indexOf(item)
        this.selectedItem = item
        this.dialog = true
      },
      deleteItem () {
        this.close()
        this.$_axios.delete(`${this.$_url}company/${this.selectedItem.id}`).then(reponse => {
          let result = reponse.data
          this.fillDataTable()
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
        this.$_axios.patch(`${this.$_url}company`, {}).then(response => {
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
