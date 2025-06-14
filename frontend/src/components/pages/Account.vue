<template>
  <div class="form-grou">
    <h1>Account administration</h1>
    <p>If you wish to change your password enter it here:</p>
    <form @submit.prevent="changePassword" class="login-form">
      <div class="forma">
        <input
          v-model="currentPassword"
          id="current-password"
          class="lf--input form-control"
          placeholder="Your current password"
          type="password"
        />
      </div>
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
      <div class="forma">
        <input
          v-model="currentPasswordForDelete"
          id="current-password-delete"
          class="lf--input form-control"
          placeholder="Your current password"
          type="password"
        />
      </div>
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
  components: { PopupError },
  data() {
    const user = JSON.parse(localStorage.getItem("user"));
    return {
      currentPassword: "",
      currentPasswordForDelete: "",
      password: "",
      userId: user.userId,
      email: user.email,
    };
  },
  methods: {
    async deleteAccount() {
      if (!this.currentPasswordForDelete.trim()) {
        this.$refs.errorPopup.showPopup(
          "Please enter your current password to delete your account."
        );
        return;
      }
      try {
        const currentHashed = CryptoJS.SHA256(
          this.currentPasswordForDelete.trim()
        ).toString();

        const currentCheck = await axios.post(
          "https://localhost:8443/check-password",
          {
            email: this.email,
            password: currentHashed,
          }
        );

        if (!currentCheck.data) {
          this.$refs.errorPopup.showPopup("Current password is incorrect.");
          return;
        }

        if (
          !confirm(
            "Are you sure you want to delete your account? This action cannot be undone."
          )
        )
          return;

        await axios.delete(`https://localhost:8443/delete/id/${this.userId}`);
        this.$emit("logout");
      } catch (error) {
        console.error(error);
      }
    },

    async changePassword() {
      if (!this.currentPassword.trim()) {
        this.$refs.errorPopup.showPopup("Please enter your current password.");
        return;
      }

      try {
        const currentHashed = CryptoJS.SHA256(
          this.currentPassword.trim()
        ).toString();

        const currentCheck = await axios.post(
          "https://localhost:8443/check-password",
          {
            email: this.email,
            password: currentHashed,
          }
        );

        if (!currentCheck.data) {
          this.$refs.errorPopup.showPopup("Current password is incorrect.");
          return;
        }

        if (!this.password.trim()) {
          this.$refs.errorPopup.showPopup("Please enter a new password.");
          return;
        }

        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        if (!passwordRegex.test(this.password.trim())) {
          this.$refs.errorPopup.showPopup(
            "New password must be at least 8 characters, using letters and numbers."
          );
          return;
        }

        const newHashed = CryptoJS.SHA256(this.password.trim()).toString();

        if (newHashed === currentHashed) {
          this.$refs.errorPopup.showPopup(
            "New password cannot be the same as current password."
          );
          return;
        }

        const userResponse = await axios.get(
          `https://localhost:8443/user/email/${this.email}`
        );
        let user = userResponse.data;
        user.password = newHashed;

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
