<!--陈列书本的组件-->
<template>
  <div>
    <el-row>
      <!--span：栅格占据的列数 offset：栅格左侧的间隔格数-->
      <el-col :span="3" :offset="1" v-for="item in bookList.slice((pageNum-1)*pageSize,pageNum*pageSize)" :key="item.id">
        <el-card :body-style="{ padding: '0px' }" class="card">
          <a href="" @click.prevent="goDetail(item.id)">
            <!--书籍图片-->
            <div class="img">
              <iframe :src="item.img" frameborder="0" scrolling="no" referrerpolicy="no-referrer"></iframe>
            </div>
            <div class="message">
              <!--书名-->
              <div class="name">
                <!--点击书名跳转到详情页-->
                <a href="" @click.prevent="goDetail(item.id)">{{item.bookName}}</a>
              </div>
              <!--作者名及单价-->
              <div class="bottom">
                <!--作者-->
                <div class="author">{{item.author}}</div>
                <div class="price">￥ {{item.price}}</div>
              </div>
            </div>
          </a>
        </el-card>
      </el-col>
    </el-row>
    <!--style样式使分页组件居中-->
    <el-row style="text-align: center">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-size="pageSize"
        :total="bookList.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
export default {
  name: 'Books.vue',
  data () {
    return {
      // 当前页码
      pageNum: 1,
      // 每一页展示多少本书
      pageSize: 12
    }
  },
  created: function () {
    // 加载全部书籍
    this.getBookList()
  },
  methods: {
    // 将指定的mapActions函数，映射为当前组件的methods方法
    ...mapActions(['getBookList']),

    // 监听页码值pageNum改变的事件
    handleCurrentChange (newPage) {
      this.pageNum = newPage
    },

    // 前往书籍详情页
    goDetail (val) {
      this.$router.push({ path: '/Detail', query: { bookId: val } })
    }
  },
  computed: {
    ...mapState(['bookList'])
  }
}
</script>

<style scoped>
  .card {
    width: 100%;
    height: 270px;
    margin-bottom: 30px;
    border-radius: 8px;
  }
  /*图片样式*/
  .img {
    width: 100%;
    height: 190px;
    position: relative;
    overflow: hidden;
  }
  .img>iframe {
    position: absolute;
    transform: scale(0.65,0.475);
    left: -59px;
    top: -104px;
    height: 400px;
  }
  .message {
    padding: 10px 14px 14px;
    height: 60px;
    background-color: #effaea;
  }
  /*书名样式*/
  .name {
    font-size: 16px;
    height: 16px;
    line-height: 16px;
    /*文本超出部分隐藏，不显示*/
    overflow: hidden;
  }
  /*a标签的样式*/
  a {
    color: dodgerblue;
    text-decoration: none;
  }
  a:hover {
    text-decoration: underline;
  }
  /*底部信息样式*/
  .bottom {
    margin-top: 10px;
    line-height: 12px;
    height: 40px;
  }
  /*作者样式*/
  .author {
    font-size: 13px;
    width: 100px;
    height: 13px;
    line-height: 13px;
    color: #999;
    /*文本超出部分隐藏，不显示*/
    overflow: hidden;
    margin-bottom: 2px;
  }
  .price {
    font-size: 16px;
    height: 16px;
    line-height: 16px;
    color: red;
    /*文本超出部分隐藏，不显示*/
    overflow: hidden;
    padding: 0px;
    float: right;
  }
</style>
