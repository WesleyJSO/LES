<template>
  <v-app>

    <v-card class="elevation-10">
      <v-toolbar dark>
        <v-toolbar-title>Login</v-toolbar-title>
      </v-toolbar>
        <li v-for="error in msgErro" :key="error">
          <v-alert :color="msgCor"
                   :value="isErro"
                   v-text="error"
                   transition="scale-transition" />
        </li>
      <v-form v-model="valido"
              @submit.prevent="findByEmailAndPassword">
        <v-card-text>
          <text v-bind="login" />
          <v-text-field prepend-icon="person"
                        v-model="email"
                        :rules="emailRules"
                        label="E-mail"
                        required />
          <v-text-field prepend-icon="lock"
                        v-model="password"
                        :rules="passwordRules"
                        label="Senha"
                        type="password"
                        required />
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
export default {
  data: () => ({
    login: [],
    user: [],
    valido: false,
    email: '',
    password: '',
    isErro: false,
    msgErro: [],
    msgCor: '',
    emailRules: [
      v => !!v || 'E-mail obrigatório!',
      v => /.+@.+.+\..+/.test(v) || 'E-mail inválido válido!'
    ],
    passwordRules: [
      v => !!v || 'Senha obrigatória!',
      v => v.length >= 3 || 'Senha deve conter no mínimo 3 caracteres'
    ]
  }),
  methods: {
    findByEmailAndPassword () {
      let error = []
      error = this.$v_user.email(this.email)
      if (error) {
        this.msgErro = [...error]
      }
      error = this.$v_user.password(this.password)
      if (error) {
        this.msgErro = [...this.msgErro, ...error]
      }
      if (this.msgErro.length > 0) {
        this.isErro = true
        this.msgCor = 'warning'
      } else {
        let user = {
          email: this.email,
          login: {
            password: this.password
          }
        }
        this.$_axios.post(`${this.$_url}UsuarioLogin`, user).then((response) => {
          let result = response.data
          if (result.resultList.length !== 0) {
            this.user = response.data
            this.isErro = true
            this.msgCor = 'info'
            this.msgErro = [`Bem vindo ${this.user.login.nomeLgin}.`]
            this.$emit('login', this.user.login)
          } else if (result.mensagem) {
            this.isErro = true
            this.msgCor = 'warning'
            this.msgErro = [...result.mensagem]
          } else {
            /* this.isErro = true
            this.msgCor = 'warning'
            this.msgErro = [`Usuário ${user.login.nomeLogin} não encontrado!`] */

            /* USER MOCK */
            this.user = {
              login: {
                nomeLogin: 'wesley',
                password: '123',
                ativo: true,
                dataCriacao: 'null'
              },
              roles: [
                {name: 'ADMIN'}
              ]
            }
            this.msgCor = 'info'
            this.msgErro = [`Bem vindo ${this.user.login.nomeLgin}.`]
            this.$emit('emittedUsuario', this.user)
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

