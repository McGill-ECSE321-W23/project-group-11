import Vue from 'vue'
import Router from 'vue-router'
import Account from '@/components/Account'
import MonthlyParking from '@/components/MonthlyParking'
import Home from '@/components/Home'
import RegisterCar from '@/components/RegisterCar'
import BookCarService from '@/components/BookCarService'
import Login from '@/components/Login'
import TemporaryParking from '@/components/TemporaryParking'
import CreateAccount from '@/components/CreateAccount'
import PaymentTempSpot from '@/components/PaymentTempSpot'
import PaymentMonthlySpot from '@/components/PaymentMonthlySpot'
import PaymentService from '@/components/PaymentService'
import ManagerLogin from '@/components/ManagerLogin'
import ManagerHome from '@/components/ManagerHome'
import ManagerTools from '@/components/ManagerTools'
import PaymentSuccess from '@/components/PaymentSuccess'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      props: true
    },
    {
      path: '/MonthlyPark',
      name: 'MonthlyParking',
      component: MonthlyParking,
      props: true
    },
    {
      path: '/Car',
      name: 'RegisterCar',
      component: RegisterCar
    },
    {
      path: '/BookCarService',
      name: 'BookCarService',
      component: BookCarService
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/TemporaryParking',
      name: 'TemporaryParking',
      component: TemporaryParking
    },
    {
      path: '/CreateAccount',
      name: 'CreateAccount',
      component: CreateAccount
    },
    {
      path: '/PaymentTempSpot',
      name: 'PaymentTempSpot',
      component: PaymentTempSpot
    },
    {
      path: '/PaymentMonthlySpot',
      name: 'PaymentMonthlySpot',
      component: PaymentMonthlySpot
    },
    {
      path: '/PaymentService',
      name: 'PaymentService',
      component: PaymentService
    },
    {
      path: '/ManagerLogin',
      name: 'ManagerLogin',
      component: ManagerLogin
    },
    {
      path: '/ManagerHome',
      name: 'ManagerHome',
      component: ManagerHome
    },
    {
      path: '/ManagerTools',
      name: 'ManagerTools',
      component: ManagerTools
    },
    {
      path: '/PaymentSuccess',
      name: 'PaymentSuccess',
      component: PaymentSuccess
    }
  ]
})
