<template>
  <div id="app">
    <Head />

    <body>
      <Nav :navItems="navigation" @logout="logout" />
      <main>
        <router-view @logout="logout"></router-view>
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
import { jwtDecode } from "jwt-decode";

export default {
  name: "App",
  components: {
    Head,
    Nav,
    Footer,
  },
  data() {
    return {
      navigation: [
        { text: "Home", path: "/" },
        { text: "Bikes", path: "/bikes" },
        { text: "Contact", path: "/contact" },
      ],
      refreshTimeoutId: null,
      accessExpiryCheckId: null,
    };
  },
  created() {
    document.title = "Bike X Optimum";
    const refreshToken = localStorage.getItem("refreshToken");
    if (refreshToken) {
      this.scheduleTokenRefresh();
      this.scheduleAccessTokenExpiryCheck();
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
            this.scheduleAccessTokenExpiryCheck();
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
    scheduleAccessTokenExpiryCheck() {
      if (this.accessExpiryCheckId) {
        clearInterval(this.accessExpiryCheckId);
      }
      this.accessExpiryCheckId = setInterval(() => {
        const token = localStorage.getItem("jwtToken");
        if (!token) {
          console.log("No access token found, logging out.");
          this.logout();
          return;
        }
        try {
          const decoded = jwtDecode(token);
          const now = Math.floor(Date.now() / 1000);
          if (decoded.exp < now) {
            console.log("Access token expired, logging out.");
            this.logout();
          } else {
            console.log(`Access token valid. Expires in ${decoded.exp - now} seconds.`);
          }
        } catch (e) {
          console.error("Failed to decode token, logging out.", e);
          this.logout();
        }
      }, 1000); // check every second
    },
    logout() {
      localStorage.removeItem("jwtToken");
      localStorage.removeItem("refreshToken");
      localStorage.removeItem("user");
      if (this.refreshTimeoutId) {
        clearTimeout(this.refreshTimeoutId);
        this.refreshTimeoutId = null;
      }
      if (this.accessExpiryCheckId) {
        clearInterval(this.accessExpiryCheckId);
        this.accessExpiryCheckId = null;
      }
      this.$store.commit("setUser", null);
      this.$store.commit("setUserBalance", 0);
      this.$router.push("/login");
      window.location.reload();
    },
  },
  beforeDestroy() {
    if (this.refreshTimeoutId) {
      clearTimeout(this.refreshTimeoutId);
    }
    if (this.accessExpiryCheckId) {
      clearInterval(this.accessExpiryCheckId);
    }
  },
};
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