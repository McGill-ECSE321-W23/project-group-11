<template>
    <div id="bookCarService">
    <h2>Book A Car Service</h2>
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
        <option value="Bob">Bob</option>
        <option value="Susie">Susie</option>
        <option value="Michelle">Michelle</option>
        <option value="Adam">Adam</option>
        <option value="Mike">Mike</option>
    </select>
    <br /><br />
    <button type="submit">Confirm</button>
    </form>
    <p>
      <span style="color:red">{{ errorMessage }}</span>
    </p>
  </div>
</template>
<script>
</script>
<style>
  html, body {
    margin: 0;
    height: 100%;
    overflow: hidden;
  }

  #bookCarService {
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