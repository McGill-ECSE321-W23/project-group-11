<template>
  <div id="temporarySpots">
    <h2>Temporary Parking</h2>
    <form @submit.prevent="submitTemporarySpot">
      <label for="car">Select Car:</label>
      <select id="car" v-model="selectedCar" required>
        <option value="" disabled selected>Select your car</option>
        <option v-for="car in cars" :key="car.id" >
          {{ car.licensePlate }}
        </option>
      </select>
      <br /><br />
      <label for="date">Select Date:</label>
        <input type="date" id="date" v-model="selectedDate" />
      <br /><br />
        <label for="startTime">Start Time (HHmm):</label>
        <input type="text" id="startTime" v-model="startTime" required />
      <br /><br />
        <label for="duration">Duration (multiple of 15):</label>
        <input type="int" id="duration" v-model="selectedDuration" @change="convertToProperDuration" required />
      <br /><br />
      <h3>Total to Pay: ${{ total }}</h3>
      <button type="submit">Confirm</button>
    </form>
    <p>
      <span class="error-message">{{ errorMessage }}</span>
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
    name: 'TemporaryParking',
    data() {
      return {
        selectedDuration: 0,
        duration: 0,
        selectedCar: null,
        cars: [],
        selectedDate: '',
        date: '',
        startTime: '',
        errorMessage: '',
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
          const response = await axiosClient.get('/price/temp/0', {size: this.selectedCar.size, duration: this.duration});
          this.total = response.data;
        } catch (error) {
          console.error('Failed to fetch total amount:', error);
        }
      },
      convertToProperDuration() {
        if (selectedDuration % 15 != 0) {
          this.duration = (selectedDuration/15) + 1;
        } else {
          this.duration = selectedDuration/15;
        }
      },
      async createReservation() {
        try {
          const request = {
            duration: this.duration,
            date: this.date,
            startTime: this.startTime,
            carto: this.selectedCar
          };
          
          localStorage.setItem('tempSpotDto', JSON.stringify(request));
          localStorage.setItem('paymentAmount', this.total);
          this.$router.push({ name: 'PaymentMonthlySpot', params: { tempSpotDto: request, paymentAmount: this.total } });
        } catch (error) {
          console.error('Failed to create reservation:', error);
        }
      },
      mounted() {
        this.fetchCars();
      },
      watch: {
      selectedDate() {
        if (this.selectedDate && this.selectedCar) {
          this.getTotalAmount();
        }
      },
      selectedCar() {
        if (this.selectedDate && this.selectedCar) {
          this.getTotalAmount();
        }
      },
    },
  }
  };
  </script>

  
<style>
  html, body {
    margin: 0;
    height: 100%;
    overflow: hidden;
  }

  #temporarySpots {
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
    text-align: center;
  }

  h3 {
    margin-top: 20px;
    margin-bottom: 50px;
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
    margin-top: 20px;
    display: block;
    width: 100%;
  }

  button:hover {
    background-color: #0069d9;
  }
</style>