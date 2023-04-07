import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import MonthlyParking from '@/components/MonthlyParking'


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
    }
  ]
})
