<template>
  <div>
    <center>
      <h2> Profile modification </h2>
      <label>Enter your new email (optional):</label>
      <input type="text" v-model="newAccountEmail" placeholder="New Email"><br><br>
  
      <label>Enter your new password:</label>
      <input type="password" v-model="newAccountPassword" placeholder="New Password"><br><br>

      <label>Confirm your new password:</label>
      <input type="password" v-model="confirmation" placeholder="Confirm New Password"><br><br>
      
      <button @click="modifyAccount(newAccountEmail, newAccountPassword,confirmation)">Change settings</button>
  
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
    name: 'Home',
    props: ['email'],
    data() {
      return {
        userEmail: this.email || localStorage.getItem('email'),
        newAccountEmail: '',
        newAccountPassword :'',
        confirmation:'',

      };
    },
    methods: {
      modifyAccount() {
        if(this.confirmation != this.newAccountPassword) {
          this.errorMessage = 'Passwords do not match. Please try again.';
          return; 
        }
        if(this.newAccountEmail != null || this.newAccountEmail !=''){
          const request = {email: this.newAccountEmail, password: this.newAccountPassword, loginStatus: true};
          axiosClient.put('/accounts', request)
          .then((response) => {            
            this.email = '';
            this.password = '';
            this.confirmPassword = '';
            this.errorMessage = '';
          })
          .catch((err) => {
            this.errorMessage = "Failed to update Account" + err.response.data;
          }) 
        }
        else {
          const request = {email: this.userEmail, password: this.newAccountPassword, loginStatus: true};
          axiosClient.put('/accounts', request)
          .then((response) => {            
            this.email = '';
            this.password = '';
            this.confirmPassword = '';
            this.errorMessage = '';
          })
          .catch((err) => {
            this.errorMessage = "Failed to update Account" + err.response.data;
          }) 
        }

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