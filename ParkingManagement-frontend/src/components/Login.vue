<style>
div {
  margin-bottom: 10px;
}
label {
  display: table;
  width: 200px;
  text-align: center;
}
input{
  width:300px
}
button{
  border-radius:4px;
  background-color:#80A7A4;
  border:2px solid #51706D;
}
</style>
<template>
<div>
<center>
  <h1>ParkingPlus&nbsp<img src="https://static.wixstatic.com/media/cef2a4_5488e99ac9bd419e9f05722ba154e95a~mv2.png/v1/fill/w_560,h_554,al_c,lg_1,q_85,enc_auto/ParkPlus%20Icon%20CMYK%20highres.png" alt="ParkingPlus Logo" style="width: 80px; height: 80px; margin-right: 20px;"><br><br></h1>
  
  <label>Enter your email:</label>
  <input type="text" v-model="email" placeholder="Email"><br><br>

  <label>Enter your password:</label>
  <input type="text" v-model="password" placeholder="Password"><br><br>

  <button @click="login(email, password)">Login</button>

  <label><br><br>Don't have an account yet?</label>
  <button @click="createAccount()">Create Account</button>

</center>
</div>
</template>
<script src = "./loginBehavior.js"></script>
