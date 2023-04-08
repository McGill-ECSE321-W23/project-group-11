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

function EmployeeDto(id, email){
    this.id = id;
    this.account = email;
}

function CustomerDto(id, email){
    this.id = id;
    this.account = email;
}


export default{
    name: 'Account',
    data() {
        return {
          accounts: [],
          managers:[],
          employees:[],
          customers:[],
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

          if(this.type = 'Manager'&& managers.length==0){
            this.id = 1;
            var m = new ManagerDto(id, a.email);
            this.managers.push(m);
          }

          if(this.type = 'Manager' && managers.length!=0){
            this.errorMessage = 'Cannot have more than 1 manager.';
          }

          if(this.type = 'Employee'){
            this.id = employees.length+1;
            var e = new EmployeeDto(id, a.email);
            this.employees.push(e);
          }

          if(this.type = 'Customer'){
            this.id = customers.length+1;
            var c = new CustomerDto(id, a.email);
            this.customers.push(e);
          }
          return;
        },
      },
    };
