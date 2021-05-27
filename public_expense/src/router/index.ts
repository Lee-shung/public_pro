/*
 * @Author: 李无敌
 * @Date: 2020-11-19 17:19:05
 * @LastEditors: 李无敌
 * @LastEditTime: 2021-02-22 11:14:34
 * @FilePath: \myworkpace\public_expense\src\router\index.ts
 */
import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import Main from '../views/Main.vue'
const Control = () => import('@/views/Control/index.vue');
const Income = ()=> import('@/views/daily/Income.vue');
const IncomeType = ()=> import('@/views/daily/IncomeType.vue');
const Login = ()=> import('@/views/Login.vue');

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Main',
    component: Main,
    children: [
      {
        path: 'home/control',
        name: 'Control',
        component: Control
      },
      {
        path: 'daily/income',
        name: 'Income',
        component: Income
      },
      {
        path: 'daily/incomeType',
        name: 'IncomeType',
        component: IncomeType
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
