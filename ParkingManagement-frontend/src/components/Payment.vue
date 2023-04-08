<template>
  <div id="paymentPage">
    <h2>Payment</h2>
    <h3>Total to Pay: ${{ total }}</h3>
    
      <div class="form-group">
        <label for="cardName">Name on Card:</label>
        <input type="text" id="cardName" v-model="cardName" required />
      </div>

      <div class="form-group">
        <label for="cardNumber">Card Number:</label>
        <input type="text" id="cardNumber" v-model="cardNumber" required />
      </div>

      <div class="form-group">
        <label for="expiryDate">Expiry Date (MM/YY):</label>
        <input type="text" id="expiryDate" v-model="expiryDate" required />
      </div>

      <div class="form-group">
        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" v-model="cvv" required />
      </div>

      <button type="submit">Submit Payment</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      total: 0, // Retrieve this value from the previous page
      cardName: "",
      cardNumber: "",
      expiryDate: "",
      cvv: "",
    };
  },
  methods: {
    submitPayment() {
      // Handle the payment submission here
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