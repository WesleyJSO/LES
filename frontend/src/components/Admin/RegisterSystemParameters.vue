<template>
    <div>
    <h1>Parametros do Sistema</h1>
    <h2>Os dados inseridos abaixo influenciarão no funcionamento do sistema</h2>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <v-stepper v-model="step">
    <v-stepper-header>
      <v-stepper-step :complete="step > 1" step="1">Adicionais de Hora Extra</v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step :complete="step > 2" step="2">Prazos de Ações</v-stepper-step>

      <v-divider></v-divider>

      <v-stepper-step step="3">Tipos de Hora</v-stepper-step>
    </v-stepper-header>

    <v-stepper-items>
      <v-stepper-content step="1">
        <v-card
          class="mb-5">
          <h3>Os adicionais de hora extra serão utilizados como base de calculo das horas excedentes dos funcionarios</h3>
          <br/>
          <v-layout>
          <!-- Row 1 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="parameters.overtimePercentage"
                    type="number"
                    class="px-0"
                    prepend-icon="looks_one"
                    clearable
                    suffix="%"
                    label="Adicional hora extra">
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="parameters.nightOvertimePercentage"
                    type="number"
                    prepend-icon="looks_two"
                    clearable
                    suffix="%"
                    label="Adicional noturno *">
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 3 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="parameters.weekEndOvertimePercentage"
                    type="number"
                    prepend-icon="looks_3"
                    clearable
                    suffix="%"
                    label="Adicional domingo e feriado *">
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
            <v-flex class="text-xs-right">
              <p>*Porcentagens serão aplicadas sobre o valor da hora extra calculado.</p>
            </v-flex>
          </v-layout>
        </v-card>

        <v-flex class="text-xs-center">
          <v-btn
            color="primary"
            @click="nextStep()">
            Próximo
          </v-btn>
        </v-flex>
      </v-stepper-content>

      <v-stepper-content step="2">
        <v-card
          class="mb-5">
          <h3>Os prazos de ações abaixo serão utilizados para limitação de ações dos usuários do sistema</h3>
          <br/>
          <v-layout>
          <!-- Row 1 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="parameters.retroactiveAppointmentLimitTime"
                    type="time"
                    class="px-0"
                    prepend-icon="looks_one"
                    clearable
                    suffix="horas"
                    label="Prazo limite para apontamentos retroativos">
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="parameters.relocationRequestLimitTime"
                    type="number"
                    prepend-icon="looks_two"
                    clearable
                    suffix="horas"
                    label="Prazo limite para solicitação de remanejamento">
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 3 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="parameters.bankCompensationLimitTime"
                    type="number"
                    prepend-icon="looks_3"
                    clearable
                    suffix="meses"
                    label="Prazo limite para compensação do banco de horas">
              </v-text-field>
            </v-flex>
          </v-layout>
        </v-card>

        <v-flex class="text-xs-center">
          <v-btn
            flat
            @click="backStep()">
            Voltar
          </v-btn>
          <v-btn
            color="primary"
            @click="nextStep()">
            Próximo
          </v-btn>
        </v-flex>
      </v-stepper-content>

      <v-stepper-content step="3">
        <v-card
          class="mb-5">
          <h2>Para o tipo {{ hourTypes[0] }}</h2>
          <br/>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-checkbox
                :label="`Não aplicar limite`"
                v-model="checkbox1"
                @change="verifyCheckboxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="checkbox1"
                    v-model="parameters.overTime.quantityFirst"
                    type="number"
                    prepend-icon="looks_one"
                    clearable
                    suffix="hrs"
                    label="Limite diário de horas extras">
              </v-text-field>
            </v-flex>
          </v-layout>
        </v-card>
        <v-card
          class="mb-5">
          <h2>Para o tipo {{ hourTypes[1] }}</h2>
          <br/>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-checkbox
                :label="`Não aplicar limite`"
                v-model="checkbox2"
                @change="verifyCheckboxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="checkbox2"
                    v-model="parameters.compTime.quantityFirst"
                    type="number"
                    prepend-icon="looks_one"
                    clearable
                    suffix="hrs"
                    label="Limite diário de banco de horas">
              </v-text-field>
            </v-flex>
          </v-layout>
        </v-card>
        <v-card
          class="mb-5">
          <h2>Para o tipo {{ hourTypes[2] }}</h2>
          <br/>
          <v-layout>
          <!-- Row 4 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <span>Qual tipo deve ser aplicado primeiro?</span>
              <v-radio-group
                            v-model="radio"
                            :mandatory="false"
                            @change="changeRadio">
                            <v-radio label="Hora Extra" value="Hora Extra"></v-radio>
                            <v-radio label="Banco de Horas" value="Banco de Horas"></v-radio>
              </v-radio-group>
            </v-flex>
          </v-layout>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs11 sm8 md5 lg5 xl3>
              <v-checkbox
                :disabled="blockCheckbox3"
                :label="`Não aplicar limite`"
                v-model="checkbox3"
                @change="verifyCheckboxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="checkbox3"
                    v-model="overtimeTypeLimit"
                    type="number"
                    prepend-icon="looks_one"
                    clearable
                    suffix="hrs"
                    label="Limite diário de horas extras">
              </v-text-field>
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs11 sm8 md5 lg5 xl3>
              <v-checkbox
                :disabled="blockCheckbox4"
                :label="`Não aplicar limite`"
                v-model="checkbox4"
                @change="verifyCheckboxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="checkbox4"
                    v-model="bankOfHoursTypeLimit"
                    type="number"
                    prepend-icon="looks_two"
                    clearable
                    suffix="hrs"
                    label="Limite diário de banco de horas">
              </v-text-field>
            </v-flex>
          </v-layout>
        </v-card>

        <v-flex class="text-xs-center">
          <v-btn
            flat
            @click="backStep()">
            Voltar
          </v-btn>
          <v-btn large
                type="submit"
                color="success"
                @click.prevent="saveParameters">
                Salvar
          </v-btn>
        </v-flex>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
  </div>
