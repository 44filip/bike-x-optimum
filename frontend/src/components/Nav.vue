<template>
   <div class="header_section header_bg">
      <div class="collapse" id="navbarToggleExternalContent">
         <div class="bg-dark p-4">
            <h5 class="text-white h4">Collapsed content</h5>
            <span class="text-muted">Toggleable via the navbar brand.</span>
         </div>
      </div>
      <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
         <a class="logo"><router-link to="/"><img src="images/logo.png"></router-link></a>

         <form class="form-inline my-2 my-lg-0">
            <ul class="navbar-nav mr-auto">
               <li v-for="item, index in navItems" :key="index" class="nav-item">
                  <a class="nav-link active"><router-link :to="item.path">{{ item.text }}</router-link></a>
               </li>
               <li v-role="['unauthorized']" class="nav-item"><a class="nav-link active"><router-link
                        to="/login">Login</router-link></a></li>
               <li v-role="['admin', 'user']" class="nav-item"><a href="#" class="nav-link active"><router-link
                        to="/account">Account</router-link></a></li>
               <li v-role="['admin', 'user']" class="nav-item"><a href="#" class="nav-link active"><router-link
                        class="gas" to="/topup">Top-up&nbsp;<div>(${{ formattedBalance }})</div></router-link></a></li>
               <li v-role="['admin', 'user']" @click="logout" class="nav-item"><a href="#"
                     class="nav-link active">Logout</a></li>
            </ul>
            <div class="login_menu">
               <ul>
                  <li class="numCart"><router-link to="/shop">
                        <div class="cartF">
                           <div><img id="cartLogo" src="/images/shopping-cart.png"></div>
                           <div class="numCart">{{ cartQuantity }}</div>
                        </div>
                     </router-link></li>
               </ul>
            </div>
         </form>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent"
            aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
         </button>
      </nav>
   </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
   name: "NavComponent",
   props: ["navItems"],
   computed: {
      ...mapGetters([
         'cartQuantity',
         'balance'
      ]), formattedBalance: function () {
         // Use toFixed(2) to round the balance to two decimal places
         return parseFloat(this.balance).toFixed(2);
      }
   }, methods: {
      logout() {
         this.$store.commit("changeUser", undefined)
         this.$router.push("/login")
         window.location.reload();
         this.balance.$forceUpdate()
      }
   }
};

</script>