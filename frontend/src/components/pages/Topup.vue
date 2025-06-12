<template>
  <div class="page-size">
    <div class="form-grou">
      <h1>Topup</h1>
      <form @submit.prevent="addToBalance" class="login-form">
        <div class="forma">
          <input
            v-model="balance"
            id="balance"
            class="lf--input form-control"
            placeholder="Amount to add ($)"
            type="text"
          />
        </div>
        <div class="forma">
          <input
            class="lf--submit form-control"
            type="submit"
            value="ADD TO BALANCE"
          />
        </div>
      </form>
    </div>
    <PopupError ref="popup" />
  </div>
</template>

<script>
import axios from "axios";
import PopupError from "./PopupError.vue";

export default {
  name: "TopupComponent",
  components: { PopupError },
  data() {
    return {
      balance: "",
    };
  },
  methods: {
    async addToBalance() {
      const amount = parseFloat(this.balance);
      if (isNaN(amount) || amount <= 0) {
        this.$refs.popup.showPopup("Please enter a valid positive amount.");
        return;
      }

      try {
        const userData = JSON.parse(localStorage.getItem("user"));
        if (!userData || !userData.email) {
          this.$refs.popup.showPopup("User not logged in.");
          return;
        }

        const response = await axios.get(
          `https://localhost:8443/user/email/${userData.email}`
        );
        const user = response.data;

        user.balance = (parseFloat(user.balance) + amount).toFixed(2);

        await this.updateUserInBackend(user);
      } catch (error) {
        console.error(error);
        this.$refs.popup.showPopup("Failed to add balance. Please try again.");
      }
    },
    async updateUserInBackend(user) {
      try {
        await axios.put("https://localhost:8443/update", user, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        localStorage.setItem("user", JSON.stringify(user));
        window.dispatchEvent(new Event("balance-updated"));
        this.$refs.popup.showPopup("Balance added successfully!");
      } catch (error) {
        console.error(error);
        this.$refs.popup.showPopup("Failed to update balance.");
      }
    },
  },
};
</script>