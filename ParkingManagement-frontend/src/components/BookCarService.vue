<template>
    <div id="bookCarService">
    <h2>Service Booking</h2>
    <form @submit.prevent="submitReservation">
    <label for="serviceType">Select Service:</label>
      <select id="service" v-model="selectedService" required>
        <option value="" disabled selected>Select a Service</option>
        <option v-for="service in services" :key="service.id" :value="service.id">
          {{ service.name }}
        </option>
      </select>
    <br /><br />
    <label for="date">Select Date:</label>
    <input type="date" id="date">
    <br /><br />
    <label for="time">Select Time:</label>
    <input type="time" list="avail">
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
        <option v-for="car in cars" :key="car.id" :value="car.id">
          {{ car.name }}
        </option>
    </select>
    <br /><br />
    <label for="employee">Select Employee:</label>
    <select>
      <option value=""></option>
      <select id="time" name="time"></select>
    </select>
    <br /><br />
    <h3>Total to Pay: ${{ total }}</h3>
    <button type="submit">Confirm</button>
    </form>
    <p>
      <span style="color:red">{{ errorMessage }}</span>
    </p>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        total: 0, 
      };
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