<template>
  <div class="container ml-3">
    <form class="row" id="addForm">
      <div class="form-group col-lg-5">
        书籍名称：
        <input @keyup.enter="focusChange(this)" v-model="addbookname" type="text" class="form-control">
        作者：
        <input @keyup.enter="focusChange(this)" v-model="addAuthor" type="text" class="form-control">
        出版社：
        <input @keyup.enter="focusChange(this)" v-model="addPress" type="text" class="form-control">
        书籍分类：

        <select v-model="addCategory_id" class="form-control">
          <option disabled value="">请选择</option>
          <option value="0">小说</option>
          <option value="1">编程</option>
          <option value="2">美食</option>
          <option value="3">商业</option>
          <option value="4">建筑</option>
        </select>

        价格：
        <input @keyup.enter="focusChange(this)" v-model="addPrice" type="text" class="form-control">
        书籍封面：
        <input @keyup.enter="focusChange(this)" v-model="imgSrc" type="text" class="form-control">
      </div>
      <div class="form-group col-lg-7 pl-5">
        <div class="row mt-3 mb-3">
          简介：
          <textarea v-model="addDescription" class="form-control" rows="5"></textarea>
        </div>
        <div class="row">
          <div class="col-lg-6">
            <input class="btn btn-primary btn-block btn-sm mt-2" type="reset" value="取消">
          </div>
          <div class="col-lg-6">
            <input @click="addBook()" class="btn btn-primary btn-block btn-sm mt-2" value="确定">
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
  export default {
    name: "AddBook",
    data() {
      return {
        addbookname: "",
        addAuthor: "",
        addPress: "",
        addCategory_id: "",
        addPrice: "",
        addDescription: "",
        imgSrc: ''
      };
    },
    methods: {
      focusChange(current) {
        console.log(current);
        let input = $('#addForm input');
        for (let i = 0; i < input.length; i++) {
          if (current == input[i]) {
            input[i + 1].focus();
            break;
          }
        }
      },
      
      addBook() {
        let book = {
          bookName: this.addbookname,
          author: this.addAuthor,
          press: this.addPress,
          category_id: this.addCategory_id,
          price: this.addPrice,
          description: this.addDescription,
          img: this.imgSrc,
        };
        this.$axios.post('book/add', book).then((result1) => {
          console.log(result1)
          if (result1.status != 200) {
            alert('添加书籍失败！')
          } else {
            alert("添加成功！")
            location.reload()
          }
        })
      }
    }
  }
</script>

<style scoped>
  #cover {
    max-height: 250px;
  }
</style>
