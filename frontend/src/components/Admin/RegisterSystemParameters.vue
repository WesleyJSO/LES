<template>
    <v-app>
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

      <v-stepper-step step="3">Tipo de Hora</v-stepper-step>
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
                    v-model="overtime"
                    type="number"
                    class="px-0"
                    prepend-icon="looks_one"
                    clearable
                    suffix="%"
                    :rules="$v_parameters.overtimeRules(overtime)"
                    label="Adicional hora extra"
                    required>
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="nightOvertime"
                    type="number"
                    prepend-icon="looks_two"
                    clearable
                    suffix="%"
                    :rules="$v_parameters.nightOvertimeRules(nightOvertime)"
                    label="Adicional noturno *"
                    required>
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 3 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="weekEndOvertime"
                    type="number"
                    prepend-icon="looks_3"
                    clearable
                    suffix="%"
                    :rules="$v_parameters.weekEndOvertimeRules(weekEndOvertime)"
                    label="Adicional domingo e feriado *"
                    required>
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
                    v-model="retroactiveAppointment"
                    type="number"
                    class="px-0"
                    prepend-icon="looks_one"
                    clearable
                    suffix="horas"
                    :rules="$v_parameters.retroactiveAppointmentRules(retroactiveAppointment)"
                    label="Prazo limite para apontamentos retroativos"
                    required>
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="relocationRequest"
                    type="number"
                    prepend-icon="looks_two"
                    clearable
                    suffix="horas"
                    :rules="$v_parameters.relocationRequestRules(relocationRequest)"
                    label="Prazo limite para solicitação de remanejamento"
                    required>
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout>
          <!-- Row 3 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-text-field
                    v-model="bankCompensation"
                    type="number"
                    prepend-icon="looks_3"
                    clearable
                    suffix="meses"
                    :rules="$v_parameters.bankCompensationRules(bankCompensation)"
                    label="Prazo limite para compensação do banco de horas"
                    required>
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
          <h3>O tipo de hora configurado será aplicado à todo usuário sem parametro próprio</h3>
          <br/>
          <v-layout>
          <!-- Row 1 -->
          <v-flex xs12 sm6 d-flex>
            <v-select
              v-model="type"
              :items="hourTypes"
              prepend-icon="query_builder"
              label="Modelo de Horas"
              :item-value="type"
              @change="verifyFields">
            </v-select>
          </v-flex>
          </v-layout>
          <br/>
          <v-layout v-if="bankOfHoursType || overtimeType">
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-checkbox
                :label="`Não aplicar limite`"
                v-model="checkbox1"
                @change="verifyCheckBoxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="blockHoursLimit"
                    v-model="hoursLimit"
                    type="number"
                    prepend-icon="looks_one"
                    clearable
                    suffix="horas"
                    label="Limite diário de horas">
              </v-text-field>
            </v-flex>
          </v-layout>
          <br/>
          <v-layout v-if="bothTypes">
          <!-- Row 4 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <span>Qual tipo deve ser aplicado primeiro?</span>
              <v-radio-group 
                            v-model="radio"
                            :mandatory="false"
                            @change="changeRadio">
                            <v-radio label="Hora Extra" value="overtime"></v-radio>
                            <v-radio label="Banco de Horas" value="bankOfHours"></v-radio>
              </v-radio-group>
            </v-flex>
          </v-layout>
          <v-layout v-if="bothTypes">
          <!-- Row 2 -->
            <v-flex xs12 sm9 md6 lg6 xl4>              
              <v-checkbox
                :disabled="blockCheckbox2"
                :label="`Não aplicar limite de horas extras`"
                v-model="checkbox2"
                @change="verifyCheckBoxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="blockOvertimeTypeLimit"
                    v-model="overtimeTypeLimit"
                    type="number"
                    prepend-icon="looks_one"
                    clearable
                    suffix="horas"
                    label="Limite diário de horas extras">
              </v-text-field>
            </v-flex>
          </v-layout>
          <v-layout v-if="bothTypes">
          <!-- Row 3 -->
            <v-flex xs12 sm9 md6 lg6 xl4>
              <v-checkbox
                :disabled="blockCheckbox3"
                :label="`Não aplicar limite de banco de horas`"
                v-model="checkbox3"
                @change="verifyCheckBoxes"
              ></v-checkbox>
              <v-text-field
                    :disabled="blockBankOfHoursTypeLimit"
                    v-model="bankOfHoursTypeLimit"
                    type="number"
                    prepend-icon="looks_two"
                    clearable
                    suffix="horas"
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
  </v-app>
</template>

<script>
import Parameters from '../../objects/Parameters'

