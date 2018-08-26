<template>
  <v-app>

    <v-card class="elevation-10">
      <v-toolbar dark>
        <v-toolbar-title>Login</v-toolbar-title>
      </v-toolbar>
        <li v-for="error in msgErro" :key="error">
          <v-alert :color="msgCor" :value="isErro" v-text="error" transition="scale-transition" />
        </li>
      <v-form v-model="valido" @submit.prevent="findByNomeAndSenha">
        <v-card-text>
          <text v-bind="login" />
          <v-text-field prepend-icon="person" v-model="nome" :rules="nomeRegras" label="Nome" required />
          <v-text-field prepend-icon="lock" v-model="senha" :rules="senhaRegras" label="Senha" type="password" required />
        </v-card-text>

        <v-card-actions>
          <v-spacer />
          <v-btn color="info" type="submit">Login</v-btn>
        </v-card-actions>
      </v-form>

    </v-card>

  </v-app>
</template>

<script>
import axios from 'axios'

export default {
  data: () => ({
    login: [],
    usuario: [],
    valido: false,
    nome: '',
    senha: '',
    isErro: false,
    msgErro: [],
    msgCor: '',
    nomeRegras: [v => !!v || 'Nome obrigatório!'],
    senhaRegras: [
      v => !!v || 'Senha obrigatória!',
      v => v.length >= 3 || 'Senha deve conter no mínimo 3 caracteres'
    ]
  }),
  methods: {
    findByNomeAndSenha () {
      if ((!this.nome && !this.senha) || this.senha < 3) {
        this.isErro = true
        this.msgCor = 'warning'
        if (!this.nome && !this.senha) {
          this.msgErro = ['Nome e Senha devem ser preenchidos!']
        } else {
          this.msgErro = ['Senha deve conter no mínimo 3 caracteres!']
        }
      } else {
        let usuario = {
          login: {
            nomeLogin: this.nome,
            senha: this.senha
          }
        }
        axios.post('http://localhost:8080/usuarioLogin', usuario).then(response => {
          let resultado = response.data
          if (resultado.listaResultado.length !== 0) {
            this.usuario = response.data
            this.isErro = true
            this.msgCor = 'info'
            this.msgErro = [`Bem vindo ${this.usuario.login.nomeLgin}.`]
            this.$emit('login', this.usuario.login)
          } else if (resultado.mensagem) {
            this.isErro = true
            this.msgCor = 'warning'
            this.msgErro = [...resultado.mensagem]
          } else {
            /* this.isErro = true
            this.msgCor = 'warning'
            this.msgErro = [`Usuário ${usuario.login.nomeLogin} não encontrado!`] */

            /* USER MOCK */
            this.usuario = {
              login: {
                nomeLogin: 'wesley',
                senha: '123',
                ativo: true,
                dataCriacao: 'null'
              },
              roles: [
                {nome: 'ADMIN'}
              ]
            }
            this.$emit('emittedUsuario', this.usuario)
          }
        },
        response => {
          this.isErro = true
          this.msgCor = 'error'
          this.msgErro = ['Erro durante execução do serviço!']
          console.log(response.data)
        })
      }
    }
  }
}
</script>

