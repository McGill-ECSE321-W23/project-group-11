<template>
    <div id="homePage">

      <div class="header">
        <span class="user-email">{{ userEmail }}</span>
      </div>

      <h1>ParkSimple Home Page</h1>
      <div class="button-container">
        <a href="/#/car"><button>Register Car</button></a>
        <a href="/#/TemporaryParking"><button>Book Temporary Spot</button></a>
        <a href="/#/MonthlyPark"><button>Book a Monthly Spot</button></a>
        <a href="/#/BookCarService"><button>Book Car Service</button></a>
        <button @click="logout(user-email)">Log Out</button>

      </div>
  
      <div class="tables-container">
        <div class="table-container">
            <h3>Upcoming Monthly Reservations</h3>
            <table>
              <thead>
                <tr>
                  <th>Car License Plate</th>
                  <th>Year</th>
                  <th>Month</th>
                  <th>Placenumber</th>

                </tr>
              </thead>
              <tbody>
                <tr v-for="reservation in monthlyreservations" :key="index">
                  <td>{{ reservation.carDto.licensePlate}}</td>
                  <td>{{ reservation.year }}</td>
                  <td>{{ reservation.month }}</td>
                  <td>{{ reservation.placeNumber }}</td>
                </tr>
              </tbody>
            </table>
        </div> 
  
        <div class="table-container">
          <h3>Upcoming Temporary Reservations</h3>
          <table>
            <!-- The rest of the table content remains the same -->
          </table>
        </div>
  
        <div class="table-container">
          <h3>Upcoming Service Bookings</h3>
          <table>
            <thead>
              <tr>
                <th>Car</th>
                <th>Service</th>
                <th>Date</th>
                <th>Time</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="reservation in servicebookings" :key="index">
                <td>{{ reservation.car.licensePlate }}</td>
                <td>{{ reservation.serviceType.name }}</td>
                <td>{{ reservation.date }}</td>
                <td>{{ reservation.startTime }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
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
        monthlyreservations: [],
        servicebookings: [],
      };
    },
    methods: {
      logout() {
        axiosClient.post('/logout', {email: this.userEmail}).then(() => {
          localStorage.removeItem('email');
          this.$router.push({name: 'Login'});
        })
        .catch((error) => {
          this.errorMessage = "Please try again: " + error.response.data;
        });
      }
    },  
    created() {
      axiosClient.get('/reservedspots/').then((response) => { console.log('Fetched reservations:', response.data);
          this.monthlyreservations = response.data;
        })
        .catch((err) => {console.error('Error fetching reservations:', err);
        });

      axiosClient.get('/bookings/').then((response) => {
        console.log('Fetched bookings:', response.data);
        this.servicebookings = response.data; 
      })
      .catch((err) => {
        console.error('Error fetching bookings:', err);
      });

    }
  };
</script>
  
<style>
  #homePage {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    text-align: center;
    padding: 0 20px;
  }

  h1 {
    font-size: 48px;
    margin-bottom: 30px;
  }

  .button-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 10px;
    margin-bottom: 40px;
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

  button:hover {
    background-color: #0069d9;
  }

  /* Table styles */
  table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    background-color: #ffffff;
    font-size: 14px;
  }

  th,
  td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }

  th {
    background-color: #007bff;
    font-weight: bold;
    color: white;
  }

  tr:nth-child(even) {
    background-color: #f2f2f2;
  }

  tr:hover {
    background-color: #ddd;
  }

  .tables-container {
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 80px;
  }

  .table-container {
    flex: 1;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    border-radius: 8px;
    padding: 20px;
    background-color: #fff;
  }

  h3 {
      margin-bottom: 16px; /* Added margin-bottom to separate the title from the table */
  }

  .header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .user-email {
    font-size: 16px;
    font-weight: bold;
    text-align: right;
  }

</style>
  