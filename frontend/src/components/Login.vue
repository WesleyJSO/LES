<template>
  <div>
    <v-flex xs12 sm8 offset-sm2 pa-5>
      <v-card class="elevation-5">

        <v-toolbar dark>
          <v-toolbar-title>Login</v-toolbar-title>
        </v-toolbar>
          <v-card-title v-if="dialog" class="pa-0 ma-0">
            <v-progress-linear
              background-color="black"
              color="grey" height="5"
              :indeterminate="dialog"
            ></v-progress-linear>
          </v-card-title>
          <li v-for="(msg, index) in messages" :key="index">
            <v-alert
                :value="haveMessage"
                :color="msgColor"
                v-text="msg"
                transition="scale-transition" />
          </li>
        <v-form ref="form" v-model="valid">
          <v-card-text>

            <v-text-field id="email"
                prepend-icon="person"
                v-model="user.email"
                :rules="$v_user.emailRules(user.email)"
                label="E-mail"
                required />
            <v-text-field id="password"
                prepend-icon="lock"
                v-model="user.password"
                :rules="$v_user.passwordRules(user.password)"
                label="Senha"
                type="password"
                required />
          </v-card-text>

          <v-card-actions>
            <v-btn id="sendEmail"
              color="blue"
              depressed flat
              @click="sendForgotPasswordEmail()">Esqueci a senha</v-btn>
            <v-spacer />
            <v-btn id="submit"
              color="info"
              :disabled="!valid"
              @click="login">Login</v-btn>
          </v-card-actions>
          <Loader :load="dialog" />
        </v-form>
      </v-card>
    </v-flex>
  </div>
</template>

<script>
import Authenticator from '@/service/Authenticator'
export default {
  data: () => ({
    dialog: false,
    user: {},
    valid: false,
    haveMessage: false,
    messages: [],
    msgColor: ''
  }),
  created () {
    this.user.email = 'wesley@wesley.com'
    this.user.password = '1234'
  },
  methods: {
    hasRole (role) {
      return Authenticator.HAS_ROLE(role)
    },
    sendForgotPasswordEmail () {
      this.messages = []
      this.haveMessage = false
      if (this.validateEmail()) {
        this.$_axios.patch(`${this.$_url}user`, {params: {email: this.user.email}}).then(response => {
          let result = response.data
          if (result.resultList.length !== 0) {
            this.haveMessage = true
            this.msgColor = 'info'
            this.messages = [`Nova senha de acesso enviada para o e-mail ${this.user.email}!`]
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
    async login () {
      this.valid = false
      this.dialog = true
      this.messages = []
      this.haveMessage = false
      try {
        let response = await this.$_axios.post(`${this.$_url}login`, {email: this.user.email, password: this.user.password})
        let result = response.data
        if (result.message) {
          this.messages = result.message
          this.haveMessage = true
          if (result.success) {
            this.companyList.splice(this.deleteIndex, 1)
            this.messageColor = 'info'
          } else {
            this.messageColor = 'warning'
          }
        }
        if (this.hasRole('ROLE_EMPLOYEE')) {
          this.$router.push('/LinhaDoTempo')
        } else {
          this.$router.push('/dashboard')
        }
        this.$emit('login', this.user.email)
      } catch (err) {
        if (err.response.status === 403 || err.response.status === 401) {
          this.messages = ['Usuário ou Senha Inválidos!!']
        } else {
          this.messages = ['Erro durante execução do serviço!']
        }
        this.valid = true
        this.dialog = false
        console.log(JSON.stringify(err, null, ''))
        localStorage.removeItem('user-token')
        this.$router.push('/Login')
        this.messageColor = 'error'
        this.haveMessage = true
      }
    },
    clearForm () {
      this.$refs.form.reset()
    }
  }
}
</script>

