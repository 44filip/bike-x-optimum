<template>
  <div class="footer">
    <div class="footer_section layout_padding">
      <div class="container-fluid">
        <div class="row foot">
          <div class="col-lg-12 col-sm-6 footer-ikonice">
            <div class="call_text">
              <a
                href="https://maps.app.goo.gl/Gwz5zrjaYUGQ6c6X7"
                target="_blank"
                ><img src="images/map-icon.png" /><span class="padding_left_0"
                  >Location</span
                ></a
              >
            </div>
            <div class="call_text">
              <a href="tel:0123456789"
                ><img src="images/call-icon.png" /><span class="padding_left_0"
                  >Call +012 345/6789</span
                ></a
              >
            </div>
            <div class="call_text">
              <a href="mailto:demo@example.com"
                ><img src="images/mail-icon.png" /><span class="padding_left_0"
                  >demo@example.com</span
                ></a
              >
            </div>
            <div class="call_text">
              <a href="/Bike X Optimum.pdf" target="_blank"
                ><img src="images/toggle-icon.png" /><span
                  class="padding_left_0"
                  >Documentation</span
                ></a
              >
            </div>
            <div class="social_icon">
              <ul>
                <li>
                  <a href="https://facebook.com" target="_blank"
                    ><img src="images/fb-icon1.png"
                  /></a>
                </li>
                <li>
                  <a href="https://twitter.com" target="_blank"
                    ><img src="images/twitter-icon.png"
                  /></a>
                </li>
                <li>
                  <a href="https://linkedin.com" target="_blank"
                    ><img src="images/linkedin-icon.png"
                  /></a>
                </li>
              </ul>
            </div>
          </div>
          <br />
        </div>
        <div class="col-lg-6 col-sm-6 cont">
          <input
            type="text"
            class="email_text"
            placeholder="Enter your e-mail"
            name="Enter your e-mail"
          />
          <div class="subscribe_bt"><a href="#">Subscribe</a></div>
        </div>
      </div>
    </div>
    <!-- footer section end -->
    <!-- copyright section start -->
    <div class="copyright_section">
      <div class="container">
        <p class="copyright_text">Copyright &copy; 2025 - Bike X Optimum -</p>
      </div>
    </div>
    <a
      href="https://www.flaticon.com/free-icons/document"
      title="document icons"
    ></a>
  </div>
</template>
<script>
export default {
  name: "FooterComponent",
};
</script>
