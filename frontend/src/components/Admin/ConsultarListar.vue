<template>
  <v-app>
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
            <register-user :user="itemEdit"
                           :edit="isEditing">
            </register-user>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click.native="close">Cancelar</v-btn>
            <v-btn color="blue darken-1" flat @click.native="save">Salvar</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table :headers="headers"
                  :items="users"
                  item-key="id"
                  hide-actions
                  :search="searchFilter"
                  class="elevation-2" >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded">
          <td>{{ props.item.name }}</td>
          <td class="text-xs-center">{{ props.item.cargaHoraria }}</td>
          <td class="text-xs-center">{{ props.item.salario }}</td>
          <td class="text-xs-center">{{ props.item.tipoHora }}</td>
          <td class="text-xs-center">{{ props.item.gestor }}</td>
          <td class="text-xs-center">{{ props.item.phone1 }}</td>
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
        <v-data-table :headers="subHeaders"
                      :items="[...props.item].splice(0, 6)"
                      hide-actions
                      dark
                      class="elevation-1" >
          <template slot="items" slot-scope="props">
            <td class="text-xs-center">{{ props.item.pis }}</td>
            <td class="text-xs-center">{{ props.item.dataNascimento }}</td>
            <td class="text-xs-center">{{ props.item.dataIngressoEmpresa }}</td>
            <td class="text-xs-center">{{ props.item.phone2 }}</td>
            <td class="text-xs-center">{{ props.item.phone3 }}</td>
          </template>
        </v-data-table>
      </template>
    </v-data-table>
  </v-app>
</template>

<script>
// import Consultar from '@/components/usuario/Consultar'
// import Listar from '@/components/usuario/Listar'
import RegisterUser from '@/components/Admin/RegisterUser'

export default {
  data: () => ({
    headers: [
      { text: 'Nome', align: 'center', sortable: false, value: 'name' },
      { text: 'Carga Horária', align: 'center', value: 'cargaHoraria' },
      { text: 'Salário', value: 'salario' },
      { text: 'Tipo de Hora', value: 'tipoHora' },
      { text: 'Gestor', value: 'gestor' },
      { text: 'Telefone', value: 'phone1' },
      { text: 'E-mail', align: 'center', value: 'email' },
      { text: 'Actions', value: 'name', sortable: false }
    ],
    subHeaders: [
      { text: 'PIS/PASESP', value: 'pis', align: 'center', sortable: false },
      { text: 'Data de Nascimento', value: 'birthdate', align: 'center', sortable: false },
      { text: 'Data de Ingresso na Empresa', value: 'dataIngressoEmpresa', align: 'center', sortable: false },
      { text: 'Telefone 2', value: 'phone2', align: 'center', sortable: false },
      { text: 'Telefone 3', value: 'phone3', align: 'center', sortable: false }
    ],
    // * MOCK *
    users: [
      {
        id: 0,
        name: 'Zeller',
        lastName: 'Gonçalves',
        cargaHoraria: 6,
        salario: 2000,
        tipoHora: 'Banco de Horas',
        pis: 1890144,
        dataNascimento: '06/05/1998',
        dataIngressoEmpresa: '29/05/2018',
        phone1: '4678-2617',
        phone2: '',
        phone3: '',
        email: 'josevictorzg@gmail.com',
        password: ''
      },
      {
        id: 1,
        name: 'Victor',
        lastName: 'Gonçalves',
        cargaHoraria: 6,
        salario: 2000,
        tipoHora: 'Banco de Horas',
        pis: 1892222,
        dataNascimento: '06/05/1998',
        dataIngressoEmpresa: '29/05/2018',
        phone1: '4678-4674',
        phone2: '99867-9124',
        phone3: '',
        email: 'josevictorzg@hotmail.com',
        password: '',
        password2: ''
      }
    ],
    searchFilter: '',
    editedIndex: -1,
    dialog: false,
    itemEdit: {},
    isEditing: false
  }),
  computed: {
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
    },
    editItem (item) {
      // Emit messagem to RegisterUser.vue component
      //
      /* this.editedIndex = this.users.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true */
      this.dialog = true
      this.isEditing = true
      this.itemEdit = Object.assign({}, item)
    },
    deleteItem (item) {
      const index = this.users.indexOf(item)
      confirm('Are you sure you want to delete this item?') && this.users.splice(index, 1)
    },
    close () {
      this.dialog = false
      this.isEditing = false
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      }, 300)
    },
    save () {
      if (this.editedIndex > -1) {
        Object.assign(this.users[this.editedIndex], this.editedItem)
      } else {
        this.users.push(this.editedItem)
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
