<template> 
    <div class="form-grou">
        <h1>Register</h1>
        <form class='login-form'>
            <div class="forma">
                <input id="email" class='lf--input form-control' placeholder='E-mail' type='text'>
            </div>
            <div class="forma">
                <input id="password" class='lf--input form-control' placeholder='Password' type='password'>
            </div>
            <div class="forma">
                <input class='lf--submit form-control' type='submit' value='REGISTER'>
            </div>
        </form>
        <a class='lf--forgot'><router-link to="/login">Already have an account?<br>Login HERE</router-link></a>
    </div>
</template>
<script>
    export default {
        name: "RegisterComponent"}
</script>