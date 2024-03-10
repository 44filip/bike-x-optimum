<template>
    <div v-if="show" class="popup">
        <p>{{ message }}</p>
    </div>
</template>

<script>
export default {
    data() {
        return {
            show: false,
            message: 'Item added to cart!',
        };
    },
    methods: {
        showPopup() {
            this.show = true;
            setTimeout(() => {
                this.show = false;
            }, 1000); // Hide after 1 second
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
}
</style>
