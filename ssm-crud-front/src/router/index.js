import { createRouter, createWebHashHistory } from 'vue-router'
import CrudView from '../views/CrudView.vue'
const routes = [
  {
    path: '/',
    name: 'home',
    component: CrudView
  },
  {
    path: '/about',
    name: 'about',
    // 懒加载
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
