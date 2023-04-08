import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


function AccountDto(email, password, loginStatus){
    this.email = email;
    this.password = password;
    this.loginStatus = loginStatus;
}

export default {
  name: "Login",
  data() {
    return {
      //username: "",
      //password: "",
      //error: "",
    };
  },
  methods: {
    /**
     * login an account
     */
    login: function (email, password) {
        var account = new AccountDto(email, password, false);
        AXIOS.post('/login', {account}).then(response => {
                //alert(response);
                //this.error = "";
                //this.username = "";
                //this.password = "";
                alert("success");
                window.location.href = "/HomePage"
            }).catch((e) => {
                //alert(e);
                alert(e.response);
            });
    },

    createAccount: function () {
        //window.location.href = "/AccountPage"
      }
  }
};