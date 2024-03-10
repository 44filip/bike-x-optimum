<template>
    <div>
        <!-- banner section start -->
        <div class="header_section header_bg">
            <div class="banner_section layout_padding">
                <div id="main_slider" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="best_text">Best</div>
                                        <div class="image_1"><img src="images/main-img.png"></div>
                                    </div>
                                    <div class="col-md-5">
                                        <h1 class="banner_taital">New Model Cycle</h1>
                                        <p class="banner_text">It is a long established fact that a reader will be
                                            distracted by the readable content. </p>
                                        <div class="contact_bt"><router-link to="/bikes">Shop Now</router-link>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="best_text">Best</div>
                                        <div class="image_1"><img src="images/img-1.png"></div>
                                    </div>
                                    <div class="col-md-5">
                                        <h1 class="banner_taital">New Model Cycle</h1>
                                        <p class="banner_text">It is a long established fact that a reader will be
                                            distracted by the readable content. </p>
                                        <div class="contact_bt"><a href="contact.html">Shop Now</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-7">
                                        <div class="best_text">Best</div>
                                        <div class="image_1"><img src="images/img-1.png"></div>
                                    </div>
                                    <div class="col-md-5">
                                        <h1 class="banner_taital">New Model Cycle</h1>
                                        <p class="banner_text">It is a long established fact that a reader will be
                                            distracted by the readable content. </p>
                                        <div class="contact_bt"><a href="contact.html">Shop Now</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- banner section end -->
        <div class="cycle_section layout_padding">
            <div class="container">
                <h1 class="cycle_taital">Our most popular cycles</h1>
                <p class="cycle_text">It is a long established fact that a reader will be distracted. </p>
                <div class="cycle_section_2 layout_padding">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="box_main">
                                <h6 class="number_text">01</h6>
                                <div class="image_2"><img src="images/img-2.png"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h1 class="cycles_text">Cycle</h1>
                            <p class="lorem_text">It is a long established fact that a reader will be distracted by the
                                readable content of a page when looking at its layout. The point of using Lorem Ipsum is
                                that it has a more-or-less normal distribution of letters.</p>
                            <div class="btn_main">
                                <div class="buy_btn"><router-link to="/bikes">Order</router-link></div>
                                <h4 class="price_tex">Price <span style=" color: #f7c17b">$</span> <span
                                        style=" color: #325662">220</span></h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cycle_section_3 layout_padding">
                    <div class="row">
                        <div class="col-md-6">
                            <h1 class="cycles_text">Stylish</h1>
                            <p class="lorem_text">It is a long established fact that a reader will be distracted by the
                                readable content of a page when looking at its layout. The point of using Lorem Ipsum is
                                that it has a more-or-less normal distribution of letters.</p>
                            <div class="btn_main">
                                <div class="buy_btn"><router-link to="/bikes">Order</router-link></div>
                                <h4 class="price_tex">Price <span style=" color: #f7c17b">$</span> <span
                                        style=" color: #325662">280</span></h4>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="box_main_3">
                                <h6 class="number_text_2">02</h6>
                                <div class="image_2"><img src="images/img-3.png"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cycle_section_2 layout_padding">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="box_main_3">
                                <h6 class="number_text_2">03</h6>
                                <div class="image_2"><img src="images/img-4.png"></div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <h1 class="cycles_text">Modern</h1>
                            <p class="lorem_text">It is a long established fact that a reader will be distracted by the
                                readable content of a page when looking at its layout. The point of using Lorem Ipsum is
                                that it has a more-or-less normal distribution of letters.</p>
                            <div class="btn_main">
                                <div class="buy_btn"><router-link to="/bikes">Order</router-link></div>
                                <h4 class="price_tex">Price <span style=" color: #f7c17b">$</span> <span
                                        style=" color: #325662">280</span></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "CycleComponent"
}

</script>