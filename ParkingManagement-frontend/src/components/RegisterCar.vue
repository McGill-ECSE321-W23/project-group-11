<template>
  <div id="registerCar">
     <h2>Register Car</h2>
    <form @submit.prevent="submitCarRegistration">
      <label for="licensePlate">License Plate:</label>
      <input type="text" id="licensePlate" v-model="licensePlate" required />
      <br /><br />
      <label for="carSize">Select Car Size:</label>
      <select id="carSize" v-model="selectedSize" required>
        <option value="" disabled selected>Select car size</option>
        <option v-for="size in carSizes" :key="size" :value="size">
          {{ size }}
        </option>
      </select>
      <br /><br />
      <a href="/#/Home" class="button-link">Home</a>
      <button class="green-button" v-bind:disabled="submit" @click.prevent="createCar()">Confirm</button>
    </form>
    <p>
      <span class="error-message">{{ errorMessage }}</span>
      <span class="success-message">{{ successMessage }}</span>
    </p>
  </div>
</template>

<script>
import axios from 'axios';
import config from '../../config';

const axiosClient = axios.create({
  baseURL: config.dev.backendBaseUrl
});

export default {
  props: ['email'],
  data() {
    return {
      userEmail: localStorage.getItem('email'),
      licensePlate: "",
      selectedSize: "",
      carSizes: ["Regular", "Large"],
      errorMessage: "",
      successMessage: "",
    };
  },
  methods: {
    async createCar() {
      if (!this.licensePlate || !this.selectedSize) {
        this.errorMessage = "Please fill out all the fields.";
        return;
      }
    
      const userEmail = this.userEmail;
      const customerId = await this.fetchCustomerIdByEmail(userEmail);
    
      if (!customerId) {
        this.errorMessage = "Failed to fetch customer ID.";
        return;
      }
    
      const carData = {
        size: this.selectedSize,
        licensePlate: this.licensePlate,
        customer: {
          id: customerId
        }
      };
    
      try {
        await axiosClient.post('/car/create', carData);
        this.errorMessage = "";
        this.successMessage = `Successfully added car: ${this.licensePlate}`;
      } catch (error) {
        console.error('Failed to create car:', error);
        this.errorMessage = "Failed to create car.";
      }
    },
    async fetchCustomerIdByEmail(email) {
      try {
        const response = await axiosClient.get(`/customer/${email}`);
        return response.data.id;
      } catch (error) {
        console.error('Failed to fetch customer ID:', error);
        return null;
      }
    }
  }
};
</script>
<style>
  html, body {
    margin: 0;
    height: 100%;
    overflow: hidden;
  }
  #registerCar {
    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    color: #333;
    background: #f9f9f9;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    padding: 40px;
    box-sizing: border-box;
  }

  h2 {
    margin-bottom: 30px;
    font-weight: 500;
  }

  label {
    margin-bottom: 5px;
    font-weight: 500;
  }

  input,
  select {
    padding: 12px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background-color: #fff;
    color: #333;
    outline: none;
  }

  input:focus,
  select:focus {
    box-shadow: 0 0 0 2px #007aff;
  }

  button {
    background-color: #007aff;
    color: white;
    padding: 12px 20px;
    font-size: 16px;
    font-weight: 500;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  button:hover {
    background-color: #0058d3;
  }

  .error-message {
    color: #ff3b30;
    margin-top: 20px;
    font-weight: 500;
  }

  .green-button {
    background-color: #28a745;
    color: white;
    padding: 12px 20px;
    font-size: 16px;
    font-weight: 500;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .green-button:hover {
    background-color: #218838;
  }

  .success-message {
    color: #28a745;
    margin-top: 20px;
    font-weight: 500;
    font-size: 18px;
    text-align: center;
  }

  .button-link {
    display: inline-block;
    padding: 12px 20px;
    font-size: 16px;
    font-weight: 500;
    color: white;
    background-color: #007aff;
    border: none;
    border-radius: 5px;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .button-link:hover {
    background-color: #0058d3;
  }

</style>