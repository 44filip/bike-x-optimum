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
    <TopUpAdded class="TopUpAdded" ref="TopUpAdded"></TopUpAdded>
  </div>
</template>

<script>
import axios from 'axios';
import TopUpAdded from './TopUpAdded.vue';

export default {
  name: 'TopupComponent',
  data() {
    return {
      balance: '',
    };
  },
  components: {
    TopUpAdded,
  },
  methods: {
    async addToBalance() {
      let email = JSON.parse(localStorage.getItem('user'));
      let userEmail = email.email;
      const response = await axios.get(
        `https://localhost:8443/user/email/${userEmail}`
      );
      let user = response.data;
      user.balance = (parseFloat(user.balance) + parseFloat(this.balance)).toFixed(2);

      await this.updateUserInBackend(user);
    },
    async updateUserInBackend(user) {
      try {
        await axios.put('https://localhost:8443/update', user, {
          headers: {
            'Content-Type': 'application/json',
          },
        });

        localStorage.setItem('user', JSON.stringify(user));
        window.dispatchEvent(new Event('balance-updated'));
        this.$refs.TopUpAdded.showPopup();
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>