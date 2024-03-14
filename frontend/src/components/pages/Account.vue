<template>
    <div class="form-grou">
        <h1>Change password or delete accoutn</h1>
        <form @submit.prevent="changePassword" class='login-form'>
            <div class="forma">
                <input v-model="password" id="password" class='lf--input form-control' placeholder='Your new password'
                    type='password'>
            </div>
            <div class="forma">
                <input class='lf--submit form-control' type='submit' value='CHANGE PASSWORD'>
            </div>
        </form>
        <form @submit.prevent="deleteAccount" class='login-form'>
            <div class="forma">
                <input class='logout lf--submit form-control' type='submit' value='DELETE ACCOUNT'>
            </div>
        </form>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    name: "AccountComponent",
    data() {
        return {
            password: "",
            userId: JSON.parse(localStorage.getItem('user')).userId // Assuming the user object has an 'id' property
        }
    },
    methods: {
        async deleteAccount() {
            try {
                console.log(this.userId);
                const response = await axios.delete(`http://localhost:8081/delete/id/${this.userId}`);
                this.$store.commit("changeUser", undefined)
                this.$router.push("/")
                window.location.reload();
                this.$forceUpdate()

                console.log(response.data);
                console.log("obrisan");

                // Handle successful deletion (e.g., log out and redirect)
            } catch (error) {
                console.error(error);
                // Handle error (e.g., show error message)
            }
        },
        async changePassword() {
            var email = JSON.parse(localStorage.getItem('user'))
            var userEmail = email.email;
            // Update the user's balance
            const response = await axios.get(`http://localhost:8081/user/email/${userEmail}`)
            var user = response.data;
            console.log(user.password);
            user.password = this.password;
            console.log(this.password);
            console.log(user);
            // Send the updated user information to the backend


            await this.changePasswordInBackend(user);
        }, async changePasswordInBackend(user) {
            try {
                console.log(user);
                const response = await axios.put('http://localhost:8081/update', user, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                console.log(response.data);
                console.log("password changed");
                // Handle successful update (e.g., show success message)
            } catch (error) {
                console.error(error);
                // Handle error (e.g., show error message)
            }
        }

    }
}
</script>
