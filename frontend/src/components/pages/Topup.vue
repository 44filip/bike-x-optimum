<template>
    <div class="page-size">
        <div class="form-grou">
            <h1>Topup</h1>
            <form @submit.prevent="addToBalance" class='login-form'>
                <div class="forma">
                    <input v-model="balance" id="balance" class='lf--input form-control' placeholder='Amount to add ($)'
                        type='text'>
                </div>
                <div class="forma">
                    <input class='lf--submit form-control' type='submit' value='ADD TO BALANCE'>
                </div>
            </form>
        </div>
        <TopUpAdded class="TopUpAdded" ref="TopUpAdded"></TopUpAdded>
    </div>
</template>
<script>
import axios from 'axios';
import TopUpAdded from './TopUpAdded.vue';
export default {
    name: "TopupComponent",
    data() {
        return {
            balance: "",

        }
    },
    components:{
        TopUpAdded
    }
    ,
    methods: {
        async addToBalance() {
            var email = JSON.parse(localStorage.getItem('user'))
            var userEmail = email.email;
            const response = await axios.get(`https://localhost:8443/user/email/${userEmail}`)
            var user = response.data;
            console.log(user);
            user.balance = (parseFloat(user.balance) + parseFloat(this.balance)).toFixed(2);
            console.log(user);


            await this.updateUserInBackend(user);
        }, async updateUserInBackend(user) {
            try {
                console.log(user);
                const response = await axios.put('https://localhost:8443/update', user, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response.data);
                console.log("funds added");
                this.$store.commit('updateBalance', user.balance);
                this.$refs.TopUpAdded.showPopup();
                localStorage.setItem('user', JSON.stringify(user));
            } catch (error) {
                console.error(error);
            }
        },
        updateBalance() {
            const newBalance = 100;

            this.$store.commit('updateBalance', newBalance);
        }
    }
}
</script>
