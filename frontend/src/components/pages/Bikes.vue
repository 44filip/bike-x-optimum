<template>
    <div>
        <div class="about_section layout_padding">
            <div class="container">
                <h1 class="about_taital">About our Bike X Store</h1>
                <p class="about_text">It is a long established fact that a reader will be distracted by the readable
                    content
                    of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less
                    normal distribution of letters.
                </p>
            </div>
        </div>
        <div class="container">
            <input id="searchBar" type="text" v-model="searchQuery" placeholder="Search for products...">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <div v-for="product in products" :key="product.id" class="col-md-4">
                    <div class="card">
                        <img :src=product.imgPath class="card-img-top" alt="img-1.png">
                        <div class="card-body">
                            <h5 class="card-title">{{ product.model }}</h5>
                            <div class="btn_main">
                                <div class="buy_bt"><a href="javascript:void(0);" @click="addToCart(product)">Add to
                                        Cart</a>

                                </div>

                                <div>
                                    <h4 class="price_text">Price <span style=" color: #f7c17b">$</span> <span
                                            style=" color: #325662">{{ product.price }}</span></h4>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div v-if="products == 0" id="emptyCart" bis_skin_checked="1">
                    <h2>There are no matching items!</h2>
                </div>
                <popup class="popup" ref="popup"></popup>
            </div>
        </div>

    </div>
</template>

<script>
import Popup from './PopupCart'
import axios from 'axios';
import { mapGetters } from 'vuex';
export default {
    components: {
        Popup
    },
    name: "BikesComponent",
    data() {
        return {
            products: [],
            searchQuery: ''
        }
    },
    methods: {
        addToCart(product) {
            this.$store.dispatch('addToCart', product);
            this.$refs.popup.showPopup();
        },
        fetchProducts() {
            axios.get('https://localhost:8443/bikes')
                .then(response => {
                    this.products = response.data.filter(product =>
                        product.model.toLowerCase().includes(this.searchQuery.toLowerCase())
                    );
                    console.log(response.data);
                })
                .catch(error => {
                    console.error("There was an error!", error);
                });
        }
    },
    computed: {
        ...mapGetters([
            'cartQuantity'
        ])
    },
    created() {
        this.fetchProducts();
    },
    watch: {
        searchQuery() {
            this.fetchProducts();
        }
    }

}
</script>