export default {
  data: () => ({
    step: 1,
    parameters: [],
    haveMessage: false,
    messages: [],
    messageColor: '',
    edit: false,
    parametersId: 0,
    // overtime percenteges
    overtime: 50,
    nightOvertime: 20,
    weekEndOvertime: 100,
    // deadlines
    retroactiveAppointment: 1,
    relocationRequest: 12,
    bankCompensation: 6,
    // hour type
    hourTypes: [
      'Hora Extra',
      'Banco de Horas',
      'Hora Extra e Banco de Horas'
    ],
    hoursLimit: 3,
    overtimeTypeLimit: 0,
    bankOfHoursTypeLimit: 0,
    bankOfHoursType: true,
    overtimeType: false,
    bothTypes: false,
    radio: 'overtime',
    type: 'Hora Extra',
    checkbox1: false,
    checkbox2: false,
    checkbox3: false,
    blockCheckbox2: true,
    blockCheckbox3: false,
    blockHoursLimit: false,
    blockOvertimeTypeLimit: false,
    blockBankOfHoursTypeLimit: false,
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
  methods: {
    changeRadio () {
      if (this.radio === 'overtime') {
        this.checkbox2 = false
        this.blockCheckbox2 = true
        this.blockCheckbox3 = false
      } else if (this.radio === 'bankOfHours') {
        this.blockCheckbox2 = false
        this.checkbox3 = false
        this.blockCheckbox3 = true
      }
      this.verifyCheckBoxes()
    },
    verifyCheckBoxes () {
      if (this.checkbox1) {
        this.blockHoursLimit = true
        this.hoursLimit = null
      } else {
        this.blockHoursLimit = false
        this.hoursLimit = 0
      }
      if (this.checkbox2 && this.radio !== 'overtime') {
        this.blockOvertimeTypeLimit = true
        this.overtimeTypeLimit = null
      } else {
        this.blockOvertimeTypeLimit = false
        this.overtimeTypeLimit = 0
      }
      if (this.checkbox3 && this.radio !== 'bankOfHours') {
        this.blockBankOfHoursTypeLimit = true
        this.bankOfHoursTypeLimit = null
      } else {
        this.blockBankOfHoursTypeLimit = false
        this.bankOfHoursTypeLimit = 0
      }
    },
    verifyFields (type) {
      if (type === 'Hora Extra') {
        this.overtimeType = true
        this.bankOfHoursType = false
        this.bothTypes = false
      } else if (type === 'Banco de Horas') {
        this.overtimeType = false
        this.bankOfHoursType = true
        this.bothTypes = false
      } else if (type === 'Hora Extra e Banco de Horas') {
        this.overtimeType = false
        this.bankOfHoursType = false
        this.bothTypes = true
      }
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
    createParameters () {
      let parameters =
        new Parameters(
          this.parametersId,
          this.overtime,
          this.nightOvertime,
          this.weekEndOvertime,
          this.retroactiveAppointment,
          this.relocationRequest,
          this.bankCompensation,
          this.type,
          this.hoursLimit,
          this.overtime,
          this.bankOfHoursTypeLimit
        )
      return parameters
    },
    validateDeadlines () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_parameters.retroactiveAppointmentRules(this.retroactiveAppointment)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.relocationRequestRules(this.relocationRequest)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.bankCompensationRules(this.bankCompensation)
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
    validatePercentages () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_parameters.overtimeRules(this.overtime)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.nightOvertimeRules(this.nightOvertime)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.weekEndOvertimeRules(this.weekEndOvertime)
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
    validateHourType () {
      this.haveMessage = false
      this.messages = []
      this.messageColor = ''
      let error = []
      error = this.$v_parameters.hoursLimitRules(this.type, this.checkbox1, this.hoursLimit)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.overtimeTypeLimitRules(this.type, this.checkbox2, this.overtimeTypeLimit)
      if (error) {
        this.messages = [...this.messages, ...error]
      }
      error = this.$v_parameters.bankOfHoursTypeLimitRules(this.type, this.checkbox3, this.bankOfHoursTypeLimit)
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
    *callApi (parameters) {
      if (!this.edit) {
        yield this.$_axios.post(`${this.$_url}parametros`, parameters).then(response => {
          var result = response.data
          if (result.listaResultado.length !== 0) {
            // retorno ok /
            parameters = result.listaResultado
          }
          if (result.mensagem) {
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
        },
        response => {
          // erro na requisição do serviço /
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      } else {
        yield this.$_axios.put(`${this.$_url}parametros`, parameters).then(response => {
          var result = response.data
          if (result.listaResultado.length !== 0) {
            // retorno ok /
            parameters = result.listaResultado
          }
          if (result.mensagem) {
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
        },
        response => {
          // erro na requisição do serviço /
          this.messages = ['Erro durante execução do serviço!']
          this.haveMessage = true
          this.messageColor = 'error'
        })
      }
    },
    saveParameters (event) {
      if (this.validateHourType()) {
        this.$router.push('/Parametros?message=mensagem')
        // let parameters = this.createUser()
        // this.callApi(parameters).next().value
      }
      this.$vuetify.goTo(0)
    },
    clearForm () {
      this.parameters = {}
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