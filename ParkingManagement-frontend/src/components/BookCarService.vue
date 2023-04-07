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
    #homePage{
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
        background: #f2ece8;
    }
</style>