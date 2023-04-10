<template>
  <div>
    <center>
      <h1>ParkingPlus&nbsp<img src="https://static.wixstatic.com/media/cef2a4_5488e99ac9bd419e9f05722ba154e95a~mv2.png/v1/fill/w_560,h_554,al_c,lg_1,q_85,enc_auto/ParkPlus%20Icon%20CMYK%20highres.png" alt="ParkingPlus Logo" style="width: 80px; height: 80px; margin-right: 20px;"><br><br></h1>
      
      <label>Enter your email:</label>
      <input type="text" v-model="accountEmail" placeholder="Email"><br><br>
  
      <label>Enter your password:</label>
      <input type="text" v-model="accountPassword" placeholder="Password"><br><br>
      
      <button @click="login(accountEmail, accountPassword)">Login</button>
  
      <p style="color:red"><br><br>{{ errorMessage }}</p>
  
      <label><br><br>Don't have an account yet?</label>
      <a href="/#/CreateAccount"><button>Create Account</button></a>
  
      <div class="manager-signin-container">
        <a href="/#/ManagerLogin">
          <button class="manager-signin-btn">Manager Sign In</button>
        </a>
      </div>
  
    </center>
  </div>
</template>





<script>

import axios from 'axios';
import config from '../../config';
const axiosClient = axios.create({baseURL: config.dev.backendBaseUrl});


export default {
  name: "Login",
  data() {
    return {errorMessage: ''};
  },

  methods: {

    /**
     * login an account
     */
    login: function (accountEmail, accountPassword) {
      axiosClient.post('/login', {email: accountEmail, password: accountPassword}).then(() => {
         this.$router.push('/home');
        })
        .catch((error) => {
          this.errorMessage = "Please try again: " + error.response.data;
        })
    },


  }
}

</script>





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
  button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    font-weight: bold;
    cursor: pointer;
    margin-top: 0px;
    display: block;
    width: 10%;
  }
  .manager-signin-container{
    margin-top: 40px;
  }
  .manager-signin-btn {
    background-color: #28a745;
  }
  .manager-signin-btn:hover {
    background-color: #218838;
  }
  
</style>