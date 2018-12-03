<template>
  <v-app id="inspire">
    <v-navigation-drawer
      fixed app v-model="drawer"
      :clipped="$vuetify.breakpoint.mdAndUp">

      <admin-role v-if="this.hasRole('ROLE_ADMIN')"></admin-role>
      <user-role v-else-if="this.hasRole('ROLE_EMPLOYEE') && !this.hasRole('ROLE_MANAGER') && !this.hasRole('ROLE_ADMIN')"></user-role>
      <manager-role v-else-if="this.hasRole('ROLE_MANAGER')"></manager-role>

    </v-navigation-drawer>

    <v-toolbar dark dense app fixed
      :clipped-left="$vuetify.breakpoint.mdAndUp">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title v-text="title" />
      <v-icon slot="badge" dark small >{{ notifications }}</v-icon>
      <v-spacer></v-spacer> <!-- separete left and right -->
      <v-icon large color="blue lighten-2" >{{ account_circle }}</v-icon>
      <v-toolbar-title>{{ user }}</v-toolbar-title>
      <div class="hidden-sm-and-down">
        <v-btn @click.stop="logOut()" flat>{{this.lblLogin}}</v-btn>
      </div>
    </v-toolbar>

    <v-content>
      <v-container fluid fixed>
        <v-slide-y-transition mode="out-in">
            <v-flex md12>
              <router-view @login="setUsuarioLogado" />
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
import ManagerRole from '@/components/roles/ManagerRole'
import Authenticator from '@/service/Authenticator'
import router from './router'

export default {
  data () {
    return {
      drawer: false,
      lblLogin: 'LOGIN',
      isAdminAtivo: false,
      isUsuarioAtivo: false,
      title: 'SAH - Sistema de Apontamento de Horas',
      login: [],
      notifications: '',
      account_circle: '',
      avatar: '',
      roles: [],
      username: ''
    }
  },
  components: {
    AdminRole,
    UserRole,
    ManagerRole
  },
  computed: {
    user () {
      let user = this.username.split('@')[0]
      return user.replace(user.substring(0, 1), user.substring(0, 1).toUpperCase())
    }
  },
  methods: {
    logOut () {
      sessionStorage.removeItem('token')
      sessionStorage.removeItem('principal')
      this.drawer = false
      this.lblLogin = 'Login'
      this.account_circle = ''
      this.username = ''
      router.push('/')
    },
    hasRole (role) {
      return Authenticator.HAS_ROLE(role)
    },
    setUsuarioLogado (user) {
      this.lblLogin = 'LOGOUT'
      this.drawer = true
      this.account_circle = 'account_circle'
      this.username = user
    },
    logout () {
      this.lblLogin = 'LOGIN'
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
    font-size: 30px;
    text-align: center;
  }
  li { list-style: none; }
</style>

