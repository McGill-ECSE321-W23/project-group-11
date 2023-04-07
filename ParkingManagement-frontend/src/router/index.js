import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import MonthlyParking from '@/components/MonthlyParking'
import Home from '@/components/Home'
import RegisterCar from '@/components/RegisterCar'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },    
    {
      path: '/MonthlyPark',
      name: 'MonthlyParking',
      component: MonthlyParking
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/Car',
      name: 'RegisterCar',
      component: RegisterCar
    }
  ]
})
