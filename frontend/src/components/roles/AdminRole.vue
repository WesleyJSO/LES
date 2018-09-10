<template>
  <v-app>

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>group</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="CadastrarUsuario">
          <v-list-tile-title>Cadastro de usuários</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>

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

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>group</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Apontamentos">
          <v-list-tile-title>Realizar apontamentos</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>group</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Solicitacao">
          <v-list-tile-title>Solicitar abono/falta</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>
    

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>group</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Grupos">
          <v-list-tile-title>Grupos</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>


    <v-list-tile>
      <v-list-tile-action>
        <v-icon>check</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="VisualizarSolicitacao">
          <v-list-tile-title>Solicitações</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>
    
    <v-list-tile>
      <v-list-tile-action>
        <v-icon>next_week</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="CadastrarEmpresa">
          <v-list-tile-title>Dados Empresariais</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>assignment</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Parametros">
          <v-list-tile-title>Parametros do Sistema</v-list-tile-title>
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