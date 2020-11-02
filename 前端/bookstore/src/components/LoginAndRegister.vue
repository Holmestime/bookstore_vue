<!--注册组件-->
<template>
  <body id="poster">
    <div style="padding-top: 15px">
      <el-button type="primary" round @click="back">回到首页</el-button>
    </div>
    <div class="container">
      <el-tabs type="border-card">
        <!--登录表单-->
        <el-tab-pane label="登录">
          <!--label-position指定label右对齐，label-width属性指定label标签的宽度-->
          <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-position="right" label-width="90px">
            <!--用户名-->
            <el-form-item label="用户名：" prop="username">
              <el-input type="text" v-model="loginForm.username" auto-complete="off"></el-input>
            </el-form-item>
            <!--密码-->
            <el-form-item label="密码：" prop="password">
              <el-input type="password" v-model="loginForm.password" auto-complete="off"></el-input>
            </el-form-item>
            <!--登录按钮-->
            <el-form-item style="width: 100%">
              <el-button type="primary" style="width: 100%; background: #505458;border: none" @click="login">登录</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <!--注册表单-->
        <el-tab-pane label="注册">
          <!--label-position指定label右对齐，label-width属性指定label标签的宽度-->
          <el-form ref="registerFormRef" :model="registerForm" :rules="registerFormRules" label-position="right" label-width="90px">
            <!--用户名-->
            <el-form-item label="用户名：" prop="username">
              <el-input type="text" v-model="registerForm.username" auto-complete="off"></el-input>
            </el-form-item>
            <!--密码-->
            <el-form-item label="密码：" prop="password">
              <el-input type="password" v-model="registerForm.password" auto-complete="off"></el-input>
            </el-form-item>
            <!--注册按钮-->
            <el-form-item style="width: 100%">
              <el-button type="primary" style="width: 100%; background: #505458;border: none" @click="register">注册</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </body>
</template>

<script>
  import {
    mapMutations
  } from 'vuex'
  export default {
    name: 'LoginAndRegister.vue',
    data() {
      return {
        // 登录表单的数据绑定对象
        loginForm: {
          // 用户名
          username: 'admin2',
          // 密码
          password: 'liushi2'
        },
        // 登录表单的验证规则对象
        loginFormRules: {
          // 验证用户名是否合法
          username: [{
              required: true,
              message: '请输入用户名',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 11,
              message: '用户名长度在5到11个字符',
              trigger: 'blur'
            }
          ],
          // 验证密码是否合法
          password: [{
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            {
              min: 6,
              max: 16,
              message: '密码长度在6到16个字符',
              trigger: 'blur'
            }
          ]
        },
        // 注册表单的数据绑定对象
        registerForm: {
          // 用户名
          username: '',
          // 密码
          password: ''
        },
        // 注册表单的验证规则对象
        registerFormRules: {
          // 验证用户名是否合法
          username: [{
              required: true,
              message: '请输入用户名',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 11,
              message: '用户名长度在5到11个字符',
              trigger: 'blur'
            }
          ],
          // 验证密码是否合法
          password: [{
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            {
              min: 6,
              max: 16,
              message: '密码长度在6到16个字符',
              trigger: 'blur'
            }
          ]
        }
      }
    },
    methods: {
      // 将指定的mutations函数，映射为当前组件的methods函数
      ...mapMutations(['setUser', 'setId', 'setPassword']),

      // 登录方法
      login() {
        // 表单提交前的预验证
        this.$refs.loginFormRef.validate(async valid => {
          if (!valid) {
            console.log('表单提交失败')
            return
          }
          // 表单提交到后台进行验证
          const {
            data: result
          } = await this.$axios.post('user/login', {
            username: this.loginForm.username,
            password: this.loginForm.password
          })
          console.log(result)
          if (result.code !== 200) {
            return this.$message.error('用户名或密码错误，登录失败')
          }
          this.$message.success('登录成功')
          // 发送请求获取userid
          const {
            data: res
          } = await this.$axios.post('user/getId', { username:this.loginForm.username })
          // 存储登录信息
          // username和userid、password存入sessionStorage
          sessionStorage.setItem('username', this.loginForm.username)
		  sessionStorage.setItem('userid', res)
          sessionStorage.setItem('password', this.loginForm.password)
          // 将username和userid、password存入vuex
          this.setUser(this.loginForm.username)
		  this.setId(res)
		  this.setPassword(this.loginForm.password)
          
          // 通过编程式导航跳转到后台主页，路由地址是/Home
          if (this.loginForm.username == 'admin') {
            this.$router.push('/manage')
          } else {
            
            this.$router.push('/Home')
          }
        })
      },

      // 注册方法
      register() {
        // 表单提交前的预验证
        this.$refs.registerFormRef.validate(async valid => {
          if (!valid) {
            console.log('表单提交失败')
            return
          }
          // 表单提交到后台验证用户名是否重复
          const {
            data: result
          } = await this.$axios.post('user/register', {
            username: this.registerForm.username,
            password: this.registerForm.password
          })
          if (result.code !== 200) {
            return this.$message.error('用户名已存在，请更换用户名')
          }
          this.$message.success('注册成功')
        })
      },

	  // 返回首页
	  back() {
        this.$router.push('/Home')
      }
    }
  }
</script>

<style scoped>
  #poster {
    background: url("../assets/eva1.jpg") center no-repeat;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }

  body {
    margin: 0px;
  }

  .container {
    /*border-radius: 25px;*/
    background-clip: padding-box;
    margin: 110px auto;
    width: 400px;
    /* padding: 35px 35px 15px 35px;*/
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login-title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
