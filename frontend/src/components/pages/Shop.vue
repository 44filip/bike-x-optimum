<template>
    <div class="latest-products" bis_skin_checked="1">
        <div class="container" bis_skin_checked="1">
            <div class="row" bis_skin_checked="1">
                <div class="col-md-12" bis_skin_checked="1">
                    <div class="section-heading" bis_skin_checked="1">
                        <h2>Shopping cart</h2>
                    </div>
                </div>
                <div class="row row-cols-1 row-cols-md-3 g-4" id="row" bis_skin_checked="1">
                    <div v-if="cart.length != 0" class="remove_btn col-md-6"><a href="javascript:void(0);"
                            @click="clearCart">Clear Cart</a></div>
                    <div class="container-fluid table-wrap" bis_skin_checked="1">
                        <table id="table" class="table table-bordered ">
                            <thead>
                                <tr v-if="cart.length != 0" class="shopTr">
                                    <th scope="col">Name</th>
                                    <th scope="col">Item</th>
                                    <th class="price" scope="col">Price</th>
                                    <th class="quant" scope="col">Quantity</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="shopTr" v-for="item in cart" :key="item.id">
                                    <td scope="col">{{ item.name }}</td>
                                    <td scope="col"><img :src=item.img class="card-img-top cat-pic"></td>
                                    <td scope="col"><span style=" color: #f7c17b">$</span> <span
                                            style=" color: #325662">{{ parseFloat(
                        item.price * item.quantity).toFixed(2) }}</span></td>
                                    <td scope="col"><button class="minus" @click="decrementQuantity(item)"
                                            :disabled="item.quantity <= 1">-</button>{{ item.quantity }}<button
                                            @click="incrementQuantity(item)" :disabled="item.quantity >= 10">+</button>
                                    </td>
                                    <td scope="col">
                                        <div class="remove_btn"><a href="javascript:void(0);"
                                                @click="removeFromCart(item)">Remove</a></div>
                                    </td>

                                </tr>
                            </tbody>
                        </table>

                    </div>
                    <div class="checkout">
                        <p v-if="cart.length != 0">Total Price: ${{ totalPrice.toFixed(2) }}</p><br>
                        <div v-if="cart.length != 0" class="remove_btn col-md-6"><a href="javascript:void(0);"
                                @click="clearCart">Checkout</a></div>
                    </div>

                </div>

            </div>
            <div id="emptyCart" bis_skin_checked="1">
                <h2 v-if="cart.length === 0">Your shopping cart is empty!</h2>
            </div>

        </div>

    </div>
</template>

<script>

import { mapState, mapGetters, mapActions } from 'vuex';
export default {

    name: "ShopComponent",
    computed: {
        cart() {
            return this.$store.state.cart;
        },
        ...mapGetters('cart', {
            products: 'cartProducts',
            total: 'cartTotal'
        }),
        ...mapState('cart', {
            checkoutStatus: state => state.checkoutStatus
        }),
        totalPrice: function () {
            return this.cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
        }
    },
    methods: {

        ...mapActions('cart', ['checkout']),
        clearCart() {
            this.$store.commit('emptyCart');
        },
        removeFromCart(item) {
            this.$store.commit('removeItem', item);
        },
        incrementQuantity(item) {
            this.$store.dispatch('incrementQuantity', item);
        },
        decrementQuantity(item) {
            this.$store.dispatch('decrementQuantity', item);
        }
    },
    created: {
        clearCart() {
            this.$store.commit('emptyCart');
        }
    }
}
</script>