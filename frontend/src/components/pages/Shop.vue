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
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                </tr>
                            </thead>
                            <!-- <td>
                                <tr v-for="(item, index) in cart" :key="index">
                                    <th scope="col">{{ item.name }}</th>
                                    <th scope="col">{{ item.price }}</th>
                                </tr>
                            </td> -->
                        </table>
                    </div>
                </div>

            </div>
            <div id="emptyCart" bis_skin_checked="1">
                <!-- <h2 v-if="cart.length === 0">Your shopping cart is empty!</h2> -->
            </div>
        </div>

    </div>
</template>

<script>

export default {
    name: "ShopComponent"
}
</script>