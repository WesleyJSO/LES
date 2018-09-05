<template>
  <v-app>
      <h1>Gerenciar Grupos</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="dark">
      <v-toolbar-title>Gerenciar Grupos</v-toolbar-title>
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
            <h3>Cadastrar Grupo</h3>
            <br/>
            <!--Add validate method <v-form v-model="valid" @submit.prevent="validateUser"> -->
            <v-form>
              <v-layout>
              <!-- Row 1 -->
              <v-flex xs12 sm9 md6 lg6 xl4>
                  <v-text-field v-model="groups.name"
                                type="text"
                                class="px-0"
                                prepend-icon="assignment"
                                clearable
                                label="Nome"
                                required>
                  </v-text-field>
                </v-flex>
                <v-flex>
                  <v-combobox v-model="groups.members"
                              prepend-icon="face"
                              :items="members"
                              label="Membros"
                              chips>
                    <template slot="selection"
                              slot-scope="data">
                      <v-chip :selected="data.selected"
                              :disabled="data.disabled"
                              :key="JSON.stringify(data.item)"
                              class="v-chip--select-multi"
                              @input="data.parent.selectItem(data.item)">
                        <v-avatar class="accent white--text"
                                  v-text="data.item.slice(0, 1).toUpperCase()">
                        </v-avatar>
                          {{ data.item }}
                      </v-chip>
                    </template>
                  </v-combobox>
                </v-flex>
              </v-layout>
              <v-layout>
                <v-flex>
                  <v-combobox v-model="groups.type"
                              prepend-icon="check_circle"
                              :items="members"
                              label="Centro de Custo"
                              chips>
                    <template slot="selection"
                              slot-scope="data">
                      <v-chip :selected="data.selected"
                              :disabled="data.disabled"
                              :key="JSON.stringify(data.item)"
                              class="v-chip--select-multi"
                              @input="data.parent.selectItem(data.item)">
                        <v-avatar class="accent white--text"
                                  v-text="data.item.slice(0, 1).toUpperCase()">
                        </v-avatar>
                          {{ data.item }}
                      </v-chip>
                    </template>
                  </v-combobox>
                </v-flex>
              </v-layout>
            </v-form>
            <v-spacer></v-spacer>
            <v-btn @click="addAppoint" type="submit" color="success">Salvar</v-btn>
            <v-btn color="error">Cancelar</v-btn>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table :headers="getGroupHeaders"
                  :items="users"
                  item-key="nome"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-2" >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.name }}</td>
          <td class="text-xs-center">{{ props.item.data }}</td>
          <td class="text-xs-center">{{ props.item.centroCusto }}</td>
          <td class="text-xs-center">{{ props.item.membros }}</td>
          <td class="justify-center layout px-0">
            <v-icon 
                    class="mr-2"
                    @click.stop="editItem(props.item)">person_add</v-icon>
            <v-icon
            class="mr-2"
            @click.stop="editItem(props.item)">edit</v-icon>
            <v-icon 
            class="mr-2"
            @click.stop="editItem(props.item)">delete</v-icon>
          </td>
        </tr>
      </template>
      <template slot="expand" slot-scope="props">
        <v-data-table :headers="getSubHeaders"
                      :items="[...props.item].splice(0, 6)"
                      hide-actions
                      dark
                      class="elevation-1" >
          <template slot="items" slot-scope="props">
            <td class="text-xs-center">{{ props.item.pis }}</td>
            <td class="text-xs-center">{{ props.item.birthDate }}</td>
            <td class="text-xs-center">{{ props.item.entryDateInCompany }}</td>
            <td class="text-xs-center">{{ props.item.thelephoneList[1].number }}</td>
            <td class="text-xs-center">{{ props.item.thelephoneList[1].number }}</td>
          </template>
        </v-data-table>
      </template>
    </v-data-table>
    <v-card>
        <v-card-title></v-card-title>
        <v-card-text style="height: 100px; position: absolute">
        <v-fab-transition>
            <v-btn @click="addAppointment()"
                    color="blue"
                    dark
                    absolute
                    top
                    right
                    fab>
            <v-icon>add</v-icon>
            </v-btn>
        </v-fab-transition>
        </v-card-text>
    </v-card>
  </v-app>
</template>

<script>
// import Consultar from '@/components/usuario/Consultar'
// import Listar from '@/components/usuario/Listar'
import RegisterUser from '@/components/admin/RegisterUser'
import Employee from '@/objects/Employee'
import BaseHourCalculation from '@/objects/BaseHourCalculation'
import Telephone from '@/objects/Telephone'
import Login from '@/objects/Login'
import AdminService from '@/service/AdminService'

