<template>
  <div class="latest-products" bis_skin_checked="1">
    <div class="container" bis_skin_checked="1">
      <div class="col-md-12" bis_skin_checked="1">
        <div class="section-heading" bis_skin_checked="1">
          <h2>Shopping cart</h2>
        </div>
      </div>
      <div v-if="cart.length != 0" class="row" bis_skin_checked="1">
        <div
          v-role="['user', 'admin']"
          class="row row-cols-1 row-cols-md-3 g-4"
          id="row"
          bis_skin_checked="1"
        >
          <div v-if="cart.length != 0" class="remove_btn col-md-6">
            <a href="javascript:void(0);" @click="clearCart">Clear Cart</a>
          </div>
          <div class="container-fluid table-wrap" bis_skin_checked="1">
            <table id="table" class="table table-bordered">
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
                  <td scope="col">
                    <img :src="item.imgPath" class="card-img-top cat-pic" />
                  </td>
                  <td scope="col">
                    <span style="color: #f7c17b">$</span>
                    <span style="color: #325662">{{
                      parseFloat(item.price * item.quantity).toFixed(2)
                    }}</span>
                  </td>
                  <td scope="col">
                    <button
                      class="minus"
                      @click="decrementQuantity(item)"
                      :disabled="item.quantity <= 1"
                    >
                      -</button
                    >{{ item.quantity
                    }}<button @click="incrementQuantity(item)">+</button>
                  </td>
                  <td scope="col">
                    <div class="remove_btn">
                      <a
                        href="javascript:void(0);"
                        @click="removeFromCart(item)"
                        >Remove</a
                      >
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="checkout">
            <p v-if="cart.length != 0">
              Total Price: <span style="color: #f7c17b">$ </span
              >{{ totalPrice.toFixed(2) }}
            </p>
            <br />
            <div v-if="cart.length != 0" class="remove_btn col-md-6">
              <a href="javascript:void(0);" @click="sendTransactionData"
                >Checkout</a
              >
            </div>
          </div>
        </div>
      </div>
      <div
        v-role="['user', 'admin']"
        v-if="cart.length === 0"
        id="emptyCart"
        bis_skin_checked="1"
      >
        <h2>Your shopping cart is empty!</h2>
      </div>
      <div v-role="['unauthorized']" id="emptyCart" bis_skin_checked="1">
        <h2>Please log in.</h2>
        <div class="remove_btn col-md-6">
          <router-link to="/login">Click here!</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ShopComponent",
  computed: {
    cart() {
      return this.$store.state.cart;
    },
    totalPrice() {
      return this.cart.reduce(
        (sum, item) => sum + item.price * item.quantity,
        0
      );
    },
  },
  methods: {
    clearCart() {
      this.$store.commit("emptyCart");
    },
    removeFromCart(item) {
      this.$store.commit("removeItem", item);
    },
    incrementQuantity(item) {
      this.$store.commit("incrementQuantity", item);
    },
    decrementQuantity(item) {
      this.$store.commit("decrementQuantity", item);
    },
    async sendTransactionData() {
      const user = JSON.parse(localStorage.getItem("user"));
      if (!user) return;

      const userId = user.userId;
      const products = this.cart;

      try {
        // Add transactions
        for (const product of products) {
          for (let i = 0; i < product.quantity; i++) {
            await axios.post("https://localhost:8443/addTransaction", {
              userId: userId,
              bikeId: product.bikeId,
            });
          }
        }

        // Update user balance
        const response = await axios.get(
          `https://localhost:8443/user/email/${user.email}`
        );
        const updatedUser = response.data;
        updatedUser.balance = (
          parseFloat(updatedUser.balance) - parseFloat(this.totalPrice)
        ).toFixed(2);

        await axios.put("https://localhost:8443/update", updatedUser, {
          headers: { "Content-Type": "application/json" },
        });

        this.$store.commit("updateBalance", updatedUser.balance);
        localStorage.setItem("user", JSON.stringify(updatedUser));
        window.dispatchEvent(new Event("balance-updated"));
        this.clearCart();
      } catch (error) {
        console.error("Transaction or balance update failed:", error);
      }
    },
  },
};
</script>
