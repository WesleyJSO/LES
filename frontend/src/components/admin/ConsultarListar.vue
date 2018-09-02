<template>
  <v-app>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <v-toolbar flat color="white">
      <v-toolbar-title>Funcionários</v-toolbar-title>
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
            <register-user :user="editedItem"
                           :edit="isEditing"
                           @save="save">
            </register-user>
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table :headers="getHeaders"
                  :items="users"
                  item-key="pis"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-2" >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.nome }}</td>
          <td class="text-xs-center">{{ props.item.listaBaseCalculoHoras[0].cargaHoraria }}</td>
          <td class="text-xs-center">{{ props.item.listaBaseCalculoHoras[0].salario }}</td>
          <td class="text-xs-center">{{ props.item.listaBaseCalculoHoras[0].tipoHora === -1 ? 'BANCO' : 'EXTRA' }}</td>
          <td class="text-xs-center">{{ props.item.gestor.nome }}</td>
          <td class="text-xs-center">{{ props.item.listaTelefone[0].numero }}</td>
          <td class="text-xs-center">{{ props.item.email }}</td>
          <td class="justify-center layout px-0">
            <v-icon small
                    class="mr-2"
                    @click.stop="editItem(props.item)">edit</v-icon>
            <v-icon small
                    @click.stop="deleteItem(props.item)">delete
            </v-icon>
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
            <td class="text-xs-center">{{ props.item.dataNascimento }}</td>
            <td class="text-xs-center">{{ props.item.dataIngressoEmpresa }}</td>
            <td class="text-xs-center">{{ props.item.listaTelefone[1].numero }}</td>
            <td class="text-xs-center">{{ props.item.listaTelefone[2].numero }}</td>
          </template>
        </v-data-table>
      </template>
    </v-data-table>
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
      type: Object,
      default () {
        return {
          thelephoneList: ['46784571'],
          password: '',
          passwordValidation: ''
        }
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
    }
  },
  data: () => ({
    messages: [],
    haveMessage: false,
    messageColor: '',
    searchFilter: '',
    editedIndex: -1,
    dialog: false,
    isEditing: false
  }),
  computed: {
    getHeaders () {
      return AdminService.HEADERS
    },
    getSubHeaders () {
      return AdminService.SUB_HEADERS
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
