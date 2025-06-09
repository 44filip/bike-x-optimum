<template>
  <div class="form-grou">
    <h1>Account administration</h1>
    <p>If you wish to change your password enter it here:</p>
    <form @submit.prevent="changePassword" class="login-form">
      <div class="forma">
        <input
          v-model="password"
          id="password"
          class="lf--input form-control"
          placeholder="Your new password"
          type="password"
        />
      </div>
      <div class="forma changepass">
        <input
          class="lf--submit form-control"
          type="submit"
          value="CHANGE PASSWORD"
        />
      </div>
    </form>
    <p>If you wish to terminate your account press here:</p>
    <form @submit.prevent="deleteAccount" class="login-form">
      <div class="forma delacc">
        <input
          class="lf--submit form-control"
          type="submit"
          value="DELETE ACCOUNT"
        />
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import CryptoJS from "crypto-js";

export default {
  name: "AccountComponent",
  data() {
    return {
      password: "",
      userId: JSON.parse(localStorage.getItem("user")).userId,
    };
  },
  methods: {
    async deleteAccount() {
      try {
        await axios.delete(`https://localhost:8443/delete/id/${this.userId}`);
        this.$emit("logout"); // emit logout to App.vue
      } catch (error) {
        console.error(error);
      }
    },
    async changePassword() {
      try {
        const hashedPassword = CryptoJS.SHA256(this.password.trim()).toString();
        let email = JSON.parse(localStorage.getItem("user")).email;
        const response = await axios.get(
          `https://localhost:8443/user/email/${email}`
        );
        let user = response.data;
        user.password = hashedPassword;
        await axios.put("https://localhost:8443/update", user, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        this.$emit("logout"); // emit logout to App.vue after password change
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
