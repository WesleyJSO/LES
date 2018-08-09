<template id="app">
    <v-app>
      <consultar></consultar>
      <listar></listar>
    </v-app>
</template>

<script>
import Consultar from '@/components/usuario/Consultar'
import Listar from '@/components/usuario/Listar'

export default {
  components: {
    Consultar,
    Listar
  },
  data () {
    return {
    }
  }
}
</script>
