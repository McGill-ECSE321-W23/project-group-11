<template>
<div>
  <center>
    <h1>ParkingPlus&nbsp<img src="https://static.wixstatic.com/media/cef2a4_5488e99ac9bd419e9f05722ba154e95a~mv2.png/v1/fill/w_560,h_554,al_c,lg_1,q_85,enc_auto/ParkPlus%20Icon%20CMYK%20highres.png" alt="ParkingPlus Logo" style="width: 80px; height: 80px; margin-right: 20px;"><br><br></h1>
    
    <h2>Manager Sign-in</h2>

    <label for="email">Enter your email:</label>
    <input type="email" id="email" name="email"><br><br>

    <label for="password">Enter your password:</label>
    <input type="password" id="password" name="password"><br><br>

    <a href="/#/ManagerHome"><button>Login</button></a>

  </center>
</div>
</template>
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
    background-color: #28a745;
    color: white;
    font-weight: bold;
    cursor: pointer;
    margin-top: 0px;
    display: block;
    width: 10%;
  }

  button:hover{
    background-color: #218838;

  }


</style>
