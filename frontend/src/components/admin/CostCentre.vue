<template>
  <v-app>
      <h1>Gerenciar Centro de Custos</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="dark">
      <v-toolbar-title>Centros de Custos</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-text-field v-model="searchFilter"
                    append-icon="search"
                    label="Search"
                    single-line
                    hide-details>
      </v-text-field>
      <v-dialog v-model="dialog" max-width="1000px" max-height="300px" >
        <v-card>
          <v-card-text>
            <h3>{{this.title}}</h3>
            <br/>
            <!--Add validate method <v-form v-model="valid" @submit.prevent="validateUser"> -->
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-layout row wrap>
              <!-- Row 1 -->
              <v-flex xs12 sm9 md6 lg6 xl4>
                  <v-text-field v-model="costCentre.name"
                                type="text"
                                class="px-0"
                                prepend-icon="assignment"
                                clearable
                                :rules="$v_costCentre.nameRules(costCentre.name)"
                                label="Nome"
                                required>
                  </v-text-field>
                </v-flex>
                <v-flex xs12 sm9 md6 lg6 xl4>
                  <v-text-field v-model="costCentre.code"
                                type="text"
                                class="px-0"
                                prepend-icon="assignment"
                                clearable
                                label="Código"
                                required>
                  </v-text-field>
                </v-flex>
                <v-flex>
                  <v-combobox v-model="costCentre.employees"
                              prepend-icon="check_circle"
                              :items="members"
                              item-text="email"
                              label="Membros"
                              multiple
                              chips>
                    <template slot="selection"
                              slot-scope="data">
                      <v-chip :selected="data.selected"
                              :disabled="data.disabled"
                              :key="JSON.stringify(data.item)"
                              class="v-chip--select-multi"
                              @input="data.parent.selectItem(data.item)">
                        <v-avatar class="accent white--text"
                                  v-text="data.item.email.slice(0, 1).toUpperCase()">
                        </v-avatar>
                          {{ data.item.email}}
                      </v-chip>
                    </template>
                  </v-combobox>
                </v-flex>
              </v-layout>
            </v-form>
            <v-spacer></v-spacer>
            <v-btn @click="saveCostCentre()" 
                   type="submit" 
                   color="success"
                   :disabled="!valid">Salvar</v-btn>
            <v-btn @click="close()" color="error">Cancelar</v-btn>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table :headers="getCostCentreHeaders"
                  :items="this.itemsToShow"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-2" >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.code }}</td>
          <td class="text-xs-center">{{ props.item.name }}</td>
          <td class="text-xs-center">{{ props.item.nickname }}</td>
          <td class="text-xs-center">{{ props.item.employees.length }}</td>
          <td class="text-xs-center">{{ props.item.creationDate }}</td>
          <td class="justify-center layout px-0">
            <!-- <v-icon class="mr-2"
                    @click.stop="editItem(props.item)">person_add</v-icon> -->
            <v-icon class="mr-2"
                    @click.stop="editItem(props.item)">edit</v-icon>
            <v-icon class="mr-2"
                    @click.stop="deleteItem(props.item)">delete</v-icon>
          </td>
        </tr>
      </template>
    </v-data-table>
    <v-card>
        <v-card-title></v-card-title>
        <v-card-text style="height: 100px; position: absolute">
        <v-fab-transition>
            <v-btn @click="addCostCentres()"
                   color="blue"
                   dark
                   absolute
                   top
                   right
                   fab >
            <v-icon>add</v-icon>
            </v-btn>
        </v-fab-transition>
        </v-card-text>
    </v-card>
  </v-app>
</template>

<script>
import AdminService from '@/service/AdminService'
// import BaseHourCalculation from '../../objects/BaseHourCalculation'
// import Telephone from '../../objects/Telephone'
// import Employee from '../../objects/Employee'
// import Login from '../../objects/Login'
// import User from '../../objects/User'

