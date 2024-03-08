<template>
    <div>
      <Bikes
        v-for="product in products"
        :key="product.id"
        :product="product"
        @add-to-cart="$emit('add-to-cart', $event)"
      />
    </div>
  </template>
  
  <script>
  import Bikes from './Bikes.vue';
  
  export default {
    components: { Bikes },
    props: ['bikes']
  }
  </script>