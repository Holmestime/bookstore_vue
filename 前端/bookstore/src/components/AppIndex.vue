<!--书店页面组件-->
<template>
  <!--style中的height用于使组件占满整个页面-->
  <div style="height: 100%">
    <el-container style="height: 100%">
      <!--侧边分类栏-->
      <el-aside width="200px; margin-top: 20px">
        <SideMenu ref="sideMenuRef" @indexSelect="listByCategory()"></SideMenu>
      </el-aside>
      <!--显示图书区域-->
      <el-main>
        <Books ref="booksRef"></Books>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import SideMenu from './SideMenu'
  import Books from './Books'
  import {
    mapActions
  } from 'vuex'
  export default {
    name: 'AppIndex.vue',
    components: {
      Books,
      SideMenu
    },
    methods: {
      // 将指定的mapActions函数，映射为当前组件的methods方法
      ...mapActions(['getByCategory']),

      // 根据分类栏的menuId获取书籍列表给bookList赋值
      listByCategory() {
        // 获取分类栏的menuId
        const id = this.$refs.sideMenuRef.menuId
        this.getByCategory(id)
        // 页面返回到第一页
        this.$refs.booksRef.pageNum = 1
      }
    }
  }
</script>

<style scoped>
</style>
