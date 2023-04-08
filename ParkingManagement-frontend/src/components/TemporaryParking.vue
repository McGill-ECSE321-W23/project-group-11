<template>
  <div id="temporarySpots">
    <h2>Temporary Parking Spots</h2>
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
      <button type="submit">Confirm</button>
    </form>
    <p>
      <span class="error-message">{{ errorMessage }}</span>
    </p>
  </div>
</template>


<script>
  export default {
    data() {
      return {
        selectedDuration: '',
        selectedCar: '',
        durations: [15, 30, 45, 60, 75, 90, ], // durations in 15-minute increments
        availableCars: [ // replace with actual available cars data
        ],
        errorMessage: '',
      };
    },
    methods: {
      submitTemporarySpot() {
        if (!this.selectedDuration || !this.selectedCar) {
          this.errorMessage = 'Please select a duration and a car.';
          return;
        }
        
        // TODO: Implement the logic to book a temporary spot using the selected duration and car.
        
        this.errorMessage = '';
        // show success message or redirect to confirmation page
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

  #parkingReservation {
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

  .reservation-form {
    display: grid;
    grid-template-columns: repeat(2, auto);
    grid-gap: 20px;
    align-items: center;
  }

  .form-group {
    display: flex;
    flex-direction: column;
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
    -webkit-appearance: none;
  }

  input:focus,
  select:focus {
    box-shadow: 0 0 0 2px #007aff;
  }

  .confirm-btn {
    grid-column: 1 / -1;
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

  .confirm-btn:hover {
    background-color: #0058d3;
  }

  .error-message {
    color: #ff3b30;
    margin-top: 20px;
    font-weight: 500;
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
</style>