import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        cart: [],
    },
    mutations: {
        addToCart(state, product) {
            const index = state.cart.findIndex(cartItem => cartItem.id === product.id);
            if (index !== -1) {
                state.cart[index].quantity += 1; // Increment quantity if item already exists
            } else {
                state.cart.push({ ...product, quantity: 1 }); // Add new item with quantity 1
            }
        },
        emptyCart(state) {
            state.cart = [];
        },
        removeItem(state, item) {
            const index = state.cart.findIndex(cartItem => cartItem.id === item.id);
            if (index !== -1) {
                state.cart.splice(index, 1);
            }
        },
        incrementQuantity(state, item) {
            const index = state.cart.findIndex(cartItem => cartItem.id === item.id);
            if (index !== -1) {
                state.cart[index].quantity += 1; // Increment quantity
            }
        },
        decrementQuantity(state, item) {
            const index = state.cart.findIndex(cartItem => cartItem.id === item.id);
            if (index !== -1 && state.cart[index].quantity > 1) {
                state.cart[index].quantity -= 1; // Decrement quantity, but not below 1
            }
        }
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
        }
    },
    plugins: [createPersistedState()],
    modules: {
        cart: {
            namespaced: true
        }
    },
    created: {
        emptyCart(state) {
            state.cart = [];
        }
    },
    getters: {
        cartQuantity: state => {
            return state.cart.reduce((total, item) => total + item.quantity, 0);
        }
    }
});