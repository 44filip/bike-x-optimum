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
                    <div class="image_1"><img src="images/main-img.png" /></div>
                  </div>
                  <div class="col-md-5">
                    <h1 class="banner_taital">New Model Cycle</h1>
                    <p class="banner_text">
                      It is a long established fact that a reader will be
                      distracted by the readable content.
                    </p>
                    <div class="contact_bt">
                      <router-link to="/bikes">Shop Now</router-link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- other carousel items unchanged -->
          </div>
        </div>
      </div>
    </div>
    <!-- banner section end -->

    <div class="cycle_section layout_padding">
      <div class="container">
        <h1 class="cycle_taital">Our most popular cycles</h1>
        <p class="cycle_text">
          It is a long established fact that a reader will be distracted.
        </p>

        <div class="cycle_section_2 layout_padding">
          <div class="row">
            <div class="col-md-6">
              <div class="box_main">
                <h6 class="number_text">01</h6>
                <div class="image_2">
                  <img
                    :src="products[0]?.imgPath || 'images/img-1.png'"
                    alt="Bike 1"
                  />
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <h1 class="cycles_text">{{ products[0]?.model || "Cycle" }}</h1>
              <p class="lorem_text">
                It is a long established fact that a reader will be distracted
                by the readable content of a page when looking at its layout.
                The point of using Lorem Ipsum is that it has a more-or-less
                normal distribution of letters.
              </p>
              <div class="btn_main">
                <div class="buy_btn">
                  <router-link to="/bikes">Order</router-link>
                </div>
                <h4 class="price_tex">
                  Price <span style="color: #f7c17b">$</span>
                  <span style="color: #325662">{{
                    products[0]?.price || 220
                  }}</span>
                </h4>
              </div>
            </div>
          </div>
        </div>

        <div class="cycle_section_3 layout_padding">
          <div class="row">
            <div class="col-md-6">
              <h1 class="cycles_text">{{ products[1]?.model || "Stylish" }}</h1>
              <p class="lorem_text">
                It is a long established fact that a reader will be distracted
                by the readable content of a page when looking at its layout.
                The point of using Lorem Ipsum is that it has a more-or-less
                normal distribution of letters.
              </p>
              <div class="btn_main">
                <div class="buy_btn">
                  <router-link to="/bikes">Order</router-link>
                </div>
                <h4 class="price_tex">
                  Price <span style="color: #f7c17b">$</span>
                  <span style="color: #325662">{{
                    products[1]?.price || 280
                  }}</span>
                </h4>
              </div>
            </div>
            <div class="col-md-6">
              <div class="box_main_3">
                <h6 class="number_text_2">02</h6>
                <div class="image_2">
                  <img
                    :src="products[1]?.imgPath || 'images/img-2.png'"
                    alt="Bike 2"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="cycle_section_2 layout_padding">
          <div class="row">
            <div class="col-md-6">
              <div class="box_main_3">
                <h6 class="number_text_2">03</h6>
                <div class="image_2">
                  <img
                    :src="products[2]?.imgPath || 'images/img-3.png'"
                    alt="Bike 3"
                  />
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <h1 class="cycles_text">{{ products[2]?.model || "Modern" }}</h1>
              <p class="lorem_text">
                It is a long established fact that a reader will be distracted
                by the readable content of a page when looking at its layout.
                The point of using Lorem Ipsum is that it has a more-or-less
                normal distribution of letters.
              </p>
              <div class="btn_main">
                <div class="buy_btn">
                  <router-link to="/bikes">Order</router-link>
                </div>
                <h4 class="price_tex">
                  Price <span style="color: #f7c17b">$</span>
                  <span style="color: #325662">{{
                    products[2]?.price || 250
                  }}</span>
                </h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CycleComponent",
  data() {
    return {
      products: [],
    };
  },
  created() {
    axios
      .get("https://localhost:8443/bikes")
      .then((response) => {
        this.products = response.data;
      })
      .catch((error) => {
        console.error("Error fetching products:", error);
      });
  },
};
</script>
