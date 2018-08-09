<template>
  <v-app>
    <v-data-table :headers="headers" :items="usuarios" hide-actions class="elevation-10">
      <template slot="items" slot-scope="usuario">
        <td>{{ counter }}</td>
        <td>{{ usuario.item.nome }}</td>
        <td>{{ usuario.item.email }}</td>
        <td>{{ usuario.item.login.nomeLogin }}</td>
        <td>{{ usuario.item.login.dataCriacao }}</td>
        <td>{{ usuario.item.roles[0].nome }}</td>
        <td><v-btn color="info"><router-link to="AlterarUsuario"></router-link>Alterar</v-btn></td>
        <td><v-btn color="warning" @click="excluir">Excluir</v-btn></td>
      </template>
    </v-data-table>
  </v-app>
</template>

<script>
import axios from 'axios'

export default {
  data: () => ({
    usuarios: [],
    counter: 0,
    headers: [
      {text: '#', value: this.counter},
      {text: 'Nome', value: 'nome'},
      {text: 'E-mail', value: 'email'},
      {text: 'Login', value: 'login.loginUsuario'},
      {text: 'Data de criação', value: 'login.dataCriacao'},
      {text: 'Role', value: 'role.nome'},
      {text: 'Alterar', value: 'alterar'},
      {text: 'Excluir', value: 'excluir'}
    ]
  }),
  methods: {
    async findAll () {
      await axios.get('http://localhost:8080/usuario').then(response => {
        this.usuarios = response.data
      },
      response => {
        console.log(response)
      })
    },
    async excluir () {
    }
  },
  mounted () {
    this.findAll()
  }
}
</script>

<style scoped>
</style>
