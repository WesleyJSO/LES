<template>
    <v-card>
      <v-card-title
      class="headline grey lighten-2"
      primary-title
      >
      {{ type }}
      </v-card-title>

      <v-flex xs12 sm9 md6 lg6 xl4>
        <v-text-field type="time"
          v-model="editedValue"
          prepend-icon="timer"
          label="Hora">
        </v-text-field>
      </v-flex>

      <v-divider></v-divider>

      <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
          color="red"
          flat
          @click="appoint('close')">
          Cancelar
      </v-btn>
      <v-btn
          color="green"
          flat
          @click="appoint('appoint')">
          Apontar
      </v-btn>
      </v-card-actions>
    </v-card>
</template>

<script>
export default {
  props: ['type', 'value'],
  data: () => ({
    dialog: false,
    editedValue: ''
  }),
  created () {
    this.editedValue = this.value
  },
  methods: {
    appoint (action) {
      if (action === 'close' || this.editedValue === '--:--') {
        this.$emit('editAppoint', false)
      } else {
        this.$emit('editAppoint', this.editedValue)
      }
    },
    close () {
      this.$emit('closeIt')
    }
  }
}
</script>
