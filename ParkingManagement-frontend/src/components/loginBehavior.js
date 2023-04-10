import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


export default {
  name: "Login",
  data() {
    return {errorMessage: ''};
  },

  methods: {
    /**
     * login an account
     */
    login: function (accountEmail, accountPassword) {
      AXIOS.post('/login', {email: accountEmail, password: accountPassword}).then(() => {
         window.location.href = "/ManagerHome"
        })
        .catch((error) => {
          this.errorMessage = "Please try again: " + error.response.data;
        })
    },
  }
};