<template>
  <div class="container">
    <div class="row form-group">
      <div class="col-lg-4">
        <label for="">分类</label>
        <select v-model="category_id" v-on:change="getBooksBycategory()" class="form-control" style="width:80%">
          <option disabled value="">请选择</option>
          <option value="0">小说</option>
          <option value="1">编程</option>
          <option value="2">美食</option>
          <option value="3">商业</option>
          <option value="4">建筑</option>
        </select>
      </div>
    </div>

    <div class="mt-3">
      <div class="mt-3">
        <label>该分类书的数目为：</label>
        <label>{{bookList.length}}</label>

        <div class="row book-item" v-for="b in bookList" :key="b.id">
          <div class="col-lg-3">
            <div class="img">
              <iframe :src="b.img" frameborder="0" scrolling="no" referrerPolicy="no-referrer"></iframe>
            </div>
          </div>
          <div class="col-lg-9">
            <div class="row">
              <div class="col-lg-6 col-sm-6">
                <label class="book-name">书名：{{ b.bookName }}</label>
              </div>
              <div class="col-lg-6 col-sm-6 text-muted text-right">
                <label>编号：</label>
                <label>{{ b.id }}</label>
              </div>
            </div>
            <div class="row mt-1">
              <div class="col-lg-3 col-sm-5">
                <label>价格：</label>
                <label>{{ b.price }}</label>
              </div>
              <div class="col-lg-9 col-sm-7">
                <label>作者：</label>
                <label>{{ b.author }}</label>
              </div>
            </div>
            <div class="row mt-1">
              <div class="col-lg-3 col-sm-7">
                <label>分类：</label>
                <label>{{ categories[b.category_id].name }}</label>
              </div>
              <div class="col-lg-6 col-sm-12">
                <label>{{ b.press }}&nbsp;&nbsp;</label>
              </div>
            </div>
            <div class="row">
              <div class="col-lg-6 mt-2">
                <button @click="modifyBook(b.id)" class="btn btn-block btn-sm" data-toggle="modal" data-target="#modify">
                  修改
                </button>
              </div>
              <div class="col-lg-6 mt-2">
                <button @click="pullOff(b.id)" class="btn btn-block btn-sm">下架</button>
              </div>
              <div class="modal fade" id="modify" tabindex="-1" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title col-lg-3">{{ book.bookName }}</h5>
                      <label class="col-lg-4 text-right">{{ book.id }}</label>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      作者：
                      <input type="text" class="form-control" v-model="author">
                      出版社：
                      <input type="text" class="form-control" v-model="press">
                      分类：
                      <select v-model="category_id" class="form-control" style="width:80%">
                        <option disabled value="">请选择</option>
                        <option value="0">小说</option>
                        <option value="1">编程</option>
                        <option value="2">美食</option>
                        <option value="3">商业</option>
                        <option value="4">建筑</option>
                      </select>
                      价格：
                      <input type="text" class="form-control" v-model="price">
                      简介：
                      <textarea class="form-control" rows="5" v-model="description"></textarea>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                      <button @click="saveBook(book.id)" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="clearfix"></div>
  </div>
</template>

<script>
  export default {
    name: "categoryMgr",
    data() {
      return {
        categories: [{
            id: "0",
            name: "小说"
          },
          {
            id: "1",
            name: "编程"
          },
          {
            id: "2",
            name: "美食"
          },
          {
            id: "3",
            name: "商业"
          },
          {
            id: "4",
            name: "建筑"
          }
        ],
        bookList: [],
        book: {},
        bookName: "",
        author: "",
        press: "",
        category_id: "",
        price: "",
        description: ""
      };
    },
    methods: {
      getBooksBycategory() {
        this.$axios.post('book/getByCategory', this.category_id)
          .then(bookList => {
            this.bookList = bookList.data;
            console.log("GetByCategory ok");
          })
      },
      modifyBook(id) {
        this.$axios.post('book/getById', id).then(book => {
          this.book = book.data;
          this.bookName = this.book.bookname;
          this.author = this.book.author;
          this.press = this.book.press;
          this.price = this.book.price;
          this.category_id = this.book.category_id;
          this.description = this.book.description;
        });
      },
      saveBook(id) {
        let sbook = {
          bookName: this.bookName,
          author: this.author,
          press: this.press,
          price: this.price,
          category_id: this.category_id,
          description: this.description,
          id: this.id
        };
        this.$axios.post('book/update', sbook).then((result) => {
          console.log(result);
          console.log(result);
          if (result.status != 200) {
            alert("保存失败！")
          } else {
            alert("保存成功！")
          }
        })
      },
      pullOff(id) {
        if (confirm('确定要下架书籍吗') == true) {
          this.$axios.post('book/delete', id).then((result) => {
            this.getBooksBycategory(this.category_id);
            console.log(result);
            if (result.status != 200) {
              alert("下架失败！")
            } else {
              alert("下架成功！")
              getBooksBycategory(this.category_id);
            }
          })
        }
      }
    }
  };
</script>

<style scoped>
  /*图片样式*/
  .img {
    width: 100%;
    height: 190%;
    position: relative;
    overflow: hidden;
  }

  .img>iframe {
    transform: scale(0.48, 0.4);
    position: absolute;
    right: -75px;
    top: -120px;
    height: 400px;
  }
</style>
