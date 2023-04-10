
<template>
  <div>
    <center>
      <h1>ParkingPlus&nbsp<img src="https://static.wixstatic.com/media/cef2a4_5488e99ac9bd419e9f05722ba154e95a~mv2.png/v1/fill/w_560,h_554,al_c,lg_1,q_85,enc_auto/ParkPlus%20Icon%20CMYK%20highres.png" alt="ParkingPlus Logo" style="width: 80px; height: 80px; margin-right: 20px;"><br><br></h1>
      
      <label for="email">Email:</label>
      <input type="email" id="email" name="email"><br><br>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password"><br><br>

      <label for="confirmPassword">Confirm Password:</label>
      <input type="password" id="confirmPassword" name="confirmPassword"><br><br>

      <button type="submit" @click.prevent="createAccount">Create Account</button>
      <p v-if="errorMessage" style="color:red">{{ errorMessage }}</p>

    </center>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
      confirmPassword: '',
      errorMessage: '',
    };
  },
  methods: {
    createAccount() {
      if (this.password !== this.confirmPassword) {
        this.errorMessage = 'Passwords do not match. Please try again.';
        return;
      }
      
      // TODO: Implement the logic to create the account with the provided email, password, and other data.
      
      this.errorMessage = '';
      // show success message or redirect to confirmation page
    },
  },
};
</script>
<style>
  div {
    margin-bottom: 10px;
  }
  label {
    display: table;
    width: 200px;
    text-align: center;
  }
  input{
    width:300px
  }
  button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    font-weight: bold;
    cursor: pointer;
    margin-top: 0px;
    display: block;
    width: 10%;
  }
</style>