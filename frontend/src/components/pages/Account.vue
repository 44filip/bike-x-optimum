<template>
    <div class="form-grou">
        <h1>Are you sure you want to delete your account?</h1>
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
        }

    }
}
</script>
