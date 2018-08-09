<template>
  <v-app>

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>contact_mail</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="ConsultarListar">
          <v-list-tile-title>Consulta de usuários</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>

  </v-app>
</template>

<script>
  export default {
    data: function () {
      return {
        ativo: false
      }
    }
  }
</script>
