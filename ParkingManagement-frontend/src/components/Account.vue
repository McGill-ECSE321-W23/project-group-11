<style>
div {
  margin-bottom: 10px;
}

label {
  display: table;
  width: 200px;
  text-align: center;
}

input {
  width: 300px
}

button {
  border-radius: 4px;
  background-color: #80A7A4;
  border: 2px solid #51706D;
}
</style>
<script src="./Account.js">
</script>
<template>
  <div>
    <center>
      <h1>ParkingPlus&nbsp<img
          src="https://static.wixstatic.com/media/cef2a4_5488e99ac9bd419e9f05722ba154e95a~mv2.png/v1/fill/w_560,h_554,al_c,lg_1,q_85,enc_auto/ParkPlus%20Icon%20CMYK%20highres.png"
          alt="ParkingPlus Logo" style="width: 80px; height: 80px; margin-right: 20px;"><br></h1>
      <h2>Create Account</h2>
      <h4>Welcome to the account creation page</h4><br><br>

      <label for="email">Enter your email:</label>
      <input type="email" id="email" name="email" v-model="email"><br><br>

      <label for="password">Enter your password:</label>
      <input type="password" id="password" name="password" v-model="password"><br><br>

      <label for="reenter-password">Re-enter your password:</label>
      <input type="password" id="reenter-password" name="reenter-password" v-model="confirmPassword"><br><br>

      <label for="logintype">Registering as a:</label>
      <select name="Type" id="logintype">
        <option value="customer">Customer</option>
        <option value="employee">Employee</option>
        <option value="manager">Manager</option>
      </select><br><br>

      <button type="submit">Create Account</button><br><br>

      <p>Already have an account? <a href="#">Login Instead</a></p>

    </center>
</div></template>