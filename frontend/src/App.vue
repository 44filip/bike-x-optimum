<template>
  <div id="app">

    <Head />

    <body>
      <Nav :navItems="navigation" @logout="logout" />
      <main>
        <router-view></router-view>
      </main>
      <Footer />
    </body>
  </div>
</template>

<script>
import axios from "axios";
import Head from "./components/Head.vue";
import Nav from "./components/Nav.vue";
import Footer from "./components/Footer.vue";

export default {
  name: 'App',
  components: {
    Head,
    Nav,
    Footer
  },
  data() {
    return {
      navigation: [
        { text: "Home", path: "/" },
        { text: "Bikes", path: "/bikes" },
        { text: "Contact", path: "/contact" }
      ],
      refreshTimeoutId: null,
    }
  },
  created() {
    document.title = 'Bike X Optimum';
    const refreshToken = localStorage.getItem("refreshToken");
    if (refreshToken) {
      this.scheduleTokenRefresh();
    }
  },
  methods: {
    scheduleTokenRefresh() {
      if (this.refreshTimeoutId) {
        clearTimeout(this.refreshTimeoutId);
      }
      this.refreshTimeoutId = setTimeout(async () => {
        const refreshToken = localStorage.getItem("refreshToken");
        if (refreshToken) {
          try {
            const response = await axios.post(
              "https://localhost:8443/refresh",
              refreshToken,
              { headers: { "Content-Type": "text/plain" } }
            );
            const newAccessToken = response.data.accessToken;
            localStorage.setItem("jwtToken", newAccessToken);
            console.log("Access Token refreshed:", newAccessToken);
            this.scheduleTokenRefresh();
          } catch (error) {
            console.error("Failed to refresh token, logging out", error);
            this.logout();
          }
        } else {
          clearTimeout(this.refreshTimeoutId);
          this.refreshTimeoutId = null;
        }
      }, 8 * 1000); // 8 seconds for testing
    },
    logout() {
      localStorage.removeItem("jwtToken");
      localStorage.removeItem("refreshToken");
      localStorage.removeItem("user");
      if (this.refreshTimeoutId) {
        clearTimeout(this.refreshTimeoutId);
        this.refreshTimeoutId = null;
      }
      this.$store.commit("setUser", null);
      this.$store.commit("setBalance", 0);
      this.$router.push("/login");
      window.location.reload();
    }
  },
  beforeDestroy() {
    if (this.refreshTimeoutId) {
      clearTimeout(this.refreshTimeoutId);
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