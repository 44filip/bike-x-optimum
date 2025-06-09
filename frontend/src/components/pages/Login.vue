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
        const response = await axios.get(
          `https://localhost:8443/user/email/${this.username}`
        );
        const user = response.data;
        const hashedPassword = CryptoJS.SHA256(this.password.trim()).toString();

        if (user && user.password.toString() === hashedPassword) {
          localStorage.setItem("user", JSON.stringify(user));
          this.$store.commit("setUser", user);

          if (user.token && user.token.length > 0) {
            localStorage.setItem("jwtToken", user.token);
          }

          if (user.refreshToken && user.refreshToken.length > 0) {
            localStorage.setItem("refreshToken", user.refreshToken);
          }

          this.$router.push("/");
          window.location.reload();
        } else {
          this.$refs.errorPopup.message = "Invalid email or password.";
          this.$refs.errorPopup.showPopup();
        }
      } catch (error) {
        this.error = "An error occurred during login.";
        console.error(error);
      }
    },
  },
};
</script>
