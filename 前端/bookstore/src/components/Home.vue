<!--主页组件-->
<template>
  <!--style中的height用于使组件占满整个页面-->
  <el-container style="height: 100%">
    <!--页面头部，导航栏-->
    <el-header>
      <NavMenu></NavMenu>
    </el-header>
    <!--页面主体部分，路由占位符-->
    <router-view/>
  </el-container>
</template>

<script>
import NavMenu from './NavMenu'
export default {
  name: 'Home.vue',
  components: { NavMenu }
}
</script>

<style scoped>
</style>
