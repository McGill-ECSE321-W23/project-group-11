<template>
  <div id="manager-tools-page">
    <h1>Manager Tools</h1>
    <div class="sections-container">
      <div class="section">
        <h2>Floors Management</h2>
        <p>Manage floors and their capacities.</p>
        <button>Manage Floors</button>
      </div>

      <div class="section">
        <h2>Spots Management</h2>
        <p>Manage reserved and public spots for customers.</p>
        <button>Manage Spots</button>
      </div>

      <div class="section">
        <h2>Services Management</h2>
        <p>Manage services like tire change, car cleaning, and oil change.</p>
        <button>Manage Services</button>
      </div>

      <div class="section">
        <h2>Employees Management</h2>
        <p>Manage employee schedules, hiring, and firing.</p>
        <button>Manage Employees</button>
      </div>
    </div>
  </div>
</template>

<style>
  #manager-tools-page {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    color: #2c3e50;
    text-align: center;
    padding: 0 20px;
  }

  h1 {
    font-size: 48px;
    margin-bottom: 30px;
  }

  .sections-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 30px;
  }

  .section {
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    border-radius: 8px;
    padding: 20px;
    background-color: #fff;
    width: 300px;
    min-height: 200px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
  }

  h2 {
    font-size: 24px;
    margin-bottom: 20px;
  }

  p {
    font-size: 16px;
    margin-bottom: 20px;
  }

  button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #007bff;
    color: white;
    font-weight: bold;
    cursor: pointer;
  }

  button:hover {
    background-color: #0069d9;
  }
</style>
