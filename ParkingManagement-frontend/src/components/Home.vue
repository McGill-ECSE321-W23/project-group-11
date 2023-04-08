<template>
    <div id="homePage">
      <h1>ParkingPlus Home Page</h1>
      <div class="button-container">
        <a href="/#/car"><button>Register Car</button></a>
        <a href="/#/TemporaryParking"><button>Book Temporary Spot</button></a>
        <a href="/#/MonthlyPark"><button>Book a Monthly Spot</button></a>
        <a href="/#/BookCarService"><button>Book Car Service</button></a>
        <button>Log Out</button>
      </div>
  
      <div class="tables-container">
        <div class="table-container">
            <h3>Upcoming Monthly Reservations</h3>
            <table>
              <thead>
                <tr>
                  <th>Car</th>
                  <th>Year</th>
                  <th>Month</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="reservation in monthlyreservations" :key="reservation.id">
                  <td>{{ reservation.car }}</td>
                  <td>{{ reservation.year }}</td>
                  <td>{{ reservation.month }}</td>
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
            <!-- The rest of the table content remains the same -->
          </table>
        </div>
      </div>
    </div>
</template>
  
<script>
    import axios from 'axios';

    export default {
      name: 'tablesdata',
      data() {
        return {
          monthlyreservations: [],
        };
      },
      methods: {
        async fetchMonthlyReservations() {
          try {
            const response = await axios.get('/reservedspots');
            this.monthlyreservations = response.data;
          } catch (error) {
            console.error('Error fetching reservations:', error);
          }
        },
      },
      mounted() {
        this.fetchMonthlyReservations();
      },
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
      margin-top: 40px;
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


</style>
  