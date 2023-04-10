<template>
    <div id="paymentSuccessPage">
      <h2>Payment Success!</h2>
      <a href="/#/home"><button>Back</button></a>
    </div>
  </template>
  
  <script>
  </script>
  <style>
  
    #paymentSuccessPage {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      color: #2c3e50;
      max-width: 500px;
      margin: 0 auto;
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
  
    h2 {
      margin-bottom: 30px;
      font-weight: 500;
      font-size: 50px; /* Increased font size */
    }
  
    input {
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
      margin-top: 40px;
      display: block;
      width: 100%;
    }
  
    button:hover {
      background-color: #0069d9;
    }
  </style>