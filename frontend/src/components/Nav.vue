<template>
  <div class="header_section header_bg">
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
      <a class="logo">
        <router-link to="/"><img src="images/logo.png" /></router-link>
      </a>

      <form class="form-inline my-2 my-lg-0">
        <ul class="navbar-nav mr-auto">
          <li v-for="(item, index) in navItems" :key="index" class="nav-item">
            <a class="nav-link active">
              <router-link :to="item.path">{{ item.text }}</router-link>
            </a>
          </li>

          <li v-role="['unauthorized']" class="nav-item">
            <a class="nav-link active">
              <router-link to="/login">Login</router-link>
            </a>
          </li>

          <li v-role="['admin', 'user']" class="nav-item">
            <a href="#" class="nav-link active">
              <router-link to="/account">Account</router-link>
            </a>
          </li>

          <li v-role="['admin', 'user']" class="nav-item">
            <a href="#" class="nav-link active">
              <router-link class="gas" to="/topup">
                Topup&nbsp;
                <div>(${{ formattedBalance }})</div>
              </router-link>
            </a>
          </li>

          <li
            v-role="['admin', 'user']"
            @click="$emit('logout')"
            class="nav-item"
          >
            <a href="#" class="nav-link active">Logout</a>
          </li>
        </ul>

        <div class="login_menu">
          <ul>
            <li class="numCart">
              <router-link to="/shop">
                <div class="cartF">
                  <div>
                    <img id="cartLogo" src="/images/shopping-cart.png" />
                  </div>
                  <div class="numCart">{{ cartQuantity }}</div>
                </div>
              </router-link>
            </li>
          </ul>
        </div>
      </form>

      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarToggleExternalContent"
        aria-controls="navbarToggleExternalContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
    </nav>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "NavComponent",
  props: ["navItems"],
  computed: {
    ...mapGetters(["cartQuantity"]),
    formattedBalance() {
      return parseFloat(this.balance).toFixed(2);
    },
  },
  data() {
    return {
      balance: 0,
    };
  },
  mounted() {
    this.refreshBalance();
    window.addEventListener("balance-updated", this.refreshBalance);
  },
  beforeDestroy() {
    window.removeEventListener("balance-updated", this.refreshBalance);
  },
  methods: {
    refreshBalance() {
      const user = JSON.parse(localStorage.getItem("user"));
      if (user?.email) {
        axios
          .get(`https://localhost:8443/user/email/${user.email}`)
          .then((res) => {
            this.balance = res.data.balance;
            localStorage.setItem("user", JSON.stringify(res.data));
          })
          .catch((err) => console.error(err));
      }
    },
  },
};
</script>
