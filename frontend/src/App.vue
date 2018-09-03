<template>
  <v-app id="inspire">
    <v-navigation-drawer
        v-model="drawer"
        :clipped="clipped"
        fixed
        app >
      <v-list dense>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>home</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Login">
              <v-list-tile-title>Home</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <admin-role v-if="isAdminAtivo"></admin-role>
        <user-role v-else-if="isUsuarioAtivo"></user-role>

      </v-list>
    </v-navigation-drawer>

    <v-toolbar dense dark fixed app class="elevation-10">
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
      <v-container fluid>
        <v-slide-y-transition mode="out-in">
            <v-flex xs12 sm10 offset-sm1 pa-5>
              <router-view @emittedUser="setUsuarioLogado" />
            </v-flex>
        </v-slide-y-transition>
      </v-container>
    </v-content>

    <v-footer :fixed="fixed" app dark>
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
      lblLogin: 'LOGIN',
      isAdminAtivo: false,
      isUsuarioAtivo: false,
      title: 'Apontamento de Horas',
      fixed: false,
      login: [],
      notifications: '',
      account_circle: '',
      avatar: '',
      roles: [],
      drawer: false,
      clipped: true
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
li {
  list-style: none
}
</style>
