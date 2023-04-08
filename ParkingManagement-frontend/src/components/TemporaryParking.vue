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


<script src="./TempParkingPage.js">
</script>
<!-- <script>
  export default {
    data() {
      return {
        selectedDuration: '',
        selectedCar: '',
        durations: [15, 30, 45, 60, 75, 90, ], // durations in 15-minute increments
        availableCars: [ // replace with actual available cars data
        ],
        errorMessage: '',
        total:0
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
  </script> -->

  
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