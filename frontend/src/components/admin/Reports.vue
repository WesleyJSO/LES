<template>
      <v-flex xs5>
        <line-chart :data="data" />
      </v-flex>
</template>
<script>
export default {
  data: () => ({
    valid: false,
    requestMenu: false,
    reactive: true,
    haveMessage: false,
    messages: [],
    message: '',
    data: [
      {name: 'Horas Trabalhadas', data: {'2017-01-01 00:00:00 -0800': 3, '2017-01-02 00:00:00 -0800': 4}},
      {name: 'Horas Extras', data: {'2017-01-01 00:00:00 -0800': 3, '2017-01-02 00:00:00 -0800': 4}},
      {name: 'Horas Almoço', data: {'2017-01-01 00:00:00 -0800': 5, '2017-01-02 00:00:00 -0800': 3}}
    ]
  })
}
</script>
