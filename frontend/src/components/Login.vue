<template>
  <v-app>
    
    <v-flex xs12 sm8 offset-sm2 pa-5>
      <v-card class="elevation-10">

        <v-toolbar dark>
          <v-toolbar-title>Login</v-toolbar-title>
        </v-toolbar>

          <li v-for="(msg, index) in messages" :key="index">
            <v-alert
                :value="haveMessage"
                :color="msgColor"
                v-text="msg"
                transition="scale-transition" />
          </li>
        <v-form v-model="valid" @submit.prevent="findByEmailAndPassword">

          <v-card-text>
            <v-text-field
                prepend-icon="person"
                v-model="email"
                :rules="emailRules"
                label="E-mail"
                required />
            <v-text-field
                prepend-icon="lock"
                v-model="password"
                :rules="passwordRules"
                label="Senha"
                type="password"
                required />
          </v-card-text>

          <v-card-actions>
            <v-btn color="blue" depressed flat @click="sentForgotPasswordEmail()">Esqueci a senha</v-btn>
            <v-spacer />
            <v-btn color="info" type="submit">Login</v-btn>
          </v-card-actions>

        </v-form>
      </v-card>
    </v-flex>
  </v-app>
</template>

<script>
export default {
  data: () => ({
    login: [],
    user: [],
    valid: false,
    email: '',
    password: '',
    haveMessage: false,
    messages: [],
    msgColor: '',
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
    validateEmailPassword () {
      let error = []
      error = this.$v_user.emailRules(this.email)
      if (error) {
        this.messages = [...error]
      }
      error = this.$v_user.passwordRules(this.password)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      if (this.messages.length > 0) {
        this.haveMessage = true
        this.msgColor = 'warning'
        return false
      } else {
        return true
      }
    },
    validateEmail () {
      let error = []
      this.messages = []
      this.haveMessage = false
      error = this.$v_user.email(this.email)
      if (error) {
        this.messages = [...error]
      }
      if (this.messages.length > 0) {
        this.haveMessage = true
        this.msgColor = 'warning'
        return false
      } else {
        return true
      }
    },
    sentForgotPasswordEmail () {
      this.messages = []
      this.haveMessage = false
      if (this.validateEmail()) {
        this.$_axios.get(`${this.$_url}UsuarioEmail`, {params: {email: this.email}}).then(response => {
          let result = response.data
          if (result.resultList.length !== 0) {
            this.haveMessage = true
            this.msgColor = 'info'
            this.messages = [`Nova senha de acesso enviada para o e-mail ${this.email}!`]
          } else {
            this.haveMessage = true
            this.msgColor = 'warning'
            this.messages = ['Não existe usuário para o e-mail informado!']
          }
          if (result.message) {
            this.haveMessage = true
            this.messages = [...result.message]
            if (result.success) {
              this.msgColor = 'info'
            } else {
              this.msgColor = 'warning'
            }
          }
        },
        response => {
          this.haveMessage = true
          this.msgColor = 'error'
          this.messages = ['Erro durante envio de e-mail']
        })
      }
    },
    findByEmailAndPassword () {
      this.messages = []
      this.haveMessage = false
      if (this.validateEmailPassword()) {
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
            this.haveMessage = true
            this.msgColor = 'info'
            this.messages = [`Bem vindo ${this.user.login.nomeLgin}.`]
            this.$emit('login', this.user.login)
          } else if (result.mensagem) {
            this.haveMessage = true
            this.msgColor = 'warning'
            this.messages = [...result.mensagem]
          } else {
            /* this.haveMessage = true
            this.msgColor = 'warning'
            this.messages = [`Usuário ${user.login.nomeLogin} não encontrado!`] */

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
            this.msgColor = 'info'
            this.messages = [`Bem vindo ${this.user.login.nomeLgin}.`]
            this.$emit('emittedUser', this.user)
          }
        },
        response => {
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
        })
      }
    }
  }
}
</script>

