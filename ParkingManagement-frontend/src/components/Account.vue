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
    </style>
<template>
    <div>
    <center>
        <h1>Create Account</h1>
        <h4>Welcome to the account creation page</h4><br><br>

        <label for="email">Enter your email:</label>
        <input type="email" id="email" name="email"><br><br>

        <label for="password">Enter your password:</label>
        <input type="password" id="password" name="password"><br><br>

        <label>Enter your license plate:</label>
        <input id="licenseplate" name="licenseplate"><br><br>

        <label for="logintype">Registering as a:</label>
        <select name="Type" id="logintype">
            <option value="customer">Customer</option>
            <option value="employee">Employee</option>
            <option value="manager">Manager</option>
        </select><br><br>

        <button type="create">Create Account</button><br><br>
    </center>
    </div>
</template>