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
import Payment from '@/components/Payment'
import ManagerLogin from '@/components/ManagerLogin'
import ManagerHome from '@/components/ManagerHome'
import ManagerTools from '@/components/ManagerTools'

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
      component: MonthlyParking
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
      path: '/Payment',
      name: 'Payment',
      component: Payment
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
    }
  ]
})
