<template>
  <div>
    <div class="header">
        <span class="user-email">{{ userEmail }}</span>
      </div>
    <center>
      <h2> Profile modification </h2>
      <label>Enter your current email:</label>
      <input type="text" v-model="accountEmail" placeholder="current Email"><br><br>

      <label>Enter your new password:</label>
      <input type="password" v-model="newAccountPassword" placeholder="New Password"><br><br>

      <label>Confirm your new password:</label>
      <input type="password" v-model="confirmation" placeholder="Confirm New Password"><br><br>
      
      <button type="submit" @click="modifyAccount()">Change settings</button>
  
      <p style="color:red"><br><br>{{ errorMessage }}</p>

  
    </center>
  </div>
</template>

<script>
import axios from 'axios';
  import config from '../../config';

  const axiosClient = axios.create({
    baseURL: config.dev.backendBaseUrl
  });

  export default {
    name: 'AccountSettings',
    props: ['email'],
    data() {
      return {
        accountEmail:'',
        // newAccountEmail: '',
        newAccountPassword :'',
        confirmation:'',
        errorMessage:''
      };
    },
    methods: {
      modifyAccount() {
        if(this.newAccountPassword!=this.confirmation) {
          this.errorMessage = "Not same Passwords";
          return;
        }
        const request = {email: this.accountEmail, password: this.newAccountPassword, loginStatus: true};
        axiosClient.put("/accounts",request)
        .then((response) => {
          this.errorMessage = response.data;
        })
        .catch((err) => {
          this.errorMessage = "Failed To Update" + err.response.data;
        })
    }
  }
  }
</script>

<style>
  h2 {
    margin-bottom: 30px;
    font-weight: 500;
    font-size: 50px;
    text-align: center;
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
  </style>