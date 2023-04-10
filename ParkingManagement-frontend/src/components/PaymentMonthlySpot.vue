<template>
    <div id="paymentPage">
      <h2>Payment</h2>
      <h3>Total to Pay: ${{ total }}</h3>
      <form @submit.prevent="submitPayment">
        <div class="form-group">
          <label for="cardName">Name on Card:</label>
          <input type="text" v-model="cardName" required />
        </div>
  
        <div class="form-group">
          <label for="cardNumber">Card Number:</label>
          <input type="text" v-model="cardNumber" required />
        </div>
  
        <div class="form-group">
          <label for="expiryDate">Expiry Date (MM/YY):</label>
          <input type="text" v-model="expiryDate" required />
        </div>
  
        <div class="form-group">
          <label for="cvv">CVV:</label>
          <input type="text" v-model="cvv" required />
        </div>
  
        <button type="submit" @click="submitPayment(cardNumber,expiryDate,cvv)">Submit Payment</button>
        <p style="color:red"><br><br>{{ errorMessage }}</p>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import config from '../../config';
  const axiosClient = axios.create({baseURL: config.dev.backendBaseUrl});
  
  export default {
    name: "payment",
    data() {
      return {
        requestBody: localStorage.getItem('monthlySpotDto'),
        total: localStorage.getItem('paymentAmount'),
        cardName: "",
        cardNumber: "",
        expiryDate: "",
        cvv: "",
        errorMessage: ""
      };
    },
    methods: {
      submitPayment: function (cardNumber,expiryDate,cvv) {
        if(expiryDate.length !== 4){
          this.errorMessage = "Expiry Date must be 4 digits";
          return;
        }
        if(cvv.length !== 3){
          this.errorMessage = "cvv must be 3 digits";
          return;
        }
        axiosClient.get('/payment/'+ cardNumber + '').then(() => {
            localStorage.getItem()
            axiosClient.post('/reservedspot/book',requestBody)
            .catch((error) => {
                this.errorMessage = "Please try again: " + error.response.data;
            })
            localStorage.removeItem('tempSpotDto'); //remove the item after you used it
            this.$router.push({name: 'PaymentSuccess'});
        })
        .catch((error) => {
          this.errorMessage = "Please try again: " + error.response.data;
        })
      },
    },
  };
  </script>
  <style>
  
    #paymentPage {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      color: #2c3e50;
      max-width: 500px;
      margin: 0 auto;
    }
  
    .form-group {
      margin-bottom: 20px;
    }
  
    .form-group-inline {
      display: flex;
      justify-content: space-between;
    }
  
    label {
      display: block;
      margin-bottom: 5px;
    }
  
    h2 {
      margin-bottom: 30px;
      font-weight: 500;
      font-size: 50px; /* Increased font size */
    }
  
    h3 {
      margin-bottom: 30px;
      margin-top: 60px;
  
    }
  
    input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
  
    button {
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      background-color: #007bff;
      color: white;
      font-weight: bold;
      cursor: pointer;
      margin-top: 40px;
      display: block;
      width: 100%;
    }
  
    button:hover {
      background-color: #0069d9;
    }
  </style>