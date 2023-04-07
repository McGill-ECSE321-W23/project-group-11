import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function AccountDto(email, password, status){
    this.email = email;
    this.password = password;
    this.loginStatus = status;
}

function ManagerDto(id, email){
    this.id = id;
    this.account = email;
}


export default{
    data() {
        return {
          accounts: [],
          manager:[],
          email: '',
          password: '',
          confirmPassword: '',
          errorMessage: '',
          type:'',
        };
      },
      methods: {
        createAccount: function() {
          if (this.password !== this.confirmPassword) {
            this.errorMessage = 'Passwords do not match. Please try again.';
            return;
          }
          this.status = true;
          var a = new AccountDto(this.email, this.password, this.status);
          this.accounts.push(a);
          this.errorMessage = '';

          if(this.type = 'Manager'){
            this.id = 1;
            var m = new ManagerDto(id, a.email);
            this.manager.push(m);
          }

          //TODO associations if the account type is employee or customer
          return;
        },
      },
    };
