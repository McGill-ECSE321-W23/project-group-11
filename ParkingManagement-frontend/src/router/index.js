import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Account from '@/components/Account'
import MonthlyParking from '@/components/MonthlyParking'
import Home from '@/components/Home'
import RegisterCar from '@/components/RegisterCar'
import BookCarService from '@/components/BookCarService'
import Login from '@/components/Login'
import TemporaryParking from '@/components/TemporaryParking'
import CreateAccount from '@/components/CreateAccount'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
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
    }
  ]
})
