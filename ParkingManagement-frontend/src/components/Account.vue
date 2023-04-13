<style>
template {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
  text-align: center;
  padding: 0 20px;
}

h1 {
  font-size: 48px;
  margin-bottom: 30px;
}

.button-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  margin-bottom: 40px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #007bff;
  color: white;
  font-weight: bold;
  cursor: pointer;
  margin-right: 10px;
}

button:hover {
  background-color: #0069d9;
}

div {
  margin-bottom: 10px;
}

label {
  display: table;
  width: 200px;
  text-align: center;
}

input {
  width: 300px
}

</style>

<script>
import axios from 'axios';
import config from '../../config';

const axiosClient = axios.create({
  baseURL: config.dev.backendBaseUrl
});

export default{
    name: 'Account',
    data() {
        return {
          email: '',
          password: '',
          confirmPassword: '',
          errorMessage: '',
          type:'',
        };
      },
    methods: {
      createAccount(){
        if(this.password !== this.confirmPassword){
          this.errorMessage = 'Passwords do not match. Please try again.';
          return; 
        }
          const request = {email: this.email, password: this.password, loginStatus: true};
          axiosClient.post('/accounts', request)
          .then((response) => {
            axiosClient.post('/customer', request)
            .then((response) => {
              this.errorMessage = '';
            })
            .catch((err) => {
              this.errorMessage = "Failed to create customer: " + err.response.data;
            }) 
            this.email = '';
            this.password = '';
            this.confirmPassword = '';
            this.errorMessage = '';
            this.$router.push('/AccountSuccess');
         })
          .catch((err) => {
            this.errorMessage = "Failed to create account: " + err.response.data;
         })
      },
      computed: {
        createAccountButtonDisabled(){
          return !this.email.trim() || !this.password.trim() || !this.confirmPassword.trim();
        },
      }
    }
    };
</script>

<template>
  <div>
    <center>
      <h1>ParkSimple&nbsp<img
          src="https://static.wixstatic.com/media/cef2a4_5488e99ac9bd419e9f05722ba154e95a~mv2.png/v1/fill/w_560,h_554,al_c,lg_1,q_85,enc_auto/ParkPlus%20Icon%20CMYK%20highres.png"
          alt="ParkSimple Logo" style="width: 80px; height: 80px; margin-right: 20px;"><br></h1>
      <h2>Create Account</h2>
      <h4>Welcome to the account creation page</h4><br><br>

      <span v-if="errorMessage" style="color:red">Error: {{errorMessage}} </span>

      <label for="email">Enter your email:</label>
      <input type="email" id="email" name="email" v-model="email"><br><br>

      <label for="password">Enter your password:</label>
      <input type="password" id="password" name="password" v-model="password"><br><br>

      <label for="reenter-password">Re-enter your password:</label>
      <input type="password" id="reenter-password" name="reenter-password" v-model="confirmPassword"><br><br>

      <button type="submit" v-bind:disabled="createAccountButtonDisabled" @click="createAccount()">Create Account</button><br><br>

      <label for="logininstead">Already have an account? </label>
      <a href="/#/"><button id="logininstead">Login Instead</button></a>

    </center>
</div></template>