<template>
  <v-app>
    <v-card class="elevation-10">
      <v-toolbar dark>
        <v-toolbar-title>Formulário de consulta</v-toolbar-title>
      </v-toolbar>

      <v-form @submit.prevent="findByUser">
        <li v-for="error in msgErro" :key="error">
          <v-alert :color="msgCor" :value="isErro" v-text="error" transition="scale-transition" />
        </li>
        <v-card-text>
          <v-layout justify-space-around row>
            <v-text-field v-model="usuario.nome" prepend-icon="person" :rules="nomeRegras" :counter="40" label="Nome" />
            <v-menu ref="menuData" v-model="menuData" :close-on-content-click="true" :nudge-right="40"
              :return-value.sync="usuario.login.dataCriacao"
              lazy transition="scale-transition" offset-y full-width max-width="290px" min-width="290px">
              <v-text-field slot="activator" :rules="dataCriacaoRegras" :value="date | dataFormatada"
                label="Data de criação" prepend-icon="event" readonly />
              <v-date-picker v-model="date" :landscape="landscape" :reactive="reactive" scrollable />
            </v-menu>
          </v-layout>

          <v-layout justify-space-around row>
            <v-text-field v-model="usuario.email" prepend-icon="email" label="E-mail" />
            <v-text-field v-model="usuario.login.nomeLogin" prepend-icon="account_circle" label="Login" />
          </v-layout>
        </v-card-text>

        <v-card-actions>
          <v-spacer />
          <v-btn color="info" type="submit">Pesquisar</v-btn>
        </v-card-actions>
      </v-form>

    </v-card>
  </v-app>
</template>

<script>
import axios from 'axios'
import moment from 'moment'

export default {
  data: () => ({
    usuario: {
      nome: '',
      email: '',
      login: {
        dataCriacao: '',
        nomeLogin: ''
      }
    },
    isErro: false,
    msgErro: [],
    msgCor: '',
    dataCriacao: null,
    menuData: false,
    modal: false,
    nomeRegras: [v => /^[A-z\s]*$/.test(v) || 'Nome deve conter apenas letras.'],
    dataCriacaoRegras: [v => v <= moment(new Date()).format('DD/MM/YYYY') || 'Data deve ser menor ou igual a data atual.']
  }),
  methods: {
    async findByUser () {
      this.usuario.login.dataCriacao = this.date
      if (!this.usuario.nome && !this.usuario.email && !this.usuario.login.dataCriacao && !this.usuario.login.nomeLogin) {
        this.isErro = true
        this.msgCor = 'warning'
        this.msgErro = ['Para realizar a consulta ao menos um dos campos deve ser preenchido!']
      } else {
        axios.post('http://localhost:8080/usuarioFiltro', this.usuario).then(response => {
          alert(JSON.stringify(response))
        },
        reposnse => {
          alert(reposnse)
        })
      }
    }
  }
}
</script>
