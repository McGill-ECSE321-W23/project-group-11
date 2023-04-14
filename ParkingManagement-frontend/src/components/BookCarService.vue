<template>
    <div id="bookCarService">
    <h2>Service Booking</h2>
    <form @submit.prevent="submitReservation">
    <label for="serviceType">Select Service:</label>
    <select id="service" v-model="selectedService" required>
      <option value="" disabled selected>Select a Service</option>
      <option v-for="service in services" :key="service.id" :value="service">
        {{ service.name }}
      </option>
    </select>
    <br /><br />
    <label for="date">Select Date:</label>
    <input type="date" id="date" v-model="date"> 
    <br /><br />
    <label for="time">Select Time:</label>
    <input type="time" list="avail" v-model="startTime">
        <datalist id="avail">
        <option value="09:00:00"></option>
        <option value="09:30:00"></option>
        <option value="10:00:00"></option>
        <option value="10:30:00"></option>
        <option value="11:00:00"></option>
        <option value="11:30:00"></option>
        <option value="12:00:00"></option>
        <option value="12:30:00"></option>
        <option value="13:00:00"></option>
        <option value="13:30:00"></option>
        <option value="14:00:00"></option>
        <option value="14:30:00"></option>
        <option value="15:00:00"></option>
        <option value="15:30:00"></option>
        <option value="16:00:00"></option>
        <option value="16:30:00"></option>
        <option value="17:00:00"></option>
        </datalist>
    <br /><br />


    <label for="car">Select Car:</label>
    <select id="car" v-model="selectedCar" required>
      <option value="" disabled selected>Select your car</option>
      <option v-for="car in cars" :key="car.id" :value="car">
        {{ car.licensePlate }}
      </option>
    </select>

    <br /><br />
    <label for="employee">Select Employee:</label>
    <select id="employee" v-model="selectedEmployee" required>
      <option value="" disabled selected>Select an Employee</option>
      <option v-for="employee in employees" :key="employee" :value="employee">
        {{ employee }}
      </option>
    </select>
    <br /><br />
    <h3>Total to Pay: {{ total }}</h3>
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
    data() {
      return {
        employees: ['Jack', 'Massimo', 'Luke', 'Brian', 'James', 'Ralph'],
        selectedEmployee: null,
        cars: [],
        total: 0,
        selectedCar: null,
        selectedService: null,
        services: [],
        errorMessage: '',
        date: '', 
        startTime: '', 
        employee: '' 
      };
    },
    methods: {
      async fetchServices() {
        try {
          const response = await axiosClient.get('/servicetypes');
          this.services = response.data;
        } catch (error) {
          console.error('Failed to fetch services:', error);
        }
      },
      async fetchCars() {
        try {
          const response = await axiosClient.get('/cars');
          this.cars = response.data;
        } catch (error) {
          console.error('Failed to fetch cars:', error);
        }
      },
      async getTotalAmount() {
        if (!this.selectedService || !this.selectedCar) {
          this.total = 0;
          return;
        }
        
        try {
          const response = await axiosClient.get(`/price/service/${this.selectedService.name}`);
          this.total = response.data;
        } catch (error) {
          console.error('Failed to fetch total amount:', error);
        }
      },
      async createReservation() {
        try {
          const request = {
            date: this.date,
            startTime: this.startTime,
            employee: this.selectedEmployee,
            serviceType: {
              name: this.selectedService.name,
              manager: {
                account: {
                  email: this.selectedService.manager.account.email,
                  password: this.selectedService.manager.account.password,
                  loginstatus: this.selectedService.manager.account.loginstatus,
                },
                id: this.selectedService.manager.id
              }
            },
            car: {
              size: this.selectedCar.size,
              customer: {
                id: this.selectedCar.customer.id
              },
              licensePlate: this.selectedCar.licensePlate
            }
          };
          localStorage.setItem('serviceDto', JSON.stringify(request));
          localStorage.setItem('paymentAmount', this.total);
          await this.$router.push({ name: 'PaymentService', params: { serviceDto: request, paymentAmount: this.total } });
        } catch (error) {
          console.error('Failed to create reservation and navigate:', error);
        }
      }
    },
    mounted() {
      this.fetchCars();
      this.fetchServices(); 
    },
    watch: {
      selectedService: {
        handler() {
          if (this.selectedService && this.selectedCar) { 
            this.getTotalAmount();
          }
        },
        immediate: true,
      },
      selectedCar: {
        handler() { 
          if (this.selectedService && this.selectedCar) {
            this.getTotalAmount();
          }
        },
        immediate: true,
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

  #bookCarService {
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