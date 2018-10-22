<template>
  <v-app id="inspire">
    <v-navigation-drawer
      fixed app v-model="drawer"
      :clipped="$vuetify.breakpoint.mdAndUp"
    >
      <admin-role v-if="true"></admin-role>
      <user-role v-else-if="isUsuarioAtivo"></user-role>
    </v-navigation-drawer>

    <v-toolbar dark dense app fixed
      :clipped-left="$vuetify.breakpoint.mdAndUp">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title v-text="title" />
      <v-toolbar-title>{{ login.nomeLogin }}</v-toolbar-title>
      <v-icon slot="badge" dark small >{{ notifications }}</v-icon>
      <v-icon large color="blue lighten-2" >{{ account_circle }}</v-icon>
      <v-spacer></v-spacer> <!-- separete left and right -->
      <div class="hidden-sm-and-down">
        <v-btn v-if="lblLogin == 'LOGIN'" flat>{{ lblLogin }}</v-btn>
        <v-btn v-else-if="lblLogin == 'LOGOUT'" @click="logout" flat>{{ lblLogin }}</v-btn>
      </div>
    </v-toolbar>

    <v-content>
      <v-container fluid fixed="fixed">
        <v-slide-y-transition mode="out-in">
            <v-flex xs12 sm10 offset-sm1 pa-5>
              <router-view @emittedUser="setUsuarioLogado" />
            </v-flex>
        </v-slide-y-transition>
      </v-container>
    </v-content>

    <v-footer fixed="fixed" app dark>
      <span>&copy; LES 2018 - 2º semestre</span>
    </v-footer>

  </v-app>
</template>

<script>
import AdminRole from '@/components/roles/AdminRole'
import UserRole from '@/components/roles/UserRole'

export default {
  data () {
    return {
      drawer: false,
      lblLogin: 'LOGIN',
      isAdminAtivo: false,
      isUsuarioAtivo: false,
      title: 'Apontamento de Horas',
      login: [],
      notifications: '',
      account_circle: '',
      avatar: '',
      roles: []
    }
  },
  components: {
    AdminRole,
    UserRole
  },
  methods: {
    setUsuarioLogado (user) {
      this.lblLogin = 'LOGOUT'
      this.drawer = true
      this.login = user.login
      this.roles = user.roles
      this.account_circle = 'account_circle'
      this.roles.forEach(role => {
        if (role.name.toUpperCase() === 'ADMIN') {
          this.isAdminAtivo = true
          this.isUsuarioAtivo = false
        } else if (role.nome.toUpperCase() === 'USUARIO') {
          this.isAdminAtivo = false
          this.isUsuarioAtivo = true
        }
      })
    },
    logout () {
      this.lblLogin = 'LOGIN'
      this.isAdminAtivo = false
      this.isUsuarioAtivo = false
      this.title = 'LES'
      this.fixed = false
      this.login = []
      this.notifications = ''
      this.account_circle = ''
      this.avatar = ''
      this.roles = []
      this.drawer = false
      this.$router.go({path: '/Index'}) // não funciona quando está em outra tela que não seja a de login
    }
  },
  name: 'App'
}
</script>

<style>
  h1 {
    font-size: 40px;
    text-align: center;
    margin-top: -20px;
    margin-bottom: 20px;
  }
  li { list-style: none; }
</style>

