<template>
  <div id="temporarySpots">
    <h2>Temporary Parking</h2>
    <form @submit.prevent="submitTemporarySpot">
      <label for="car">Select Car:</label>
      <select id="car" v-model="selectedCar" required>
        <option value="" disabled selected>Select car</option>
        <option v-for="car in availableCars" :key="car.id" :value="car.id">
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
      <label for="duration">Duration:</label>
      <select id="duration" v-model="selectedDuration" required>
        <option value="" disabled selected>Select duration in 15-minute increments</option>
        <option v-for="duration in durations" :key="duration" :value="duration">
          {{ duration }}
        </option>
      </select>
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
    baseURL: config.dev.backendBaseUrl,
  });
  
  export default {
    props: ['smallprice', 'largeprice'],
    data() {
      return {
        availableCars: [],
        selectedCar: null,
        selectedDate: null,
        startTime: null,
        selectedDuration: null,
        durations: Array.from({ length: 24 * 4 }, (_, i) => (i + 1) * 15),
        errorMessage: '',
        total: 0,
      };
    },
    methods: {
      async fetchAvailableCars() {
        try {
          const response = await axiosClient.get('/cars');
          this.availableCars = response.data;
        } catch (error) {
          console.error('Failed to fetch available cars:', error);
        }
      },
      async getTotalAmount() {
        if (!this.selectedDuration || !this.selectedCar || !this.selectedDate || !this.startTime) return;
      
        const selectedCarObject = this.availableCars.find(car => car.id === this.selectedCar);
      
        const priceRequestData = {
          duration: this.selectedDuration,
          date: this.selectedDate,
          startTime: this.startTime + ':00', 
          placeNumber: 21, 
          carDto: {
            size: selectedCarObject.size,
            customer: {
              account: {
                email: "m@gmail.com", 
                password: "password", 
                loginStatus: false
              },
              id: 2 
            },
            licensePlate: selectedCarObject.licensePlate
          },
          size: selectedCarObject.size
        };
      
        const price = await this.fetchPrice(priceRequestData);
        this.total = price;
      },
      async fetchPrice(data) {
        try {
          const response = await axiosClient.post('/price/temp/0', data);
          return response.data;
        } catch (error) {
          console.error('Failed to fetch price:', error);
          return 0;
        }
      },
      async fetchSystemInfo() {
        try {
          const response = await axiosClient.get('/systeminfo/0');
          this.largeprice = response.data.largeTempSpotPrice;
          this.smallprice = response.data.regTempSpotPrice;
        } catch (error) {
          console.error('Failed to fetch system info:', error);
        }
      },

    },
    mounted() {
      this.fetchAvailableCars();
      this.fetchSystemInfo();
    },
    watch: {
      selectedDuration() {
        if (this.selectedDuration && this.selectedCar) {
          this.getTotalAmount();
        }
      },
      selectedCar() {
        if (this.selectedDuration && this.selectedCar) {
          this.getTotalAmount();
        }
      },
      smallprice() {
        if (this.selectedDuration && this.selectedCar) {
          this.getTotalAmount();
        }
      },
      largeprice() {
        if (this.selectedDuration && this.selectedCar) {
          this.getTotalAmount();
        }
      },
    },
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