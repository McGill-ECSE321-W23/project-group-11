<template>
    <div id="homePage">
      <h2>ParkingPlus Home Page</h2>
      <a href="/#/car"><button>Register Car</button></a>
      <a href="/#/TemporaryParking"><button>Book Temporary Spot</button></a>
      <a href="/#/MonthlyPark"><button>Book a Monthly Spot</button></a>
      <a href="/#/BookCarService"><button>Book Car Service</button></a>
      <button>Log Out</button>
  
      <div class="tables-container">
        <div class="table-container">
          <h3>Upcoming Monthly Reservations</h3>
          <table>
            <!-- The rest of the table content remains the same -->
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
    // No script needed for now
  </script>
  
  <style>
    #homePage {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      color: #2c3e50;
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
        margin-top: 140px; /* Adjust the margin-top to lower the tables */
        margin-left: 10px;
        margin-right: 10px;
    }

    .table-container {
        flex: 1;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2); /* Added box-shadow for distinction */
        border-radius: 8px; /* Added border-radius for a more professional look */
        padding: 20px; /* Added padding for better spacing */
        background-color: #fff; /* Set background color to white */
    }

    h3 {
        margin-bottom: 16px; /* Added margin-bottom to separate the title from the table */
    }


  </style>
  