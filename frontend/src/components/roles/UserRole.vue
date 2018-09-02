<template>
  <v-app>

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>contact_mail</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="PaginaInicial">
          <v-list-tile-title>Página inícial</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>
    
  </v-app>
</template>

<script>
  export default {
    data: function () {
      return {
      }
    }
  }
</script>
