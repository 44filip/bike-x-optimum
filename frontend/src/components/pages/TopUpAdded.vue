<template>
  <div v-if="show" class="popup">
    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
  name: "TopUpAdded",
  data() {
    return {
      show: false,
      message: "Balance added!",
      timeoutId: null,
    };
  },
  methods: {
    showPopup() {
      this.show = true;
      clearTimeout(this.timeoutId);
      this.timeoutId = setTimeout(() => {
        this.show = false;
      }, 1000);
    },
  },
};
</script>

<style scoped>
.popup {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #f9f9f9;
  padding: 40px;
  border: 1px solid #ccc;
  z-index: 1000;
  pointer-events: none;
}
</style>
