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
button{
  border-radius:4px;
  background-color:#80A7A4;
  border:2px solid #51706D;
}
</style>
<template>
<div>
<center>
  <h1>ParkingPlus</h1>

  <label for="email">Enter your email:</label>
  <input type="email" id="email" name="email"><br><br>

  <label for="password">Enter your password:</label>
  <input type="password" id="password" name="password"><br><br>

  <button type="submit">Login</button>
</center>
</div>
</template>