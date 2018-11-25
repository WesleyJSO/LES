<template>

      <v-list dense>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>home</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="dashboard">
              <v-list-tile-title>Home</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile v-if="this.hasRole(getRole.employee)">
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
            <v-icon>contact_mail</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Consultar">
              <v-list-tile-title>Consulta de usuários</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile v-if="this.hasRole(getRole.employee)">
          <v-list-tile-action>
            <v-icon>touch_app</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Apontamentos">
              <v-list-tile-title>Realizar Apontamentos</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile v-if="this.hasRole(getRole.employee)">
          <v-list-tile-action>
            <v-icon>assignment</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Solicitacao">
              <v-list-tile-title>Realizar Solicitações</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile  v-if="this.hasRole(getRole.employee)">
          <v-list-tile-action>
            <v-icon>assignment</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="MinhasSolicitacoes">
              <v-list-tile-title>Minhas Solicitações</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>assignment_turned_in</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Solicitacoes">
              <v-list-tile-title>Aprovar Solicitações</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>insert_chart</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="Graficos">
              <v-list-tile-title>Gráficos</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>dns</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="VisualizarApontamentos">
              <v-list-tile-title>Visualizar Apontamentos</v-list-tile-title>
            </router-link>
          </v-list-tile-content>
        </v-list-tile>

        <v-list-tile>
          <v-list-tile-action>
            <v-icon>date_range</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <router-link to="dashboard">
              <v-list-tile-title>Dashboard</v-list-tile-title>
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

</template>

<script>
  import Authenticator from '@/service/Authenticator'
  export default {
    data: function () {
      return {
      }
    },
    computed: {
      getRole () {
        return Authenticator.ROLE
      }
    },
    methods: {
      hasRole (role) {
        return Authenticator.HAS_ROLE(role)
      }
    }
  }
</script>
