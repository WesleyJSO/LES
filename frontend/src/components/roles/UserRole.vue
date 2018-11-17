<template>
  <div>
    <v-navigation-drawer
      fixed clipped
      class="grey lighten-4"
      app
    >
      <v-list dense>
        <v-list-tile>
          <v-list-tile-action>
            <v-icon>home</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="LinhaDoTempo">
              <v-list-tile-title>Home</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>touch_app</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Apontamentos">
              <v-list-tile-title>Realizar apontamentos</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>assignment</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Solicitacao">
              <v-list-tile-title>Realizar Solicitações</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>assignment_turned_in</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Solicitacoes">
              <v-list-tile-title>Minhas Solicitações</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>dns</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="GerenciarBancoHoras">
              <v-list-tile-title>Banco de Horas</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>timeline</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="LinhaDoTempo">
              <v-list-tile-title>Linha do Tempo</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>event</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Feriados">
              <v-list-tile-title>Feriados</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
  import Authenticator from '@/service/Authenticator'
  export default {
    data: function () {
      return {
      }
    },
    methods: {
      hasRole (role) {
        return Authenticator.HAS_ROLE(role)
      }
    }
  }
</script>
