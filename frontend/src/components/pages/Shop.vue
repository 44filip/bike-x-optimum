<template>
    <div class="latest-products" bis_skin_checked="1">
        <div class="container" bis_skin_checked="1">
            <div class="col-md-12" bis_skin_checked="1">
                <div class="section-heading" bis_skin_checked="1">
                    <h2>Shopping cart</h2>
                </div>
            </div>
            <div v-if="cart.length != 0" class="row" bis_skin_checked="1">

                <div v-role="['user', 'admin']" class="row row-cols-1 row-cols-md-3 g-4" id="row" bis_skin_checked="1">
                    <div v-if="cart.length != 0" class="remove_btn col-md-6"><a href="javascript:void(0);"
                            @click="clearCart">Clear Cart</a></div>
                    <div class="container-fluid table-wrap" bis_skin_checked="1">
                        <table id="table" class="table table-bordered ">
                            <thead>
                                <tr v-if="cart.length != 0" class="shopTr">
                                    <th class="th_name" scope="col">Name</th>
                                    <th class="th_item" scope="col">Item</th>
                                    <th class="price" scope="col">Price</th>
                                    <th class="quant" scope="col">Quantity</th>
                                    <th class="th_remove" scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="shopTr" v-for="item in cart" :key="item.id">
                                    <td scope="col">{{ item.model }}</td>
                                    <td scope="col"><img :src=item.imgPath class="card-img-top cat-pic"></td>
                                    <td scope="col"><span style=" color: #f7c17b">$</span> <span
                                            style=" color: #325662">{{ parseFloat(
                item.price * item.quantity).toFixed(2) }}</span></td>
                                    <td scope="col"><button class="minus" @click="decrementQuantity(item)"
                                            :disabled="item.quantity <= 1">-</button>{{ item.quantity }}<button
                                            @click="incrementQuantity(item)">+</button>
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
                        <p v-if="cart.length != 0">Total Price: <span style=color:#f7c17b>$ </span>{{
                totalPrice.toFixed(2) }}</p><br>
                        <div v-if="cart.length != 0" class="remove_btn col-md-6"><a href="javascript:void(0);"
                                @click="sendTransactionData">Checkout</a></div>
                    </div>

                </div>

            </div>
            <div v-role="['user', 'admin']" v-if="cart.length === 0" id="emptyCart" bis_skin_checked="1">
                <h2>Your shopping cart is empty!</h2>
            </div>
            <div v-role="['unauthorized']" id="emptyCart" bis_skin_checked="1">
                <h2>Please log in.</h2>
                <div class="remove_btn col-md-6"><a><router-link to="/login">Click here!</router-link></a></div>
            </div>
        </div>

    </div>
</template>

<script>
import axios from 'axios';
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
            return this.$store.state.cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
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
        },
        async removeFromBalance() {
            var email = JSON.parse(localStorage.getItem('user'))
            var userEmail = email.email;
            // Update the user's balance
            const response = await axios.get(`http://localhost:8081/user/email/${userEmail}`)
            var user = response.data;
            console.log(user);
            console.log(user.balance);
            console.log(this.totalPrice);
            user.balance = (parseFloat(user.balance) - parseFloat(this.totalPrice)).toFixed(2);

            // Send the updated user information to the backend


            await this.updateUserInBackend(user);
        }
        ,
        async sendTransactionData() {
            // Retrieve user ID from local storage
            const userId = JSON.parse(localStorage.getItem('user')).userId;

            // Retrieve product information from Vuex store
            const products = this.$store.state.cart;
            console.log(this.$store.state.cart);
            console.log(products);

            // Calculate the total price of items in the cart
            const totalPrice = products.reduce((sum, item) => sum + (item.price * item.quantity), 0);

            // Loop through each product in the cart
            for (const product of products) {
                // Calculate the number of transactions needed for this product
                const numberOfTransactions = product.quantity;
                const currentDate = new Date();
                const formattedDate = currentDate.toISOString().replace('T', ' ').substring(0, 19);

                // Send a transaction for each unit of the product
                for (let i = 0; i < numberOfTransactions; i++) {
                    // Prepare transaction data for each unit
                    const transaction = {
                        userId: userId,
                        bikeId: product.bikeId
                    };
                    console.log(userId);
                    console.log(product.bikeId);
                    console.log(formattedDate);
                    // Send transaction data to the server
                    try {
                        const response = await axios.post('http://localhost:8081/addTransaction', transaction);
                        this.removeFromBalance();
                        console.log("proslo");
                        console.log(response.data);
                        // Handle successful transaction (e.g., clear cart, show success message)
                    } catch (error) {
                        console.error(error);
                        // Handle error (e.g., show error message)
                    }
                }
            }

            // Deduct the total price from the user's balance
            var email = JSON.parse(localStorage.getItem('user'))
            var userEmail = email.email;
            const response = await axios.get(`http://localhost:8081/user/email/${userEmail}`);
            var user = response.data;
            user.balance = parseFloat(user.balance) - parseFloat(totalPrice);

            // Update the user's balance in the backend
            try {
                const responseBalance = await axios.put('http://localhost:8081/update', user, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log("proslo");
                console.log(responseBalance.data);
                this.clearCart(); // Clear the cart after successful transaction
            } catch (error) {
                console.error(error);
                // Handle error (e.g., show error message)
            }
        },
        async updateUserInBackend(user) {
            try {
                console.log(user);
                const response = await axios.put('http://localhost:8081/update', user, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response.data);
                console.log("funds added");
                this.$store.commit('updateBalance', user.balance);
                localStorage.setItem('user', JSON.stringify(user));
                // Handle successful update (e.g., show success message)
            } catch (error) {
                console.error(error);
                // Handle error (e.g., show error message)
            }
        }

    },
    created: {
        clearCart() {
            this.$store.commit('emptyCart');
        }
    }
}
</script>