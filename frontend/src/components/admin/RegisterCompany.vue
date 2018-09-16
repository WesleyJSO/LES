<template>
    <v-app>
    <h1>Dados da Empresa</h1>
    <li v-for="(message, index) in messages" :key="index">
      <v-alert :color="messageColor"
               :value="haveMessage"
               v-text="message"
               transition="scale-transition" />
    </li>
    <br/>
    <v-form ref="form" v-model="valid">

      <v-layout row wrap>
      <!-- Row 1 -->
        <v-flex xs6>
          <v-text-field v-model="company.socialName"
                :rules="$v_company.socialNameRules(company.socialName)"
                type="text"
                class="px-0"
                prepend-icon="assignment"
                clearable
                label="Razão Social"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field v-model="company.tradingName"
                :rules="$v_company.tradingNameRules(company.tradingName)"
                type="text"
                prepend-icon="rate_review"
                clearable
                label="Nome Fantasia"
                required>
          </v-text-field>
        </v-flex>
        <!-- Row 2  for testes 61442737000230 -->
        <v-flex xs6>
          <v-text-field v-model="company.cpnj"
                :rules="$v_company.cnpjRules(company.cpnj)"
                :counter="14"
                type="text"
                prepend-icon="email"
                clearable
                label="CNPJ"
                required>
          </v-text-field>
        </v-flex>
        <v-flex xs6>
          <!-- for tests 454504330118 -->
          <v-text-field v-model="company.stateRegistration"
                :rules="$v_company.stateRegistrationRules(company.stateRegistration)"
                :counter="12"
                type="text"
                prepend-icon="filter_frames"
                clearable
                label="Inscrição Estadual"
                required>
          </v-text-field>
        </v-flex>
        <!-- row 3 for tests 08750710 -->
        <v-flex xs4>
          <v-text-field id="zipCode"
                v-model="company.address.zipCode"
                :rules="$v_address.zipCodeRules(company.address.zipCode)"
                :counter="8"
                type="text"
                prepend-icon="list_alt"
                clearable
                label="CEP"
                required
                @change="getAddress">
          </v-text-field>
        </v-flex>
        <v-flex xs4>
          <v-text-field id="number"
                        v-model="company.address.number"
                        :rules="$v_address.numberRules(company.address.number)"
                        type="number"
                        prepend-icon="money"
                        clearable
                        label="Número"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs4>
          <v-text-field id="street"
                        v-model="company.address.street"
                        :rules="$v_address.streetRules(company.address.street)"
                        type="text"
                        prepend-icon="place"
                        clearable
                        label="Rua"
                        required>
          </v-text-field>
        </v-flex>
        <!-- row 4 -->
        <v-flex xs6>
          <v-text-field id="city"
                        v-model="company.address.city"
                        :rules="$v_address.cityRules(company.address.city)"
                        type="text"
                        prepend-icon="domain"
                        clearable
                        label="Cidade"
                        required>
          </v-text-field>
        </v-flex>
        <v-flex xs6>
          <v-text-field id="state"
                        v-model="company.address.state"
                        :rules="$v_address.stateRules(company.address.state)"
                        type="text"
                        prepend-icon="location_city"
                        clearable
                        label="Estado"
                        required>
          </v-text-field>
        </v-flex>
      </v-layout>

      <v-flex xs12 right>
        <v-btn @click="submit"
              :disabled="!valid"
              color="success">Salvar</v-btn>
        <v-btn @click="clear" color="error">Cancelar</v-btn>
      </v-flex>

    </v-form>
  </v-app>
</template>

<script>
export default {
  data: function () {
    return {
      company: {
        address: {}
      },
      edit: false,
      haveMessage: false,
      messages: [],
      messageColor: '',
      valid: false
    }
  },
  methods: {
    submit () {
      console.log(JSON.stringify(this.company))
      this.$_axios.post(`${this.$_url}company`, this.company).then(response => {
        console.log(JSON.stringify(response.data))
        let result = response.data
        if (result.resultList.length !== 0) {
          this.company = result.resultList[0]
        }
        if (result.message) {
          this.messages = [...result.message]
          this.haveMessage = true
          if (result.success) {
          // retorno mensagem de sucesso /
            this.messageColor = 'info'
            this.clear()
          } else {
            // retorno mensagem de erro /
            this.messageColor = 'warning'
          }
        }
      }).catch(error => {
        // erro na requisição do serviço /
        console.log(error)
        this.messages = ['Erro durante execução do serviço!']
        this.haveMessage = true
        this.messageColor = 'error'
      })
    },
    clear () {
      this.$refs.form.reset()
    },
    getAddress (zipCode) {
      if (zipCode.length === 8) {
        this.$_axios.get(`${this.$_viaCep}${zipCode}/json`).then(reponse => {
          console.log(reponse)
          let address = reponse.data
          this.company.address.street = address.logradouro
          this.company.address.city = address.localidade
          this.company.address.state = address.uf
          console.log(JSON.stringify(this.company.address))
        }).catch(error => {
          console.log(error)
          if (error.data) {
            this.messages = ['CEP não encontrado!']
            this.haveMessage = true
            this.messageColor = 'error'
          }
        })
      }
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
