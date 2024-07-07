<template>
    <div class="page-size">
        <div class="form-grou">
            <h1>Register</h1>
            <form class='login-form' @submit.prevent="performRegister">
                <div class="forma">
                    <input v-model="email" id="email" class='lf--input form-control' placeholder='E-mail' type='text'>
                </div>
                <div class="forma">
                    <input v-model="password" id="password" class='lf--input form-control' placeholder='Password'
                        type='password'>
                </div>
                <div class="forma">
                    <input class='lf--submit form-control' type='submit' value='REGISTER'>
                </div>
            </form>
            <a class='lf--forgot'><router-link to="/login">Already have an account?<br>Login HERE</router-link></a>
            <popup-error ref="popup"></popup-error>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import PopupError from './PopupError'
export default {
    name: "RegisterComponent",
    data() {
        return {
            email: "",
            password: ""
        }
    }, components: {
        PopupError
    }
    ,
    methods: {
        async performRegister() {
            try {
                const user = {
                    email: this.email,
                    password: this.password,
                    balance: 0,
                    role: 'user'
                };
                const response = await axios.post('http://localhost:8081/addUser', user);
                console.log(response.data);
                this.$router.push('/registerdLogin');
            } catch (error) {
                this.$refs.popup.showPopup();
            }
        }
    }
}
</script>