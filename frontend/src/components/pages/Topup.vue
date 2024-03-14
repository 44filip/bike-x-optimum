<template>
    <div class ="page-size">
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
    </div>
</template>
<script>
import axios from 'axios';
export default {
    name: "TopupComponent",
    data() {
        return {
            balance: "", // The amount to add to the user's balance

        }
    },
    methods: {
        async addToBalance() {
            var email = JSON.parse(localStorage.getItem('user'))
            var userEmail = email.email;
            // Update the user's balance
            const response = await axios.get(`http://localhost:8081/user/email/${userEmail}`)
            var user = response.data;
            console.log(user);
            user.balance = parseFloat(user.balance) + parseFloat(this.balance);
            console.log(user);
            // Send the updated user information to the backend

            
            await this.updateUserInBackend(user);
            window.location.reload();
            this.$forceUpdate()
        }, async updateUserInBackend(user) {
            try {
                console.log(user);
                const response = await axios.put('http://localhost:8081/update', user, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response.data);
                console.log("funds added");
                // Handle successful update (e.g., show success message)
            } catch (error) {
                console.error(error);
                // Handle error (e.g., show error message)
            }
        },
        updateBalance() {
            // Example: Assume this method is called after a successful top-up operation
            // and `newBalance` is the updated balance value.
            const newBalance = 100; // This should be the actual new balance value

            // Now, commit the new balance to the Vuex store
            this.$store.commit('updateBalance', newBalance);
        }
    }
}
</script>
