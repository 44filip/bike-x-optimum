import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Cycle from './components/pages/Cycle.vue'
import Bikes from './components/pages/Bikes.vue'
import Contact from './components/pages/Contact.vue'
import Login from './components/pages/Login.vue'
import Register from './components/pages/Register.vue'
import Shop from './components/pages/Shop.vue'

Vue.config.productionTip = false

var routes = [
  { path: "/cycle", component: Cycle },
  { path: "/", component: Cycle },
  { path: "/bikes", component: Bikes },
  { path: "/contact", component: Contact },
  { path: "/login", component: Login },
  { path: "/register", component: Register },
  { path: "/shop", component: Shop }

]

var router = new VueRouter({
  scrollBehavior() {
    return { x: 0, y: 0 }; // Scrolls to the top of the page
  },
  routes
})
Vue.use(VueRouter)


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
