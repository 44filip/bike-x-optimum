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
    <PopupError ref="errorPopup" />
  </div>
</template>

<script>
import axios from "axios";
import CryptoJS from "crypto-js";
import PopupError from "./PopupError.vue";

export default {
  name: "AccountComponent",
  components: {
    PopupError,
  },
  data() {
    const user = JSON.parse(localStorage.getItem("user"));
    return {
      password: "",
      userId: user.userId,
      email: user.email,
    };
  },
  methods: {
    async deleteAccount() {
      try {
        if (
          !confirm(
            "Are you sure you want to delete your account? This action cannot be undone."
          )
        ) {
          return;
        }
        await axios.delete(`https://localhost:8443/delete/id/${this.userId}`);
        this.$emit("logout");
      } catch (error) {
        console.error(error);
      }
    },
    async changePassword() {
      const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      if (!passwordRegex.test(this.password.trim())) {
        this.$refs.errorPopup.showPopup(
          "Password must be at least 8 characters, with letters and numbers."
        );
        return;
      }
      try {
        const newHashedPassword = CryptoJS.SHA256(
          this.password.trim()
        ).toString();

        const checkResponse = await axios.post(
          "https://localhost:8443/check-password",
          {
            email: this.email,
            password: newHashedPassword,
          }
        );

        if (checkResponse.data === true) {
          this.$refs.errorPopup.showPopup(
            "New password cannot be the same as the current password."
          );
          return;
        }

        const userResponse = await axios.get(
          `https://localhost:8443/user/email/${this.email}`
        );
        let user = userResponse.data;
        user.password = newHashedPassword;

        await axios.put("https://localhost:8443/update", user, {
          headers: { "Content-Type": "application/json" },
        });

        this.$emit("logout");
      } catch (error) {
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