</template>

<script>
import DateHelper from '../../helpers/DateHelper'

export default {
  props: ['parameter'],
  data: () => ({
    parameterDate: '',
    step: 1,
    parameters: {},
    haveMessage: false,
    messages: [],
    messageColor: '',
    // hour type
    hourTypes: [
      'Hora Extra',
      'Banco de Horas',
      'Hora Extra e Banco de Horas'
    ],
    overtimeTypeLimit: null,
    bankOfHoursTypeLimit: null,
    radio: 'Hora Extra',
    type: 'Hora Extra',
    checkbox1: false,
    checkbox2: false,
    checkbox3: false,
    checkbox4: false,
    blockCheckbox3: false,
    blockCheckbox4: false,
    dialog: false,
    blockDialog: false
  }),
  created () {
    this.$vuetify.goTo(0)
  },
  computed: {
    color () { return ['error', 'warning', 'success'][Math.floor(this.progress() / 40)] },
    colorValidation () { return ['error', 'warning', 'success'][Math.floor(this.progressValidation() / 40)] }
  },
  watch: {
  },
  beforeMount () {
    if (this.parameter) {
      this.parameters = this.parameter
      console.log(JSON.stringify(this.parameters))
      if (!this.parameters.overTime) {
        this.parameters.overTime = {
          both: false,
          first: 'Hora Extra',
          quantityFirst: null,
          quantitySecond: null
        }
      }
      if (!this.parameters.compTime) {
        this.parameters.compTime = {
          both: false,
          first: 'Banco de Horas',
          quantityFirst: null,
          quantitySecond: null
        }
      }
      if (!this.parameters.bothTypes) {
        this.parameters.bothTypes = {
          both: true,
          first: 'Hora Extra',
          quantityFirst: null,
          quantitySecond: null
        }
      }
      if (this.parameters.bothTypes.first === 'Hora Extra') {
        this.overtimeTypeLimit = this.parameters.bothTypes.quantityFirst
        this.bankOfHoursTypeLimit = this.parameters.bothTypes.quantitySecond
      } else if (this.parameters.bothTypes.first === 'Banco de Horas') {
        this.bankOfHoursTypeLimit = this.parameters.bothTypes.quantityFirst
        this.overtimeTypeLimit = this.parameters.bothTypes.quantitySecond
      }
      if (!this.overtimeTypeLimit) {
        this.checkbox3 = true
      }
      if (!this.bankOfHoursTypeLimit) {
        this.checkbox4 = true
      }
    }
    if (this.parameters.creationDate) {
      let date = this.parameters.creationDate.substring(0, 10).split('/')
      date = `${date[2]}/${date[1]}/${date[0]}`
      this.parameterDate = date
    }
    console.log(JSON.stringify(this.parameters))
    this.verifyFields()
  },
  methods: {
    changeRadio () {
      if (this.radio === 'Hora Extra') {
        this.checkbox3 = false
        this.blockCheckbox3 = true
        this.blockCheckbox4 = false
      } else if (this.radio === 'Banco de Horas') {
        this.checkbox4 = false
        this.blockCheckbox3 = false
        this.blockCheckbox4 = true
      }
      this.verifyCheckboxes()
    },
    verifyFields () {
      if (!this.parameters.overTime.quantityFirst) {
        this.checkbox1 = true
      }
      if (!this.parameters.compTime.quantityFirst) {
        this.checkbox2 = true
      }
      if (!this.parameters.bothTypes.first === 'Hora Extra') {
        if (!this.parameters.bothTypes.quantityFirst) {
          this.checkbox3 = true
        }
        if (!this.parameters.bothTypes.quantitySecond) {
          this.checkbox4 = true
        }
      } else if (!this.parameters.bothTypes.first === 'Banco de Horas') {
        if (!this.parameters.bothTypes.quantityFirst) {
          this.checkbox4 = true
        }
        if (!this.parameters.bothTypes.quantitySecond) {
          this.checkbox3 = true
        }
      }
      this.radio = this.parameters.bothTypes.first
      this.changeRadio()
    },
    nextStep () {
      if (this.step === 2 && this.validateDeadlines()) {
        this.step += 1
      }
      if (this.step === 1 && this.validatePercentages()) {
        this.step += 1
      }
    },
    backStep () {
      this.step -= 1
    },
    verifyCheckboxes () {
      if (this.checkbox1) {
        this.parameters.overTime.quantityFirst = null
      }
      if (this.checkbox2) {
        this.parameters.compTime.quantityFirst = null
      }
      if (this.checkbox3) {
        this.overtimeTypeLimit = null
      }
      if (this.checkbox4) {
        this.bankOfHoursTypeLimit = null
      }
    },
    validateDeadlines () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_parameters.retroactiveAppointmentRules(this.parameters.retroactiveAppointmentLimitTime)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.relocationRequestRules(this.parameters.relocationRequestLimitTime)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.bankCompensationRules(this.parameters.bankCompensationLimitTime)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      this.$vuetify.goTo(0)
      if (this.messages.length === 0) {
        return true
      } else {
        this.haveMessage = true
        this.messageColor = 'warning'
        return false
      }
    },
    validatePercentages () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_parameters.overtimeRules(this.parameters.overtimePercentage)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.nightOvertimeRules(this.parameters.nightOvertimePercentage)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.weekEndOvertimeRules(this.parameters.weekEndOvertimePercentage)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      this.$vuetify.goTo(0)
      if (this.messages.length === 0) {
        return true
      } else {
        this.haveMessage = true
        this.messageColor = 'warning'
        return false
      }
    },
    validateHourType () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_parameters.hoursLimitRules(this.parameters.overTime.first, this.checkbox1, this.parameters.overTime.quantityFirst)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.hoursLimitRules(this.parameters.compTime.first, this.checkbox2, this.parameters.compTime.quantityFirst)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.overtimeTypeLimitRules(this.checkbox3, this.overtimeTypeLimit)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.bankOfHoursTypeLimitRules(this.checkbox4, this.bankOfHoursTypeLimit)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      if (this.messages.length === 0) {
        return true
      } else if (this.messages.length <= 3) {
        this.haveMessage = true
        this.messageColor = 'warning'
        return false
      }
    },
    async callApi () {
      var response = null
      var result = null
      if (this.parameter.creationDate) {
        if (DateHelper.diffDates(new Date(this.parameterDate), new Date()) > 0) {
          try {
            response = await this.$_axios.put(`${this.$_url}parameter`, this.parameter)
            result = response.data
            if (result.resultList.length !== 0) {
              // retorno ok /
              this.parameters = result.resultList
            }
            if (result.message) {
              this.messages = [...result.message]
              this.haveMessage = true
              if (result.success) {
              // retorno mensagem de sucesso /
                this.messageColor = 'info'
              } else {
                // retorno mensagem de erro /
                this.messageColor = 'warning'
              }
            }
            response = await this.$_axios.post(`${this.$_url}parameter`, this.parameters)
            result = response.data
            if (result.resultList.length !== 0) {
              // retorno ok /
              this.parameters = result.resultList
              this.$router.push('/Parametros')
            }
            if (result.message) {
              this.messages = [...result.message]
              this.haveMessage = true
              if (result.success) {
              // retorno mensagem de sucesso /
                this.messageColor = 'info'
              } else {
                // retorno mensagem de erro /
                this.messageColor = 'warning'
              }
            }
          } catch (err) {
            this.messages = ['Erro durante execução do serviço!']
            this.haveMessage = true
            this.messageColor = 'error'
            console.log(JSON.stringify(err))
          }
        } else {
          try {
            response = await this.$_axios.put(`${this.$_url}parameter`, this.parameters)
            result = response.data
            if (result.resultList.length !== 0) {
              // retorno ok /
              this.parameters = result.resultList
              this.$router.push('/Parametros')
            }
            if (result.message) {
              this.messages = [...result.message]
              this.haveMessage = true
              if (result.success) {
              // retorno mensagem de sucesso /
                this.messageColor = 'info'
              } else {
                // retorno mensagem de erro /
                this.messageColor = 'warning'
              }
            }
          } catch (err) {
            this.messages = ['Erro durante execução do serviço!']
            this.haveMessage = true
            this.messageColor = 'error'
            console.log(JSON.stringify(err))
          }
        }
      } else {
        try {
          response = await this.$_axios.post(`${this.$_url}parameter`, this.parameters)
          result = response.data
          this.$router.push('/Parametros')
          if (result.resultList.length !== 0) {
            // retorno ok /
            this.parameters = result.resultList
            this.$router.push('/Parametros')
          }
          if (result.message) {
            this.messages = [...result.message]
            this.haveMessage = true
            if (result.success) {
            // retorno mensagem de sucesso /
              this.messageColor = 'info'
            } else {
              // retorno mensagem de erro /
              this.messageColor = 'warning'
            }
          }
        } catch (err) {
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
          console.log(JSON.stringify(err))
        }
      }
    },
    saveParameters (event) {
      if (this.validateHourType()) {
        this.parameters.bothTypes.first = this.radio
        if (this.parameters.bothTypes.first === 'Hora Extra') {
          this.parameters.bothTypes.quantityFirst = this.overtimeTypeLimit
          this.parameters.bothTypes.quantitySecond = this.bankOfHoursTypeLimit
          this.parameters.bothTypes.second = 'Banco de Horas'
        } else {
          this.parameters.bothTypes.quantityFirst = this.bankOfHoursTypeLimit
          this.parameters.bothTypes.quantitySecond = this.overtimeTypeLimit
          this.parameters.bothTypes.second = 'Hora Extra'
        }
        this.callApi()
      }
      this.$vuetify.goTo(0)
    }
  }
}
</script>

<style scoped>
  h1 {
    font-size: 40px;
    text-align: center;
    margin-top: -20px;
    margin-bottom: 20px;
  }
</style>
