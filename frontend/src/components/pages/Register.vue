<template> 
    <div class="form-grou">
        <h1>Register</h1>
        <form class='login-form'>
            <div class="forma">
                <input id="email" class='lf--input form-control' placeholder='Email' type='text'>
            </div>
            <div class="forma">
                <input id="username" class='lf--input form-control' placeholder='Username' type='text'>
            </div>
            <div class="forma">
                <input id="password" class='lf--input form-control' placeholder='Password' type='password'>
            </div>
            <div class="forma">
                <input class='lf--submit form-control' type='submit' value='REGISTER'>
            </div>
        </form>
        <a class='lf--forgot'><router-link to="/Login">Already have an account?</router-link></a>
    </div>
</template>
<script>
    export default {
        name: "RegisterComponent"}
</script>