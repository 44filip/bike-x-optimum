<template>
  <div id="app">

    <Head />

    <body>
      <Nav :navItems="navigation" />
      <main>
        <router-view :products="bikes"></router-view>

      </main>
      <Footer />
    </body>
  </div>
</template>

<script>
import Head from "./components/Head.vue"
import Nav from "./components/Nav.vue"
import Footer from "./components/Footer.vue"

export default {
  name: 'App',
  components: {
    Head,
    Nav,
    Footer
  },
  watch: {
    $route: {
      immediate: true,
      handler() {
        document.title = 'Bike X Optimum';
      }
    },
  },
  created() {
    if (!this.$store.state.cartEmptied) {
      this.$store.dispatch('emptyCart');
    }
  },
  data() {
    return {
      users: [], // Initialize users as an empty array
      navigation: [
        {
          text: "Home",
          path: "/"
        },
        {
          text: "Bikes",
          path: "/bikes"
        },
        {
          text: "Contact",
          path: "/contact"
        }
      ]
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
