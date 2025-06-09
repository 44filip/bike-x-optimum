import Vue from 'vue';
import Vuex from 'vuex';
import users from './modules/users';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    cart: [],
    balance: 0,
    cartEmptied: false,
  },
  mutations: {
    addToCart(state, product) {
      const index = state.cart.findIndex(cartItem => cartItem.bikeId === product.bikeId);
      if (index !== -1) {
        state.cart[index].quantity += 1;
      } else {
        state.cart.push({ ...product, quantity: 1 });
      }
    },
    emptyCart(state) {
      state.cart = [];
      state.cartEmptied = true;
    },
    removeItem(state, item) {
      const index = state.cart.findIndex(cartItem => cartItem.bikeId === item.bikeId);
      if (index !== -1) {
        state.cart.splice(index, 1);
      }
    },
    incrementQuantity(state, item) {
      const index = state.cart.findIndex(cartItem => cartItem.bikeId === item.bikeId);
      if (index !== -1) {
        state.cart[index].quantity += 1;
      }
    },
    decrementQuantity(state, item) {
      const index = state.cart.findIndex(cartItem => cartItem.bikeId === item.bikeId);
      if (index !== -1 && state.cart[index].quantity > 1) {
        state.cart[index].quantity -= 1;
      }
    },
    setUserBalance(state, balance) {
      state.balance = balance;
    },
    updateBalance(state, newBalance) {
      state.balance = newBalance;
    },
  },
  actions: {
    addToCart({ commit }, product) {
      commit('addToCart', product);
    },
    emptyCart({ commit }) {
      commit('emptyCart');
    },
    removeItem({ commit }, item) {
      commit('removeItem', item);
    },
    incrementQuantity({ commit }, item) {
      commit('incrementQuantity', item);
    },
    decrementQuantity({ commit }, item) {
      commit('decrementQuantity', item);
    },
    loadUserBalance({ commit }) {
      const user = JSON.parse(localStorage.getItem('user'));
      if (user && user.balance) {
        commit('setUserBalance', user.balance);
      }
    },
  },
  plugins: [createPersistedState()],
  modules: {
    users: users
  },
  getters: {
    cartQuantity: state =>
      Array.isArray(state.cart)
        ? state.cart.reduce((total, item) => total + item.quantity, 0)
        : 0,
    cartProducts: state => state.cart,
    balance: state => state.balance,
  }
});