<template>
  <div class="clearfix container">
    <form class="input-group mt-3">
      <input v-model="id" type="text" class="form-control" placeholder="书籍编号">
      <div class="input-group-append">
        <button @click="search()" class="btn" type="submit">搜索</button>
      </div>
    </form>
    <div class="mt-3">
      <div class="row book-item" v-if="this.flag">
        <div class="col-lg-3">
          <div class="img">
            <iframe :src="this.img" frameborder="0" referrerPolicy="no-referrer"></iframe>
          </div>
        </div>
        <div class="col-lg-9">
          <div class="row">
            <div class="col-lg-6 col-sm-6">
              <label>书名：</label>
              <label class="book-name">{{ this.bookName }}</label>
            </div>
            <div class="col-lg-6 col-sm-6 text-muted text-right">
              <label>编号：</label>
              <label>{{ this.id }}</label>
            </div>
          </div>
          <div class="row mt-1">
            <div class="col-lg-3 col-sm-5">
              <label>价格：</label>
              <label>{{ this.price }}</label>
            </div>
            <div class="col-lg-9 col-sm-7">
              <label>作者：</label>
              <label>{{ this.author }}</label>
            </div>
          </div>
          <div class="row mt-1">
            <div class="col-lg-3 col-sm-5">
              <label>分类：</label>
              <label>{{ categories[this.category_id].name }}</label>
            </div>
            <div class="col-lg-3 col-sm-5">
              <label>出版社：</label>
              <label>{{ this.press }}</label>
            </div>
          </div>
          <div class="row">
            <div class="modal fade" id="comment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
              aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">{{ this.bookName }}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 mt-2">
              <button @click="modifyBook( )" class="btn btn-block btn-sm" data-toggle="modal" data-target="#modify">
                修改
              </button>
            </div>
            <div class="col-lg-4 mt-2">
              <button @click="pullOff( )" class="btn btn-block btn-sm">下架</button>
            </div>
            <div class="modal fade" id="modify" tabindex="-1" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title col-lg-3">{{ this.bookName }}</h5>
                    <label class="col-lg-4 text-right">{{ this.id }}</label>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    作者：
                    <input type="text" class="form-control" v-model="author">
                    书籍编号：
                    <input type="text" class="form-control" v-model="id">
                    出版社：
                    <input type="text" class="form-control" v-model="press">
                    书籍分类：                                      
                    <select v-model="category_id" class="form-control">
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
                    <button @click="saveBook( )" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
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
    name: "BookMgr",
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
        books: [],
        book: "",
        bookName: "",
        id: "",
        author: "",
        press: "",
        category_id: "",
        price: "",
        description: "",
        img: "",
        flag: false
      };
    },
    methods: {
      search() {
        this.searchBook();
      },
      searchBook() {
        this.$axios.post('book/getById', this.id).then(book => {
          if(book.data!=""){
            console.log(book);
            this.book = book.data;
            this.bookName = this.book.bookName;
            this.id = this.book.id;
            this.author = this.book.author;
            this.press = this.book.press;
            this.price = this.book.price;
            this.category_id = this.book.category_id;
            this.description = this.book.description;
            this.img = this.book.img;
          }
          else{
            alert('未检索到指定ID书籍！');
            location.reload();
          }

        });
        if (this.book != null) {
          this.flag = true
        }
      },
      modifyBook() {
        this.$axios.post('book/getById', this.book.id).then(book => {
          this.bookName = this.book.bookName;
          this.author = this.book.author;
          this.press = this.book.press;
          this.price = this.book.price;
          this.category_id = this.book.category_id;
          this.description = this.book.description;
          this.img = this.book.img;
        });
      },
      saveBook() {
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
          if (result.status != 200) {
            alert("保存失败！")
          } else {
            alert("保存成功！")
          }
        })
      },
      pullOff() {
        if (confirm('确定要下架书籍吗') == true) {
          this.$axios.post('book/delete', this.book.id).then((result) => {
            this.searchBook();
            console.log(result);
            if (result.status != 200) {
              alert("下架失败！")
            } else {
              alert("下架成功！")
              this.flag = false
              locaiton.reload()
            }
          })
        }
      }
    }
  }
</script>

<style scoped>
  #comment p {
    word-wrap: break-word;
  }

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
    top: -100px;
    height: 400px;
  }
</style>
