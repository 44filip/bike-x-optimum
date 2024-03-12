<template>
    <div class="form-grou">
        <h1>Login</h1>
        <form class='login-form'>
            <div class="forma">
                <input v-model="username" id="email" class='lf--input form-control' placeholder='E-mail' type='text'>
            </div>
            <div class="forma">
                <input v-model="password" id="password" class='lf--input form-control' placeholder='Password'
                    type='password'>
            </div>
            <div class="forma">
                <input @click="performLogin" class='lf--submit form-control' type='submit' value='LOGIN'>
            </div>
        </form>
        <a class='lf--forgot'><router-link to="/register">Don't have an account?<br>Register HERE</router-link></a>
    </div>
</template>
<script>
import { mapGetters } from "vuex"
export default {
    name: "LoginComponent",
    data() {
        return {
            error: "",
            username: "",
            password: ""
        }
    }, computed: {
        ...mapGetters(['users', 'role'])
    },
    methods: {
        performLogin() {
            this.error = ""

            if (!this.username) {
                this.error = "Email is required.";
                return;
            }

            if (!this.password) {
                this.error = "Password is required.";
                return;
            }

            let user = this.users.find(x => x.username == this.username &&
                x.password == this.password);

            if (!user) {
                this.error = "Wrong credentials."
                return
            }

            let localStorageItem = {
                username: user.username,
                role: user.role
            }
            localStorage.setItem("user", JSON.stringify(localStorageItem));

            this.$store.commit("changeUser", localStorageItem)
            this.$router.push("/shop")

            window.location.reload();
            this.$forceUpdate()
        }
    }
}
</script>