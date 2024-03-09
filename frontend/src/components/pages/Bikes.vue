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
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <div v-for="product in products" :key="product.id" class="col">
                    <div class="card">
                        <img :src=product.img class="card-img-top" alt="img-1.png">
                        <div class="card-body">
                            <h5 class="card-title">{{ product.name }}</h5>
                            <div class="btn_main">
                                <div class="buy_bt"><a href="javascript:void(0);" @click="addToCart(product)">Buy
                                        Now</a>
                                    
                                </div>

                                <div>
                                    <h4 class="price_text">Price <span style=" color: #f7c17b">$</span> <span
                                            style=" color: #325662">{{ product.price }}</span></h4>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <popup ref="popup"></popup>
            </div>
        </div>
    </div>
</template>

<script>
import Popup from './PopupCart'
export default {
    components: {
        Popup
    },
    name: "BikesComponent",
    props: ['products'],
    methods: {
        addToCart(product) {
            this.$store.dispatch('addToCart', product);
            this.$refs.popup.showPopup();
        }
    }

}
</script>