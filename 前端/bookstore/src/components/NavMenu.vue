<template>
  <el-menu
    mode="horizontal"
    :router="true">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.path">
      {{item.navItem}}
    </el-menu-item>
    <span class="storename">网上书店</span>
    <div style="float: right; margin-top: 10px;outline: none">
      <!--未登录，则显示登录注册按钮-->
      <el-button v-if="!this.isLogin" type="primary" @click="login">登录 / 注册</el-button>
      <!--已登录，则显示个人中心及退出登录按钮-->
      <div v-else>
        <a href="#/Center">个人中心</a>
        <el-button style="margin-left: 20px" type="info" @click="logout">退出</el-button>
      </div>
    </div>
  </el-menu>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  name: 'NavMenu.vue',
  data () {
    return {
      navList: [
        { path: '/AppIndex', navItem: '首页' },
        { path: '/Cart', navItem: '购物车' }
      ]
    }
  },
  methods: {
    // 将指定的mapActions函数，映射为当前组件的methods方法
    ...mapMutations(['setUser', 'setId', 'setPassword']),

    // 点击登录按钮，跳转到登录页面
    login () {
      this.$router.push('/LoginAndRegister')
    },

    // 退出登录功能
    logout () {
      // 更新vuex中登录信息
      this.setUser(null)
      this.$router.push('/LoginAndRegister')
    }
  },
  computed: {
    // 判断是否登录
    isLogin () {
      // 根据sessionStorage修改vuex里isLogin的状态
      if (sessionStorage.getItem('username')) {
        this.setUser(sessionStorage.getItem('username'))
        this.setId(sessionStorage.getItem('userid'))
        this.setPassword(sessionStorage.getItem('password'))
      } else {
        this.setUser(null)
      }
      return this.$store.state.isLogin
    }
  }
}
</script>

<style scoped>
  .storename {
    position: absolute;
    margin-top: 15px;
    right: 50%;
    font-size: 22px;
    /*文本粗细*/
    font-weight: bold;
    color: red;
    outline: none;
  }
  a {
    color: dodgerblue;
    text-decoration: none;
  }
</style>
