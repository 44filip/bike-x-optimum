import Vue from "vue";
import App from "./App.vue";
import VueRouter from "vue-router";
import Cycle from "./components/pages/Cycle.vue";
import Bikes from "./components/pages/Bikes.vue";
import Contact from "./components/pages/Contact.vue";
import Login from "./components/pages/Login.vue";
import Register from "./components/pages/Register.vue";
import Shop from "./components/pages/Shop.vue";
import store from "./store";
import RegisteredLogin from "./components/pages/RegisteredLogin.vue";
import RoleDirective from "./directives/roleDirectives";
import Topup from "./components/pages/Topup.vue";
import Account from "./components/pages/Account.vue";
import Admin from "./components/pages/Admin.vue";

Vue.config.productionTip = false;
Vue.directive("role", RoleDirective);

var routes = [
  { path: "/", component: Cycle },
  { path: "/bikes", component: Bikes },
  { path: "/contact", component: Contact },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  { path: "/shop", component: Shop },
  { path: "/registeredLogin", component: RegisteredLogin },
  { path: "/topup", component: Topup },
  { path: "/account", component: Account },
  { path: "/admin", component: Admin },
];

var router = new VueRouter({
  scrollBehavior: () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        window.scrollTo(0, 0);
        resolve({ x: 0, y: 0 });
      }, 0);
    });
  },
  routes,
});

Vue.use(VueRouter);
window.onload = function () {
  if (!localStorage.getItem("vuex")) {
    localStorage.setItem("vuex", JSON.stringify({ cart: [] }));
  }
  if (window.localStorage) {
    if (!localStorage.getItem("firstLoad")) {
      localStorage["firstLoad"] = true;
      window.location.reload();
    } else {
      localStorage.removeItem("firstLoad");
    }
  }
};

new Vue({
  router,
  store,
  created: function () {
    var user = localStorage.getItem("user");

    if (!user) {
      this.$store.commit("changeUser", undefined);
      return;
    }

    this.$store.commit("changeUser", JSON.parse(user));
    this.$store.dispatch("loadUserBalance");
  },
  render: (h) => h(App),
}).$mount("#app");
