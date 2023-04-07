<template>
  <div id="registerCar">
    <h2>Register Car</h2>
    <form @submit.prevent="submitCarRegistration">
      <label for="licensePlate">License Plate:</label>
      <input type="text" id="licensePlate" v-model="licensePlate" required />
      <br /><br />
      <label for="carSize">Select Car Size:</label>
      <select id="carSize" v-model="selectedSize" required>
        <option value="" disabled selected>Select car size</option>
        <option v-for="size in carSizes" :key="size" :value="size">
          {{ size }}
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
      licensePlate: "",
      selectedSize: "",
      carSizes: ["Regular", "Large"],
      errorMessage: "",
    };
  },
  methods: {
    submitCarRegistration() {
      // Handle car registration
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
  #registerCar {
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

  button {
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

  button:hover {
  background-color: #0058d3;
  }

  .error-message {
  color: #ff3b30;
  margin-top: 20px;
  font-weight: 500;
  }
</style>