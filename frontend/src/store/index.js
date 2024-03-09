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
            state.cart.push(product);
        },
        emptyCart(state) {
            state.cart = [];
        },
        removeItem(state, item) {
            const index = state.cart.findIndex(cartItem => cartItem.id === item.id);
            if (index !== -1) {
                state.cart.splice(index, 1);
            }
        }
    },
    actions: {
        addToCart({ commit }, product) {
            commit('addToCart', product);
        },
    },
    plugins: [createPersistedState()],
    modules: {
        cart: {
            namespaced: true
        }
    },
    created:{
        emptyCart(state){
            state.cart = [];
        }
    }
});