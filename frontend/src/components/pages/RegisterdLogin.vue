<template>
  <div v-role="['unauthorized']" id="emptyCart" bis_skin_checked="1">
    <h2>You have registered, please log in.</h2>
    <div class="remove_btn col-md-6">
      <router-link to="/login">Click here!</router-link>
    </div>
  </div>
</template>
<script>
export default {
  name: "RegisterdLogin",
};
</script>
