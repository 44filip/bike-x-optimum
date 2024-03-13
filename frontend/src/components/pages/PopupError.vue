<template>
    <div v-if="show" class="popup">
        <p>{{ message }}</p>
    </div>
</template>

<script>
export default {
    name: "PopupError",
    data() {
        return {
            show: false,
            message: 'Account with this email already exists!',
            timeoutId: null, // Initialize timeoutId in the data
        };
    },
    methods: {
        showPopup() {
            this.show = true;
            // Clear any existing timeout
            clearTimeout(this.timeoutId);
            // Set a new timeout
            this.timeoutId = setTimeout(() => {
                this.show = false;
            }, 1500); // Hide after 1 second
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
