<template>
    <v-card>
      <v-card-title
      class="headline grey lighten-2"
      primary-title
      >
      {{ type }}
      </v-card-title>

      <v-flex xs12 sm9 md6 lg6 xl4>
        <v-form v-model="valid">
          <v-text-field type="time"
            v-model="value"
            prepend-icon="timer"
            label="Hora">
          </v-text-field>
        </v-form>
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
          :disabled="!valid"
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
  props: {
    value: '',
    type: '',
    appointment: {}
  },
  data: () => ({
    dialog: false,
    valid: false
  }),
  methods: {
    appoint (action) {
      if (action === 'close') {
        this.$emit('editAppoint', false)
      } else {
        this.$emit('replacementValue', this.value)
        if (this.type === 'Entrada Manhã') {
          this.appointment.morningEntrance = this.value
        } else if (this.type === 'Saída Manhã') {
          this.appointment.morningOut = this.value
        } else if (this.type === 'Entrada Tarde') {
          this.appointment.afternoonEntrance = this.value
        } else if (this.type === 'Saída Tarde') {
          if (!this.appointment.morningOut) {
            this.appointment.morningOut = '12:00'
            this.appointment.afternoonEntrance = '12:00'
          }
          this.appointment.afternoonOut = this.value
        } else if (this.type === 'Entrada Noite') {
          this.appointment.nightEntrance = this.value
        } else if (this.type === 'Saída Noite') {
          if (!this.appointment.morningOut && this.appointment.morningEntrance) {
            this.appointment.morningOut = '12:00'
            this.appointment.afternoonEntrance = '12:00'
          }
          if (!this.appointment.afternoonOut && !this.appointment.nightEntrance) {
            this.appointment.afternoonOut = '18:00'
            this.appointment.nightEntrance = '18:00'
          }
          this.appointment.nightOut = this.value
        } else if (this.type === 'Saída Partícular') {
          this.appointment.particularExit = this.value
        } else if (this.type === 'Retorno') {
          this.appointment.particularExitReturn = this.value
        }
        this.$emit('editAppoint', this.appointment)
      }
    },
    close () {
      this.$emit('closeIt')
    }
  }
}
</script>
