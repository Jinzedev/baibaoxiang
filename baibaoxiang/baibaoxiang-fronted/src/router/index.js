import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'index',
      component:  () => import('@/views/IndexView.vue'),
      children: [
        {
          path: '',
          name: '首页',
          component:  () => import('@/views/index/WelcomeIndex.vue')
        },
        {
            path: 'toolbox',
            name: '工具箱',
            component:  () => import('@/views/index/ToolBoxPage.vue'),
          children: [
            {
              path: '',
              name: 'convertor-tool',
              component: () => import('@/views/toolBox/Convertor.vue')
            },
            {
              path: 'encrypt-tool',
              name: 'encrypt-tool',
              component: () => import('@/views/toolBox/Encrypt.vue')
            },
          ]
        }
      ]
    }
  ]
})

export default router
