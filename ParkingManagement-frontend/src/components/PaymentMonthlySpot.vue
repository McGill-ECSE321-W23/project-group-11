<template>
    <div id="paymentPage">
      <h2>Monthly Spot Payment</h2>
      <h3><span>Total to Pay: {{ total }}</span></h3>

      <div class="header">
        <span class="user-email">{{ userEmail }}</span>
      </div>

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
  
        <button type="submit">Submit Payment</button>
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
    props: ['monthlySpotDto','paymentAmount','email'],
    data() {
      return {
        userEmail: this.email || localStorage.getItem('email'),
        requestBody: this.monthlySpotDto || JSON.parse(localStorage.getItem('monthlySpotDto')), // Use this.monthlySpotDto instead of directly accessing localStorage
        total: this.paymentAmount || localStorage.getItem('paymentAmount'),
        cardName: "",
        cardNumber: "",
        expiryDate: "",
        cvv: "",
        errorMessage: ""
      };
    },
    methods: {
      submitPayment() {
        if (this.expiryDate.length !== 4) {
          this.errorMessage = "Expiry Date must be 4 digits";
          return;
        }
        if (this.cvv.length !== 3) {
          this.errorMessage = "cvv must be 3 digits";
          return;
        }
        axiosClient.get('/payment/' + this.cardNumber + '').then(() => {
            axiosClient.post('/reservedspot/book', this.requestBody).then(() => {
                localStorage.removeItem('monthlySpotDto'); //remove the item after you used it
                this.$router.push({ name: 'PaymentSuccess' });
              })
              .catch((error) => {
                this.errorMessage = "Please try again: " + error.response.data;
              });
          })
          .catch((error) => {
            this.errorMessage = "Please try again: " + error.response.data;
          });
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