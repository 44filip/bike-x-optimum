<template>
  <div class="page-size">
    <div class="form-grou">
      <h1>Register</h1>
      <form class="login-form" @submit.prevent="performRegister">
        <div class="forma">
          <input
            v-model="email"
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
          <input class="lf--submit form-control" type="submit" value="REGISTER" />
        </div>
      </form>
      <a class="lf--forgot">
        <router-link to="/login"
          >Already have an account?<br />Login HERE</router-link
        >
      </a>
      <PopupError ref="popup" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import CryptoJS from "crypto-js";
import PopupError from "./PopupError.vue";

export default {
  name: "RegisterComponent",
  components: {
    PopupError,
  },
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async performRegister() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

      if (!emailRegex.test(this.email.trim())) {
      this.$refs.popup.showPopup("Invalid e-mail format.");
      return;
      }

      if (!passwordRegex.test(this.password.trim())) {
      this.$refs.popup.showPopup("Password must be at least 8 characters, using letters and numbers.");
      return;
      }

      try {
        const hashedPassword = CryptoJS.SHA256(this.password.trim()).toString();
        const user = {
          email: this.email,
          password: hashedPassword,
          balance: 0,
          role: "user",
        };
        await axios.post("https://localhost:8443/addUser", user);
        this.$router.push("/registeredLogin");
      } catch (error) {
        this.$refs.popup.showPopup("Account with this e-mail already exists.");
      }
    },
  },
};
</script>
<style scoped>
input.form-control:focus {
  border-color: #80bdff !important;
  box-shadow: 0 0 0 0.2rem rgba(0,123,255,.25) !important;
  outline: none !important;
}
</style>