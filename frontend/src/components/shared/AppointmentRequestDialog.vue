<template>
  <div class="text-xs-center">
    <v-dialog v-model="dialogRequest"
      :width="getWidth">
      <v-card>
        <v-card-title
          class="headline grey lighten-2"
          primary-title>
          {{ getTitle }}
        </v-card-title>

        <v-card-text>
          <b>{{getMessages.atention}}</b>
          <br/>
          {{ getText }} <br/><br/>
          <b>{{getLabels.values}}</b>
          <br/>
          {{getFieldToChangeName[appointmentRequest.fieldToChange]}} - <b>{{getPreviousValue()}}</b> <b>{{getReplacement()}}</b>
        </v-card-text>

        <v-card-text>
          <b>{{ getLabels.requestDescription }}</b>
          <v-textarea v-model="description"
              box
              outline
              name="input-7-4">
          </v-textarea>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="green"
            flat
            @click="confirmRequest">
            {{ getButtons.confirm }}
          </v-btn>
          <v-btn
            color="red"
            flat
            @click="deleteRequest">
            {{ getButtons.cancel }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
  import UserService from '@/service/UserService'
  export default {
    props: {
      item: Object,
      width: Number,
      height: Number,
      title: String,
      text: String,
      dialog: {
        type: Boolean,
        default: false
      }
    },
    data: () => ({
      observation: '',
      appointmentRequest: {},
      dialogRequest: false,
      description: ''
    }),
    computed: {
      getTitle () {
        return this.title || UserService.TITLES.default
      },
      getText () {
        return this.text || this.getMessages.default
      },
      getMessages () {
        return UserService.MESSAGES
      },
      getLabels () {
        return UserService.LABELS
      },
      getWidth () {
        return this.width || 500
      },
      getHeight () {
        return this.height || 400
      },
      getButtons () {
        return UserService.BUTTONS
      },
      getFieldToChangeName () {
        return UserService.FIELD_TO_CHANGE
      },
      getMomentFormats () {
        return UserService.MOMENT_FORMATS
      }
    },
    watch: {
      item (value) {
        this.appointmentRequest = Object.assign(value, {})
      },
      dialog (value) {
        this.dialogRequest = value
      },
      dialogRequest () {
        this.$emit('close', false)
      }
    },
    methods: {
      async deleteRequest () {
        try {
          let result = await this.$_axios.delete(`${this.$_url}appointmentRequest/${this.appointmentRequest.id}`)
          if (result.data.success) {
            this.$emit('deleteRequest', result.data)
          }
        } catch (error) {
          console.log(error)
          this.$emit('requestError', error)
        }
        this.description = ''
      },
      async confirmRequest () {
        try {
          this.appointmentRequest.description = this.description ? this.description : this.appointmentRequest.description
          let result = await this.$_axios.put(`${this.$_url}appointmentRequest`, this.appointmentRequest)
          if (result.data.success) {
            this.$emit('confirmRequest', result.data)
          }
        } catch (error) {
          console.log(error)
          this.$emit('requestError', error)
        }
        this.description = ''
      },
      getPreviousValue () {
        return this.appointmentRequest.previousValue ? `de ${this.appointmentRequest.previousValue.substring(0, 5)}` : ''
      },
      getReplacement () {
        if (this.appointmentRequest.previousValue) {
          return `para ${this.appointmentRequest.replacement.substring(0, 5)}`
        } else if (this.appointmentRequest.replacement) {
          return this.appointmentRequest.replacement.substring(0, 5)
        } else {
          return ''
        }
      }
    }
  }
</script>