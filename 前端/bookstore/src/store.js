import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const state = {
  // 是否登录
  isLogin: false,
  // 用户名
  username: null,
  // 用户id
  userid: null,
  // 密码
  password: null,
  // 书籍列表
  bookList: [],
  // 购物车列表
  cart: []
}

const mutations = {
  // 修改登录信息
  setUser (state, username) {
    if (username) {
      // 登录时
      state.username = username
      state.isLogin = true
    } else if (username === null) {
      // 退出登录时
      // 清空sessionStorage
      sessionStorage.clear()
      state.username = null
      state.userid = null
	  state.password = null
      state.isLogin = false
    }
  },
  setId (state, userid) {
    if (userid) {
      state.userid = userid
    }
  },
  setPassword (state, password) {
    if (password) {
      state.password = password
    }
  },
  // 给bookList赋值
  initBookList (state, list) {
    state.bookList = list
  },
  // 给cart赋值
  initCart (state, list) {
    list.forEach(item => {
      const id = item.book_id
      const book = state.bookList.find(i => i.id === id)
      item.bookName = book.bookName
      item.img = book.img
    })
    state.cart = list
  }
}

const actions = {
  // 获取全部书籍
  async getBookList (context) {
    const { data: result } = await axios.post('book/getAllBook')
    context.commit('initBookList', result)
  },

  // 根据分类id获取相应书籍
  async getByCategory (context, id) {
    const { data: result } = await axios.post('book/getByCategory', id)
    context.commit('initBookList', result)
  },

  // 获取购物车
  async getCartList (context, userId) {
    const { data: result } = await axios.post('cart/list', userId)
    context.commit('initCart', result)
  }
}

const getters = {
}

export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
})
