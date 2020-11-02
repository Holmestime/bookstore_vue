<!--商品详情页面组件-->
<template>
  <el-container>
    <el-header style="border-bottom: 1px solid darkgray; padding-top: 15px">
      <el-button type="primary" round @click="back">回到首页</el-button>
      <span class="detail">商品详情</span>
    </el-header>
    <el-main class="container">
      <el-row :gutter="40">
        <el-col :span="8">
          <div class="img">
            <iframe :src="this.bookInfo.img" frameborder="0" scrolling="no" referrerpolicy="no-referrer"></iframe>
          </div>
        </el-col>
        <el-col :span="16">
          <h1>{{this.bookInfo.bookName}}</h1>
          <div class="description">
            {{this.bookInfo.description}}
          </div>
          <div class="author">
            <span style="margin-right: 50px">作者：{{this.bookInfo.author}}</span>
            出版社：{{this.bookInfo.press}}
          </div>
          <div class="price">定价：{{this.bookInfo.price}}元</div>
          <div>
            <span style="margin-right: 40px"><el-input-number v-model="quantity" :min="1"></el-input-number></span>
            <el-button type="primary" @click="addToCart">添加到购物车</el-button>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'Detail',
  data () {
    return {
      // 用户编号
      user_id:sessionStorage.getItem('userid'),
      // 书本编号
      book_id: '',
      // 书本信息
      bookInfo: {},
      // 要添加到购物车的数量
      quantity: 1
    }
  },
  created () {
    this.getInfo()
  },
  methods: {
    // 根据id获取书籍详细信息
    getInfo () {
      // 获取book_id
      this.book_id = this.$route.query.bookId
      // 根据book_id获取书籍信息
      this.bookInfo = this.$store.state.bookList.find(item => item.id === this.book_id)
    },

    // 添加到购物车
    async addToCart () {
      // 先要验证是否登录，未登录则跳转到登录界面
      if (!this.isLogin) {
        return this.$router.push('/LoginAndRegister')
      }
      // 将id以及要加入购物车的数量发送到后台
      const { data: result } = await this.$axios.post('user/add', {
        user_id: this.user_id,
        book_id: this.book_id,
        price: this.bookInfo.price,
        quantity: this.quantity
      })
      if (result.code !== 200) {
        return this.$message.error('加入购物车失败')
      }else {
        this.$message.success('成功加入购物车')
		this.$router.push('/Home')
      }
    },

    // 返回首页
    back () {
      this.$router.push('/Home')
    }
  },
  computed: {
    ...mapState(['userid', 'isLogin'])
  }
}
</script>

<style scoped>
  .container {
    margin: 10px 7% 100px;
  }
  .detail {
    position: absolute;
    right: 50%;
    font-size: 22px;
    font-weight: bold;
    color: red;
  }
  .description {
    font-size: 18px;
    margin-bottom: 20px;
  }

  .author {
    font-size: 15px;
    margin-top: 10px;
    margin-bottom: 10px;
  }

  .price {
    font-size: 23px;
    color: red;
    margin-top: 10px;
    margin-bottom: 20px;
  }

  .img {
    width: 280px;
    height: 400px;
    position: relative;
    overflow: hidden;
  }
  .img>iframe {
    position: absolute;
    height: 400px;
  }
</style>
