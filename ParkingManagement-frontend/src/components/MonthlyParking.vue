<template>
  <div id="parkingReservation">
    <h2>Monthly Parking</h2>
    <form>
      <label for="month">Select Date:</label>
      <input type="month" id="month" v-model="selectedMonth" @change="convertMonthYearToInt" required />
      <br /><br />



      <label for="car">Select Car:</label>
      <select id="car" v-model="selectedCar" required>
        <option value="" disabled selected>Select your car</option>
        <option v-for="car in cars" :key="car.id" :value="car">
          {{ car.licensePlate }}
        </option>
      </select>
      <h3>Total to Pay: {{ total }}</h3>
      <br /><br />
      <button v-bind:disabled="createMonthlyDisabled" @click.prevent="createReservation()">Confirm</button>
    </form>
    <p>
      <span style="color:red">{{ errorMessage }}</span>
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
    name: 'MonthlyParking',
    data() {
      return {
        cars: [],
        selectedMonth: '',
        month: 0,
        year: 0,
        selectedCar: null,
        errorMsg: '',
        total: '-'
      };
    },
    methods: {
      async fetchCars() {
        try {
          const response = await axiosClient.get('/cars');
          this.cars = response.data;
        } catch (error) {
          console.error('Failed to fetch cars:', error);
        }
      },
      async getTotalAmount() {
        try {
          const response = await axiosClient.get('/price/month/0');
          this.total = response.data;
        } catch (error) {
          console.error('Failed to fetch total amount:', error);
        }
      },
      convertMonthYearToInt() {
        const dateParts = this.selectedMonth.split('-');
        this.year = parseInt(dateParts[0], 10);
        this.month = parseInt(dateParts[1], 10);
      },
      async createReservation() {
        try {
          const request = {
            month: this.month,
            year: this.year,
            carDto: {
              id: this.selectedCar.id,
              licensePlate: this.selectedCar.licensePlate,
              size: this.selectedCar.size,
              customer: {
                id: this.selectedCar.customer.id
              }
            }
          };
          
          localStorage.setItem('monthlySpotDto', JSON.stringify(request));
          localStorage.setItem('paymentAmount', this.total);
          await this.$router.push({ name: 'PaymentMonthlySpot', params: { monthlySpotDto: request, paymentAmount: this.total } });
        } catch (error) {
          console.error('Failed to create reservation and navigate:', error);
        }
      }
    },
    computed: {
      createMonthlyDisabled() {
        return this.year <= 0 || this.month <= 0;
      },
    },
    mounted() {
      this.fetchCars();
    },
    watch: {
      selectedMonth() {
        if (this.selectedMonth && this.selectedCar) { 
          this.getTotalAmount();
        }
      },
      selectedCar() { 
        if (this.selectedMonth && this.selectedCar) {
          this.getTotalAmount();
        }
      },
    },
  }
</script>
<style>
  html, body {
    margin: 0;
    height: 100%;
    overflow: hidden;
  }

  #parkingReservation {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    max-width: 500px;
    margin: 0 auto;
    justify-content: center;
  }

  h2 {
    margin-bottom: 30px;
    font-weight: 500;
    font-size: 50px;
  }

  h3 {
    margin-top: 30px;
    margin-bottom: 0px;
    font-weight: 400;
    font-size: 35px;
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

  input,
  select {
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
    margin-top: 0px;
    display: block;
    width: 100%;
  }

  button:hover {
    background-color: #0069d9;
  }
</style>
