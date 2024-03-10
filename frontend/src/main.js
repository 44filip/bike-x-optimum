import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Cycle from './components/pages/Cycle.vue'
import Bikes from './components/pages/Bikes.vue'
import Contact from './components/pages/Contact.vue'
import Login from './components/pages/Login.vue'
import Register from './components/pages/Register.vue'
import Shop from './components/pages/Shop.vue'
import store from './store'

Vue.config.productionTip = false

var routes = [
  //{ path: "/cycle", component: Cycle },
  { path: "/", component: Cycle },
  { path: "/bikes", component: Bikes },
  { path: "/contact", component: Contact },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  { path: "/shop", component: Shop }

]

var router = new VueRouter({
  scrollBehavior: () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        window.scrollTo(0, 0);
        resolve({ x: 0, y: 0 });
      }, 0);
    });
  },
  routes
});

Vue.use(VueRouter)
window.onload = function () {
  if (!localStorage.getItem('vuex')) {
    // If the key does not exist, set the initial value
    localStorage.setItem('vuex', JSON.stringify({ "cart": [] }));
  }
  if (window.localStorage) {
    if (!localStorage.getItem('firstLoad')) {
      localStorage['firstLoad'] = true;
      window.location.reload();
    } else {
      localStorage.removeItem('firstLoad');
    }
  }
}
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
