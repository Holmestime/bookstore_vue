import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import axios from 'axios'
import './plugins/element.js'
import echarts from 'echarts';
import $ from 'jquery'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min'
import './assets/commen.css'
//mock测试

axios.defaults.baseURL = 'https://www.easy-mock.com/mock/5e8ee8b151da6f1514fce510/example'

// 全局注册axios
Vue.prototype.$axios = axios
Vue.prototype.$echarts = echarts
Vue.prototype.router = router
axios.defaults.baseURL = 'http://localhost:8443/Book_war_exploded/'
// Vue.prototype.host = 'http://127.0.0.1:8080/api/1'
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
Vue.config.productionTip = false

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
