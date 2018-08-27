<template>
    <v-app>
    <h1>{{title}}</h1>
    <br/>
    <v-form v-model="valid" @submit.prevent="findByNomeAndSenha">
      <v-layout>          
      <!-- Row 1 -->
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.name"
                        type="text"
                        class="px-0"
                        prepend-icon="face"
                        clearable
                        :rules="nameRules"
                        label="Nome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.lastName"
                        type="text"
                        prepend-icon="face"
                        clearable
                        :rules="nameRules"
                        label="Sobrenome"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.email"
                        type="email"
                        prepend-icon="email"
                        clearable
                        :rules="emailRules"
                        label="E-mail"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 2 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.salario"
                        type="number"
                        prepend-icon="monetization_on"
                        clearable
                        :rules="salaryRules"
                        label="Salário"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.pis"
                        type="number"
                        prepend-icon="credit_card"
                        clearable
                        :rules="pisRoles"
                        label="PIS/PASESP"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-text-field v-model="user.workload"
                        type="number"
                        prepend-icon="timer"
                        clearable
                        :rules="workloadRules"
                        label="Carga Horária"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Row 3 -->
      <v-layout>
        <v-flex xs12 sm9 md6 lg6 xl4 >
          <v-menu ref="birthdate"
                  :close-on-content-click="false"
                  v-model="birthdate"
                  :nudge-right="40"
                  :return-value.sync="user.birthdate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
          <v-text-field slot="activator"
                        v-model="user.birthdate"
                        label="Data de Nascimento"
                        prepend-icon="event"
                        readonly>
          </v-text-field>
          <v-date-picker v-model="user.birthdate"
                          :reactive="reactive"
                          header-color="primary"
                          @input="$refs.birthdate.save(user.birthdate)">
          </v-date-picker>
          </v-menu>
        </v-flex>
        <v-flex xs12 sm9 md6 lg6 xl4>
          <v-menu ref="joiningDate"
                  :close-on-content-click="false"
                  v-model="joiningDate"
                  :nudge-right="40"
                  :return-value.sync="user.joiningDate"
                  lazy
                  transition="scale-transition"
                  offset-y
                  full-width
                  min-width="290px">
          <v-text-field slot="activator"
                        v-model="user.joiningDate"
                        label="Data de Ingresso na Empresa"
                        prepend-icon="event"
                        readonly>
          </v-text-field>
          <v-date-picker v-model="user.joiningDate"
                          header-color="green" 
                          :reactive="reactive"
                          @input="$refs.joiningDate.save(user.joiningDate)">
          </v-date-picker>
          </v-menu>
        </v-flex>
      </v-layout>
      <!-- Row 4 -->
      <v-layout>
        <v-flex >
          <v-combobox v-model="user.boss"
                      prepend-icon="supervisor_account"
                      :items="items.boss"
                      label="Supervisor Imediato"
                      multiple
                      chips>
            <template slot="selection"
                      slot-scope="data">
              <v-chip :selected="data.selected"
                      :disabled="data.disabled"
                      :key="JSON.stringify(data.item)"
                      class="v-chip--select-multi"
                      @input="data.parent.selectItem(data.item)">
                <v-avatar class="accent white--text"
                          v-text="data.item.slice(0, 1).toUpperCase()">
                </v-avatar>
                  {{ data.item }}
              </v-chip>
            </template>
          </v-combobox>
        </v-flex>
      </v-layout>
      <!-- Row 5 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field v-model="user.phone1"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRules"
                          label="Telefone"
                          required>
            </v-text-field>
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.phone2"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRules"
                          label="Telefone">
          </v-text-field> 
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.phone3"
                          type="phone"
                          prepend-icon="phone"
                          clearable
                          :rules="phoneRules"
                          label="Telefone">
            </v-text-field>
        </v-flex>
      </v-layout>
      <!-- Rown 5 -->
      <v-layout xs12 sm9 md6 lg6 xl4>
        <v-flex>
          <v-text-field v-model="user.password"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        placeholder="Informe a Senha do Funcionário"
                        loading >
            <v-progress-linear slot="progress"
                              :value="progress"
                              :color="color"
                              height="7"> 
            </v-progress-linear>
          </v-text-field>    
        </v-flex>
        <v-flex>
          <v-text-field v-model="user.password2"
                        color="cyan darken"
                        label="Senha"
                        type="password"
                        prepend-icon="fingerprint"
                        :rules="passwordRules"
                        placeholder="Confirme a Senha do Funcionário"
                        loading >
            <v-progress-linear slot="progress"
                              :value="progress"
                              :color="color"
                              height="7"> 
            </v-progress-linear>
          </v-text-field>    
        </v-flex>
      </v-layout>
      <div v-if="!this.edit">
        <v-btn type="submit" color="success">Salvar</v-btn>
        <v-btn type="submit" color="error">Cancelar</v-btn>
      </div>
    </v-form>
  </v-app>
</template>

<script>
// import axios from 'axios'
export default {
  props: {
    user: {
      type: Object,
      default: {}
    },
    edit: {
      type: Boolean,
      default: false
    },
    message: {
      type: String,
      default: ''
    }
  },
  data: () => ({
    valid: false,
    menu: false,
    reactive: true,
    joiningDate: false,
    birthdate: false,
    items: {
      boss: ['Bill Gates', 'Torvald Linux', 'Elon Musk']
    },
    nameRules: [
      v => !!v || 'Informe o Nome do Funcionário!',
      v => v.length <= 20 || 'Informe um nome váilido!',
      v => /[^0-9]+[a-zA-Z]/.test(v) || 'Informe um nome váilido!'
    ],
    emailRules: [
      v => !!v || 'Informe o E-mail!',
      v => /.+@.+/.test(v) || 'Informe um E-mail válido!'
    ],
    salaryRules: [
      v => !!v || 'Informe um Salário!',
      v => v === 0 || 'Informe um Salário!'
    ],
    pisRoles: [
      v => !!v || 'Informe um número PIS/PASESP!',
      v => v.length <= 9 || 'Informe um número PIS/PASESP válido!'
    ],
    workloadRules: [
      v => !!v || 'Informe uma Carga Horária!',
      v => v.length <= 1 || 'Informe uma Carga Horária válida!'
    ],
    phoneRules: [
      v => !!v || 'Informe pelo menos um Telefone!',
      v => v.length <= 9 || 'Informe um número de Telefone válido!',
      v => v.length >= 8 || 'Informe um número de Telefone válido!'
    ],
    passwordRules: [
      v => !!v || 'Informe uma Senha!'
      // v => /.[0-9]{1}+[a-z]{1}+[A-Z]{1}./.test(v) || 'Informe uma Senha válida!'
    ]
  }),
  created () {
  },
  computed: {
    progress () {
      return Math.min(100, this.user.password.length * 10)
    },
    color () {
      return ['error', 'warning', 'success'][Math.floor(this.progress / 40)]
    },
    title () {
      if (!this.edit) {
        return 'Cadastro de Funcionário'
      } else {
        return 'Alterar Funcionário'
      }
    }
  },
  watch: {
  }
}
</script>

<style scoped>
  h1 {
    font-size: 40px;
    text-align: center;
  }
</style>