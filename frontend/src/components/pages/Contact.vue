<template>
  <div class="contact_section layout_padding">
    <div class="container">
      <div class="contact_main">
        <h1 class="request_text">For any inquiries:</h1>
        <form action="/action_page.php">
          <div class="form-group">
            <input
              type="text"
              class="email-bt"
              placeholder="Name"
              name="Name"
            />
          </div>
          <div class="form-group">
            <input
              type="text"
              class="email-bt"
              placeholder="E-mail"
              name="E-mail"
            />
          </div>
          <div class="form-group">
            <input
              type="text"
              class="email-bt"
              placeholder="Phone Number"
              name="Phone Number"
            />
          </div>
          <div class="form-group">
            <textarea
              class="massage-bt"
              placeholder="Message"
              rows="5"
              id="comment"
              name="Message"
            ></textarea>
          </div>
        </form>
        <div class="send_btn"><a href="#">SEND</a></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ContactComponent",
};
</script>
