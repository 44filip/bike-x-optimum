<template>
  <div class="page-size">
    <div class="form-grou">
      <h1 v-if="showSpecialDiv">
        Your password has been successfully changed.
      </h1>
      <h1>Login</h1>
      <form class="login-form" @submit.prevent="performLogin">
        <div class="forma">
          <input
            v-model="username"
            id="email"
            class="lf--input form-control"
            placeholder="E-mail"
            type="text"
          />
        </div>
        <div class="forma">
          <input
            v-model="password"
            id="password"
            class="lf--input form-control"
            placeholder="Password"
            type="password"
          />
        </div>
        <div class="forma">
          <input class="lf--submit form-control" type="submit" value="LOGIN" />
        </div>
      </form>
      <a class="lf--forgot">
        <router-link to="/register"
          >Don't have an account?<br />Register HERE</router-link
        >
      </a>
      <PopupError ref="errorPopup" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CryptoJS from "crypto-js";
import PopupError from "./PopupError.vue";

export default {
  name: "LoginComponent",
  components: {
    PopupError,
  },
  data() {
    return {
      error: "",
      username: "",
      password: "",
      showSpecialDiv: false,
    };
  },
  created() {
    if (this.$route.query.fromSpecificPage) {
      this.showSpecialDiv = true;
    }
  },
  methods: {
    async performLogin() {
      try {
        const hashedPassword = CryptoJS.SHA256(this.password.trim()).toString();
        const response = await axios.post("https://localhost:8443/login", {
          email: this.username,
          password: hashedPassword,
        });

        const user = response.data;
        localStorage.setItem("user", JSON.stringify(user));
        this.$store.commit("setUser", user);

        if (user.token) localStorage.setItem("jwtToken", user.token);
        if (user.refreshToken)
          localStorage.setItem("refreshToken", user.refreshToken);

        this.$router.push("/");
        window.location.reload();
      } catch (error) {
        this.$refs.errorPopup.showPopup("Invalid email or password.");
        console.error(error);
      }
    },
  },
};
</script>
<style scoped>
input.form-control:focus {
  border-color: #80bdff !important;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25) !important;
  outline: none !important;
}
</style>
