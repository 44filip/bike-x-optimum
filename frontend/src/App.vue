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
  }
  ,
  data() {
    return {
      navigation: [
        {
          text: "Home",
          path: "/cycle"
        },
        {
          text: "Bikes",
          path: "/bikes"
        },
        {
          text: "Contact",
          path: "/contact"
        },
        {
          text: "Login",
          path: "/login"
        }
      ],
      bikes: [
        {
          name: "Bike 1 - Cycle",
          img: "/images/img-1.png",
          price: "220"
        },
        {
          name: "Bike 2 - Stylish",
          img: "/images/img-5.png",
          price: "250"
        },
        {
          name: "Bike 3 - Modern",
          img: "/images/img-6.png",
          price: "280"
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
