import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import Account from '@/components/Account'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/create-account',
      name: 'Create Account',
      component: Account
    }
  ]
})
