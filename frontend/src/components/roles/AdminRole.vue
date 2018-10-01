<template>
  <v-app>

    <v-list-tile>
      <v-list-tile-action>
        <v-icon>account_box</v-icon>
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
        <router-link to="Consultar">
          <v-list-tile-title>Consulta de usuários</v-list-tile-title>
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
        <v-icon>group_add</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="CentroDeCustos">
          <v-list-tile-title>Centro de Custos</v-list-tile-title>
        </router-link>
      </v-list-tile-content>
    </v-list-tile>


    <v-list-tile>
      <v-list-tile-action>
        <v-icon>assignment_turned_in</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Solicitacoes">
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
        <v-icon>bar_chart</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Graficos">
          <v-list-tile-title>Gráficos</v-list-tile-title>
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
        <v-icon>touch_app</v-icon>
      </v-list-tile-action>
      <v-list-tile-content>
        <router-link to="Apontar">
          <v-list-tile-title>Apontamento Rápido</v-list-tile-title>
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
