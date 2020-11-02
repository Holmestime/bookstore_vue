<!--购物车页面-->
<template>
  <div class="cart">
    <div v-if="this.cart.length === 0">
      <img src="../assets/cart.png">
      购物车是空的，快去商城看看吧！
    </div>
    <div v-else>
      <div style="font-size: 18px; margin-bottom: 10px">
        我的购物车
      </div>
      <el-table
        border
        :data="cart">
        <el-table-column label="封面" :resizable="false" width="225px" align="center">
          <template slot-scope="scope">
            <div class="img">
              <iframe :src="scope.row.img" frameborder="0" scrolling="no" referrerpolicy="no-referrer"></iframe>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="书名" prop="bookName" :resizable="false" width="300px" align="center"></el-table-column>
        <el-table-column label="单价（元）" prop="price" :resizable="false" width="150px" align="center"></el-table-column>
        <el-table-column label="数量" :resizable="false" width="250px" align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.quantity" @change="handleChange(scope.row.book_id,scope.row.quantity)" :min="1" size="mini"></el-input-number>
          </template>
        </el-table-column>
        <el-table-column label="操作" :resizable="false" width="150px" align="center">
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="删除商品" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="small" @click="deleteBookById(scope.row.book_id)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <div class="total">
        总价：{{total}}元
        <el-button type="danger" style="float: right" @click="account">结算</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
export default {
  name: 'Cart.vue',
  data () {
    return {
      user_id:sessionStorage.getItem('userid')
    }
  },
  created: function () {
    this.getCartList(this.user_id)
    
  },
  methods: {
    // 将指定的mapActions函数，映射为当前组件的methods方法
    ...mapActions(['getCartList']),

    // 改变商品数量
    async handleChange (bookId, value) {
      // 发送axios请求更新后端数据
      const { data: result } = await this.$axios.post('cart/update_num', {
        user_id: this.user_id,
        book_id: bookId,
        quanity: value
      })
      if (result.code !== 200) {
        // 操作失败后重新获取cartList保证前端显示正确
        this.getCartList(this.user_id)
        return this.$message.error('操作失败')
      }
      // 调用getCartList方法更新购物车
      this.getCartList(this.user_id)
    },

    // 根据id删除购物车中对应的书籍
    async deleteBookById (id) {
      // 发送axios请求删除购物车中对应书籍
      const { data: result } = await this.$axios.post('cart/remove_one', {
        user_id: this.user_id,
        book_id: id
      })
      if (result.code !== 200) {
        return this.$message.error('删除数据失败！')
      }
      // 调用getCarList方法更新购物车
      this.getCartList(this.user_id)
    },

    // 结算
    account () {
      this.$confirm('购物车中商品总价为' + this.total + '元，是否购买？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        // 发送axios请求清空购物车
        const { data: result } = await this.$axios.post('cart/check', this.user_id)
        if (result.code !== 200) {
          return this.$message.error('出错')
        }
        this.$message.success('购买成功')
        // 更新购物车
        this.getCartList(this.user_id)
      }).catch(() => {
        this.$message.info('已取消购买')
      })
    }
  },
  computed: {
    ...mapState(['userid', 'cart']),

    // 计算商品总价
    total () {
      var t = 0
      this.cart.forEach(item => {
        t += item.price * item.quantity
      })
      return t
    }
  }
}
</script>

<style scoped>
  .cart {
    margin: 30px auto 0;
    text-align: center;
    width: 1075px;
  }
  .img {
    width: 100%;
    height: 190px;
    position: relative;
    overflow: hidden;
  }
  .img>iframe {
    position: absolute;
    transform: scale(0.8,0.475);
    left: -59px;
    top: -104px;
    height: 400px;
  }
  .total {
    margin-top: 15px;
    font-size: 20px;
    color: red;
    margin-bottom: 80px;
  }
</style>
