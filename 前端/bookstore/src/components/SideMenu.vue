<!--侧边栏分类菜单-->
<template>
  <el-menu
    default-active="0"
    class="categories"
    @select="handleSelect"
    background-color="#fff"
    text-color="#000"
    active-text-color="red">
    <el-menu-item v-for="(item,i) in menuList" :key="i" :index="item.index.toString()">
      <!--菜单的icon图标-->
      <i :class="item.icon"></i>
      <!--菜单的文本内容-->
      <span slot="title">{{item.menuItem}}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu.vue',
  data () {
    return {
      menuId: null,
      menuList: [
        { index: 0, icon: 'el-icon-menu', menuItem: '全部' },
        { index: 1, icon: 'el-icon-menu', menuItem: '小说' },
        { index: 2, icon: 'el-icon-menu', menuItem: '编程' },
        { index: 3, icon: 'el-icon-menu', menuItem: '美食' },
        { index: 4, icon: 'el-icon-menu', menuItem: '商业' },
        { index: 5, icon: 'el-icon-menu', menuItem: '建筑' }
      ]
    }
  },
  methods: {
    // 获取点击的菜单的index
    handleSelect (key) {
      this.menuId = key
      // 触发父组件AppIndex中indexSelect事件
      this.$emit('indexSelect')
    }
  }
}
</script>

<style scoped>
  .categories {
    margin-top: 90px;
    text-align: center;
    width: 150px;
  }
</style>
