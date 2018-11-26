<template>
    <v-card>
      <v-card-title
      class="headline grey lighten-2"
      primary-title
      >
      {{ type }}
      </v-card-title>

      <v-flex xs12 sm9 md6 lg6 xl4 id="vFlex">
        <v-form
          v-model="valid">
          <v-text-field id="txtTime"
            type="time"
            v-model="newValue"
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
    valid: false,
    newValue: ''
  }),
  mounted () {
    this.newValue = this.value
  },
  watch: {
    value (value2) {
      this.newValue = value2
    }
  },
  methods: {
    appoint (action) {
      if (action === 'close') {
        this.$emit('editAppoint', false)
      } else {
        this.$emit('replacementValue', this.newValue)
        if (this.type === 'Entrada Manhã') {
          this.appointment.morningEntrance = this.newValue
        } else if (this.type === 'Saída Manhã') {
          this.appointment.morningOut = this.newValue
        } else if (this.type === 'Entrada Tarde') {
          this.appointment.afternoonEntrance = this.newValue
        } else if (this.type === 'Saída Tarde') {
          this.appointment.afternoonOut = this.newValue
        } else if (this.type === 'Entrada Noite') {
          this.appointment.nightEntrance = this.newValue
        } else if (this.type === 'Saída Noite') {
          this.appointment.nightOut = this.newValue
        } else if (this.type === 'Saída Partícular') {
          this.appointment.particularExit = this.newValue
        } else if (this.type === 'Retorno') {
          this.appointment.particularExitReturn = this.newValue
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
