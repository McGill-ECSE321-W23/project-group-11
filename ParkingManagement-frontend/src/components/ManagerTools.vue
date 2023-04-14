<template>
  <div id="manager-tools-page">
    <h1>Manager Tools</h1>
    <div class="sections-container">
      <div class="section">
        <h2>System Info</h2>
        <form @submit.prevent="handleSystemInfo">
          <label for="openTime">Open Time:</label>
          <input type="time" id="openTime" v-model="systemInfo.openTime" requireds step = "1" />
          <label for="closeTime">Close Time:</label>
          <input type="time" id="closeTime" v-model="systemInfo.closeTime" required step = "1"/>
          <label for="largeTempSpotPrice">Large Temp Spot Price:</label>
          <input type="number" id="largeTempSpotPrice" v-model="systemInfo.largeTempSpotPrice" required />
          <label for="regTempSpotPrice">Reg Temp Spot Price:</label>
          <input type="number" id="regTempSpotPrice" v-model="systemInfo.regTempSpotPrice" required />
          <label for="reservedSpotPrice">Reserved Spot Price:</label>
          <input type="number" id="reservedSpotPrice" v-model="systemInfo.reservedSpotPrice" required />

          <button type="submit"  @click.prevent="updateSystemInfo()">Update System Info</button>
          
        </form>
      </div>

      <div class="section">
        <h2>Add Service Types</h2>
        <form @submit.prevent="addServiceType">
          <label for="serviceName">Service Name:</label>
          <input type="text" id="serviceName" v-model="newServiceType.name" required />
          <label for="serviceCost">Service Cost:</label>
          <input type="number" id="serviceCost" v-model="newServiceType.cost" required />
          <label for="serviceDuration">Service Duration (min):</label>
          <input type="number" id="serviceDuration" v-model="newServiceType.duration" required />
          <button type="submit">Add Service Type</button>
        </form>
      </div>
      
      <div class="section">
        <h2>Existing Service Types</h2>
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Cost</th>
              <th>Duration</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="serviceType in serviceTypes" :key="serviceType.id">
              <td>{{ serviceType.name }}</td>
              <td>${{ serviceType.cost }}</td>
              <td>{{ serviceType.duration }} min</td>
              <td>
                <button @click="deleteServiceType(serviceType.id)">X</button>
              </td>
              </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios";
  import config from "../../config";
  
  const axiosClient = axios.create({
    baseURL: config.dev.backendBaseUrl,
  });
  
  export default {
    name: "ManagerTools",
    data() {
      return {
        systemInfoExists: false,
        systemInfo: {
          openTime: '00:00:00',
          closeTime: '00:00:00',
          largeTempSpotPrice: 0,
          regTempSpotPrice: 0,
          reservedSpotPrice: 0,
        },
        manager: {
          account: {
            email: "",
          },
          id: 0,
        },
        serviceTypes: [],
        newServiceType: {
          name: "",
          cost: 0,
          duration: 0,
        },
      };
    },
    methods: {
      async fetchSystemInfo() {
        try {
          const response = await axiosClient.get("/systeminfo/0");
          const managerListResponse = await axiosClient.get("/manager");
          this.systemInfo = response.data;
      
      
          if (managerListResponse.data.length > 0) {
            this.id = managerListResponse.data[0].id;
            this.manager = managerListResponse.data[0];
          } else {
            console.log("No managers found.");
          }
          if (response.data) {
            this.systemInfoExists = true;
          }
        } catch (error) {
          console.error("Error fetching system info and manager data:", error);
        }
      },
      async createSystemInfo() {
        try {
          const requestData = {
            ...this.systemInfo,
            manager: {
              account: {
                email: this.manager.account.email,
              },
              id: this.manager.id,
            },
          };
          await axiosClient.post("/systeminfo", requestData);
          this.fetchSystemInfo();
          this.systemInfoExists = true;
        } catch (error) {
          console.error("Failed to create system info:", error);
        }
      },
      async updateSystemInfo() {
        try {
          if (!this.systemInfoExists) {
            await this.createSystemInfo();
          } else {
            await axiosClient.put("/systeminfo", this.systemInfo);
            this.fetchSystemInfo();
            localStorage.setItem("smallprice", this.systemInfo.regTempSpotPrice);
            localStorage.setItem("largeprice", this.systemInfo.largeTempSpotPrice);
          }
        } catch (error) {
          console.error("Failed to update system info:", error);
        }
      },        
      async addServiceType() {
        try {
          const requestData = {
            ...this.newServiceType,
            manager: { id: this.manager.id },
          };
          await axiosClient.post("/servicetype", requestData);
          this.fetchServiceTypes();
          this.newServiceType = { name: "", cost: 0, duration: 0 };
        } catch (error) {
          console.error("Failed to add service type:", error);
        }
      },
      async deleteServiceType(serviceTypeId) {
        try {
          const serviceTypeToDelete = this.serviceTypes.find((st) => st.id === serviceTypeId);
          const requestData = {
            name: serviceTypeToDelete.name,
            cost: serviceTypeToDelete.cost,
            duration: serviceTypeToDelete.duration,
            manager: { id: this.manager.id },
          };
          await axiosClient.delete(`/servicetype`, { data: requestData });
          this.fetchServiceTypes();
        } catch (error) {
          console.error("Failed to delete service type:", error);
        }
      },
      async fetchServiceTypes() {
        try {
          const response = await axiosClient.get('/servicetypes/');
          this.serviceTypes = response.data;
        } catch (error) {
          console.error('Error fetching serviceTypes:', error);
        }
      },
    
    },
    created() {
      this.fetchSystemInfo();
      this.fetchServiceTypes()
    },
    mounted() {
      this.fetchSystemInfo();
      this.fetchServiceTypes();
    },
  };
  </script>

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
      align-items: center;
    }
  
    h2 {
      font-size: 24px;
      margin-bottom: 20px;
    }
  
    form {
      display: flex;
      flex-direction: column;
      align-items: center;
    }
  
    label {
      font-size: 16px;
      margin: 5px 0;
    }
  
    input {
      margin-bottom: 10px;
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
  
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 10px;
    }
  
    th,
    td {
      padding: 8px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }
  
    th {
      background-color: #f2f2f2;
    }
  </style>