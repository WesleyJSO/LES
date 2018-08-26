<template>
  <v-app>

    <v-card class="elevation-10">
      <v-toolbar dark>
        <v-toolbar-title>Login</v-toolbar-title>
      </v-toolbar>
        <v-alert :color="msgCor" :value="isErro" v-text="msgErro" transition="scale-transition" />

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
// import axios from 'axios'

export default {
  data: () => ({
    login: [],
    valido: false,
    nome: '',
    senha: '',
    isErro: false,
    msgErro: '',
    msgCor: '',
    nomeRegras: [v => !!v || 'Nome obrigatório!'],
    senhaRegras: [
      v => !!v || 'Senha obrigatória!',
      v => v.length >= 3 || 'Senha deve conter no mínimo 3 caracteres'
    ]
  }),
  methods: {
    async findByNomeAndSenha () {
      if ((!this.nome && !this.senha) || this.senha < 3) {
        this.isErro = true
        this.msgCor = 'warning'
        if (!this.nome && !this.senha) {
          this.msgErro = 'Nome e Senha devem ser preenchidos!'
        } else {
          this.msgErro = 'Senha deve conter no mínimo 3 caracteres!'
        }
      } else {
        // let login = {nomeLogin: this.nome, senha: this.senha}
        /* MOCK */
        this.login = {
          nomeLogin: 'zeller',
          senha: '123',
          dataCriacao: 'null',
          ativo: true,
          usuario: {
            nome: 'JoZeller',
            email: 'teste@teste.com',
            roles: [
              {nome: 'ADMIN'}
            ]
          }
        }
        this.$emit('message', this.login)
        /* axios.post('http://localhost:8080/login', login).then(response => {
          if (response.data === '') {
            this.isErro = true
            this.msgCor = 'warning'
            this.msgErro = 'Usuário não encontrado!'
          } else {
            this.login = response.data
            this.isErro = true
            this.msgCor = 'info'
            this.$emit('message', this.login)
            this.msgErro = 'Bem vindo ' + login.nomeLogin + '.'
          }
        },
        response => {
          this.isErro = true
          this.msgCor = 'error'
          this.msgErro = 'Erro durante execução do serviço!'
          console.log(response.data)
        }) */
      }
    }
  }
}
</script>

<style scoped>

</style>
