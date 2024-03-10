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
                    <div class="container-fluid table-wrap" bis_skin_checked="1">
                        <table id="table" class="table table-bordered ">
                            <thead>
                                <tr v-if="cart.length != 0" class="shopTr">
                                    <th scope="col">Name</th>
                                    <th scope="col">Item</th>
                                    <th scope="col">Price</th>

                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="shopTr" v-for="item in cart" :key="item.id">
                                    <td scope="col">{{ item.name }}</td>
                                    <td scope="col"><img :src=item.img class="card-img-top cat-pic"></td>
                                    <td scope="col"><span style=" color: #f7c17b">$</span> <span style=" color: #325662">{{
                                        item.price }}</span></td>
                                    <td scope="col">{{ item.quantity }}</td>
                                    <td scope="col">
                                        <div class="remove_btn"><a href="javascript:void(0);"
                                                @click="removeFromCart(item)">Remove</a></div>
                                    </td>

                                </tr>
                            </tbody>
                        </table>

                    </div>

                    <div v-if="cart.length != 0" class="remove_btn col-md-6"><a href="javascript:void(0);"
                            @click="clearCart">Clear Cart</a></div>
                </div>

            </div>
            <div id="emptyCart" bis_skin_checked="1">
                <h2 v-if="cart.length === 0">Your shopping cart is empty!</h2>
            </div>
            <p v-if="checkoutStatus">{{ checkoutStatus }}</p>
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
        })
    },
    methods: {

        ...mapActions('cart', ['checkout']),
        clearCart() {
            this.$store.commit('emptyCart');
        },
        removeFromCart(item) {
            this.$store.commit('removeItem', item);
        }
    },
    created :{
        clearCart() {
            this.$store.commit('emptyCart');
        }
    }
}
</script>