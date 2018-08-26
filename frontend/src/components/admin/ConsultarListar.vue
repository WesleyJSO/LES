<template id="app">
    <v-app>
      <consultar></consultar>
      <listar></listar>
    </v-app>
</template>

<script>
import Consultar from '@/components/admin/Consultar'
import Listar from '@/components/admin/Listar'

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
