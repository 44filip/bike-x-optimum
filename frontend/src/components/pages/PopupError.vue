<template>
  <div v-if="show" class="popup">
    <p>{{ currentMessage }}</p>
  </div>
</template>

<script>
export default {
  name: "PopupError",
  props: {
    message: {
      type: String,
      default: "An error occurred.",
    },
  },
  data() {
    return {
      show: false,
      currentMessage: this.message,
      timeoutId: null,
    };
  },
  methods: {
    showPopup(newMessage) {
      if (newMessage) {
        this.currentMessage = newMessage;
      }
      this.show = true;
      clearTimeout(this.timeoutId);
      this.timeoutId = setTimeout(() => {
        this.show = false;
      }, 1500);
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