mode: 'hash'
import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../components/Home'
import LoginAndRegister from '../components/LoginAndRegister'
import Cart from '../components/Cart'
import AppIndex from '../components/AppIndex'
import Detail from '../components/Detail'
import Center from '../components/Center'

import Manage from "@/components/Manage";
import AddBook from "@/components/AddBook";
import BookMgr from "@/components/BookMgr";
import CategoryMgr from "@/components/CategoryMgr";
//import CountMgr from "@/components/CountMgr";
//<meta name="referrer" content="no-referrer"/>
Vue.use(VueRouter)

const routerPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return routerPush.call(this, location).catch(error => error)
}

const routes = [
  {
    path: '/',
    redirect: '/Home'
  },
  {
    path: '/LoginAndRegister',
    component: LoginAndRegister
  },
  {
    path: '/Home',
    component: Home,
    redirect: '/AppIndex',
    children: [
      // AppIndex和Cart作为Home的子路由
      { path: '/AppIndex', component: AppIndex },
      { path: '/Cart', component: Cart }
    ]
  },
  {
    path: '/Detail',
    component: Detail
  },
  {
    path: '/Center',
    component: Center
  },
  {
    path:'/manage',
    component:Manage,
    redirect: '/addBook',
    children:
    [
      {
        path: "/addBook",
        component: AddBook
      },
      {
        path: "/bookManage",
        component: BookMgr
      },
      {
        path: "/categoryMgr",
        component: CategoryMgr
      },
      {
        path: "/Home",
        component: Home
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

// 设置路由导航守卫控制页面的跳转
router.beforeEach((to, from, next) => {
  if (to.path === '/LoginAndRegister') {
    return next()
  }
  if (to.path === '/manage') {
    return next()
  }
  if (to.path === '/addBook') {
    return next()
  }
  if (to.path === '/bookManage') {
    return next()
  }
  if (to.path === '/categoryMgr') {
    return next()
  }
  if (to.path === '/Home') {
    return next()
  }
  if (to.path === '/AppIndex') {
    return next()
  }
  if (to.path === '/Books') {
    return next()
  }
  if (to.path === '/Detail') {
    return next()
  }
  if (to.path === '/NavMenu') {
    return next('Home')
  }
  if (to.path === '/SideMenu') {
    return next('Home')
  }
  /* 获取username */
  const userStr = sessionStorage.getItem('username')
  /* 未取到userStr，则跳转到登录界面 */
  if (!userStr) {
    return next('LoginAndRegister')
  }
  /* 取到userStr */
  return next()
})

export default router
