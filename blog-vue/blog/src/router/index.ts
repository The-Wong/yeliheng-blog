import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import NotFound from '../views/error/404.vue';

export const constantRoutes: Array<RouteRecordRaw> = [
  {
    path: '/',
    component: (resolve) => import('@/views/home/Home.vue'),
  },
  {
    path: '/articles/:id',
    component: (resolve) => import('@/views/article/Article.vue'),
  }
  
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes: constantRoutes,
})

export default router