export default {
  props: {
    item: {
      type: Object
    }
  },
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    searchFilter: '',
    editedIndex: -1,
    dialog: false,
    isEditing: false,
    costCentre: {},
    costCentres: [],
    members: [],
    valid: false,
    employessToSave: []
  }),
  computed: {
    getCostCentreHeaders () {
      return AdminService.COST_CENTRE_HEADERS
    },
    title () {
      return this.edit ? 'Alterar Centro de Custo' : 'Cadastrar Centro de Custo'
    },
    itemsToShow () {
      return this.costCentres
    }
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  mounted () {
    this.getCostCentresList()
  },
  methods: {
    clearForm () {
      this.$refs.form.reset()
    },
    addCostCentres () {
      this.dialog = true
      this.getMembersList()
      // this.clearForm()
    },
    saveCostCentre () {
      // User either to save or edit cost centres
      // alert(JSON.stringify(this.costCentre, null, ' '))
      this.employessToSave = this.costCentre.employee
      this.$_axios.post(`${this.$_url}costcentre`, this.costCentre).then((response) => {
        let result = response.data
        console.log(JSON.stringify(result.resultList), null, ' ')
        // alert(this.editedIndex)
        if (result.resultList.length !== 0) {
          this.getCostCentresList()
        }
        if (result.message) {
          // alert('Messages everything OK')
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
          // retorno mensagem de sucesso
            this.messageColor = 'info'
          } else {
            // retorno mensagem de erro
            this.messageColor = 'warning'
          }
        }
        this.dialog = false
        this.costCentre = {}
      },
      (response) => {
        // erro na requisição do serviço
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    editItem (item) {
      this.editedIndex = this.costCentres.indexOf(item)
      this.costCentre = Object.assign({}, item)
      this.getMembersList()
      this.dialog = true
      this.isEditing = true
    },
    deleteItem (item) {
      let index = this.costCentres.indexOf(item)
      confirm('Tem certeza que deseja excluir este Centro de Custo?') && this.costCentres.splice(index, 1)
      this.$_axios.delete(`${this.$_url}costcentre/${item.id}`).then((response) => {
        let result = response.data
        // this.costCentres = this.costCentres.splice(index, 1)
        if (result.mensagem) {
          this.messages = [...result.mensagem]
          this.haveMessage = true
          if (result.success) {
            // retorno mensagem de sucesso
            this.messageColor = 'info'
          } else {
            // retorno mensagem de erro
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        // erro na requisição do serviço
        alert(JSON.stringify(response, null, ' '))
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    close () {
      this.dialog = false
      this.isEditing = false
      this.costCentre = Object.assign({}, {})
      setTimeout(() => {
        this.costCentre = Object.assign({}, this.costCentre)
      }, 300)
    },
    getMembersList () {
      this.$_axios.get(`${this.$_url}employee`).then((response) => {
        let resultado = response.data
        // alert(JSON.stringify(resultado, null, ' '))
        if (resultado.resultList.length >= 0) {
          // Fetch a list of cost centres from backend
          this.members = resultado.resultList
        }
        if (resultado.message) {
          this.messages = [...resultado.message]
          this.haveMessage = true
          if (resultado.sucesso) {
            // Success message
            this.messageColor = 'info'
          } else {
            // Waring message
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        console.log(JSON.stringify(response, null, ' '))
        // Error during request
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    getCostCentresList () {
      this.$_axios.get(`${this.$_url}costcentre`).then((response) => {
        let resultado = response.data
        // alert(JSON.stringify(resultado, null, ' '))
        if (resultado.resultList.length >= 0) {
          // Fetch a list of cost centres from backend
          this.costCentres = resultado.resultList
        }
        if (resultado.message) {
          this.messages = [...resultado.message]
          this.haveMessage = true
          if (resultado.sucesso) {
            // Success message
            this.messageColor = 'info'
          } else {
            // Waring message
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        console.log(JSON.stringify(response, null, ' '))
        // Error during request
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    saveUser (user) {
      alert(JSON.stringify(user))
      this.$_axios.post(`${this.$_url}employee`, user).then((response) => {
        let result = response.data
        alert(JSON.stringify(result))
        if (result.resultList.length !== 0) {
          // retorno ok
          alert(JSON.stringify(result))
          user = result.resultList[0]
        }
        if (result.message) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
          // retorno mensagem de sucesso
            this.messageColor = 'info'
            this.clearForm()
          } else {
            // retorno mensagem de erro
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        // erro na requisição do serviço
        alert(JSON.stringify(response))
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    }
  },
  components: {
    // Consultar,
    // Listar,
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
  h3 {
    font-size: 35px;
    text-align: center;
    margin-bottom: 20px;
  }
</style>