export default {
  props: {
    users: {
      type: Array,
      default () {
        return [{
          name: 'Projeto X',
          data: '21/10/2010',
          centroCusto: 'Projeto X',
          membros: '5'
        }]
      }
    },
    editedItem: {
      type: Object,
      default () {
        return {
          thelephoneList: [],
          password: '',
          passwordValidation: ''
        }
      }
    },
    groups: {
      type: Object,
      default () {
        return {}
      }
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
    members: ['João', 'Maria', 'Laura']
  }),
  computed: {
    getHeaders () {
      return AdminService.HEADERS
    },
    getSubHeaders () {
      return AdminService.SUB_HEADERS
    },
    getGroupHeaders () {
      return AdminService.GROUPS_HEADERS
    }
  },
  watch: {
    dialog (val) {
      val || this.close()
    }
  },
  created () {
    /* this.$_axios.get(`${this.$_url}funcionario`).then((response) => {
      let resultado = response.data
      if (resultado.listaResultado.length !== 0) {
        // retorno ok
        this.users = resultado.listaResultado
      }
      if (resultado.mensagem) {
        this.messages = [...resultado.mensagem]
        this.haveMessage = true
        if (resultado.sucesso) {
          // retorno mensagem de sucesso
          this.messageColor = 'info'
        } else {
          // retorno mensagem de erro
          this.messageColor = 'warning'
        }
      }
    },
    (response) => {
      console.log(JSON.stringify(response, null, ' '))
      // erro na requisição do serviço
      this.messages = ['Erro durante execução do serviço!']
      this.haveMessage = true
      this.messageColor = 'error'
    }) */
  },
  methods: {
    initialize () {
    },
    addAppointment () {
      this.dialog = true
    },
    prepareUserObject (userObject) {
      let listaTelefone = []
      listaTelefone.push(new Telephone(userObject.telefone1), new Telephone(userObject.telefone2), new Telephone(userObject.telefone3))
      let funcionario = new Employee(
        userObject.id,
        userObject.nome,
        userObject.email,
        userObject.ultimoNome,
        userObject.dataNascimento,
        new Login(userObject.senha, userObject.senhaValidacao, userObject.nomeLogin, userObject.ativo),
        listaTelefone,
        userObject.pis,
        userObject.dataIngressoEmpresa,
        new Employee(0, userObject.gestor),
        new BaseHourCalculation(0, userObject.tipoHora, userObject.cargaHoraria, null, userObject.salario)
      )
      return funcionario
    },
    editItem (item) {
      // Emit messagem to RegisterUser.vue component
      //
      this.editedIndex = this.users.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
      this.isEditing = true
    },
    deleteItem (item) {
      const index = this.users.indexOf(item)
      confirm('Tem certeza que deseja excluir este usuário?') && this.users.splice(index, 1)
      /* this.$_axios.post(`${this.$_url}funcionario`, funcionario).then((response) => {
        let resultado = response.data
        if (resultado.listaResultado.length !== 0) {
          /* retorno ok
          this.funcionario = resultado.listaResultado
        }
        if (resultado.mensagem) {
          this.messages = [...resultado.mensagem]
          this.haveMessage = true
          if (resultado.sucesso) {
          /* retorno mensagem de sucesso
            this.messageColor = 'info'
          } else {
            /* retorno mensagem de erro
            this.messageColor = 'warning'
          }
        }
      },
      (response) => {
        /* erro na requisição do serviço
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      }) */
    },
    close () {
      this.dialog = false
      this.isEditing = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },
    save (funcionario) {
      alert('Parent Save', JSON.stringify(funcionario, null, ' '))
      this.$_axios.post(`${this.$_url}funcionario`, funcionario).then((response) => {
        let resultado = response.data
        if (resultado.listaResultado.length !== 0) {
          // retorno ok
          this.funcionario = resultado.listaResultado
        }
        if (resultado.mensagem) {
          this.messages = [...resultado.mensagem]
          this.haveMessage = true
          if (resultado.sucesso) {
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
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
      if (this.editedIndex > -1) {
        Object.assign(this.users[this.editedIndex], funcionario)
      } else {
        this.users.push(this.funcionario)
      }
      this.close()
    }
  },
  components: {
    // Consultar,
    // Listar,
    RegisterUser
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
