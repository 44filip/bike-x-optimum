<template>
    <div class="form-grou">
        <h1>Top-up</h1>
        <form @submit.prevent="addToBalance" class='login-form'>
            <div class="forma">
                <input v-model="balance" id="balance" class='lf--input form-control' placeholder='Amount to add $'
                    type='text'>
            </div>
            <div class="forma">
                <input class='lf--submit form-control' type='submit' value='ADD TO BALANCE'>
            </div>
        </form>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: "TopupComponent",
    data() {
        return {
            balance: 0, // The amount to add to the user's balance
            user: JSON.parse(localStorage.getItem('user')) || {}
        }
    },
    methods: {
        async addToBalance() {
            // Update the user's balance
            this.user.balance = parseFloat(this.user.balance) + parseFloat(this.balance);
            console.log(this.user);
            // Send the updated user information to the backend
            await this.updateUserInBackend();
        }, async updateUserInBackend() {
            try {
                const response = await axios.put('http://localhost:8081/update', this.user, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response.data);
                console.log("dodato");
                // Handle successful update (e.g., show success message)
            } catch (error) {
                console.error(error);
                // Handle error (e.g., show error message)
            }
        },
    }
}
</script>
