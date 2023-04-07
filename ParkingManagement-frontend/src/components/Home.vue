<template>
    <div id="homePage">
        <h2>ParkingPlus Home Page</h2>
        <a href="/#/car"><button>Register Car</button></a>
        <button>Book Daily Spot</button>
        <a href="/#/MonthlyPark"><button>Book a Monthly Spot</button></a>
        <a href="/#/CarService"><button>Book Car Service</button></a>
        <button>Log Out</button>
    </div>
</template>
<script>    
</script>
<style>
    #homePage{
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        color: #2c3e50;
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