<template>
  <el-container>
    <el-header style="border-bottom: 1px solid darkgray; padding-top: 15px">
      <el-button type="primary" round @click="back">回到首页</el-button>
      <span class="center">个人中心</span>
    </el-header>
    <el-main class="container">
      <div style="font-size: 30px;margin-bottom: 80px">
        欢迎来到个人中心!
      </div>
      修改密码
      <div class="form">
        <el-form ref="changePasswordFormRef" :model="changePasswordForm" :rules="changePassowordFormRules" label-width="120px">
          <el-form-item label="当前密码：" prop="oldPassword">
            <el-input type="password" v-model="changePasswordForm.oldPassword" auto-complete="off"></el-input>
          </el-form-item>
		  <el-form-item label="新密码：" prop="newPassword">
            <el-input type="password" v-model="changePasswordForm.newPassword" auto-complete="off"></el-input>
          </el-form-item>
          <el-button type="primary" @click="changePassword">修改</el-button>
        </el-form>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
export default {
  name: 'Center',
  data () {
    return {
      // 修改密码表单的数据绑定对象
      changePasswordForm: {
        oldPassword: '',
        newPassword: ''
      },
      // 修改密码表单的验证规则对象
      changePassowordFormRules: {
        // 验证旧密码是否合法
        oldPassword: [
          { required: true, message: '请输入当前密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度在6到16个字符', trigger: 'blur' }
        ],
		// 验证新密码是否合法
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' },
          { min: 6, max: 16, message: '密码长度在6到16个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 将指定的mapMutations函数，映射为当前组件的methods方法
    ...mapMutations(['setUser']),
    // 返回首页
    back () {
      this.$router.push('/Home')
    },
    // 修改密码
    changePassword () {
      // 表单提交前的预验证
      this.$refs.changePasswordFormRef.validate(async valid => {
        if (!valid) {
          return this.$message.error('请填写新密码')
        }
		if (this.changePasswordForm.oldPassword !== this.$store.state.password) {
          return this.$message.error('旧密码错误，请重新输入')
        }
        // 表单提交到后台进行验证
        const { data: result } = await this.$axios.post('user/pwChange', {
          username: this.username,
          password: this.changePasswordForm.newPassword
        })
        if (result.code !== 200) {
          this.changePasswordForm.oldPassword = ''
          return this.$message.error('修改失败')
        }
        this.$message.success('修改成功，请重新登录')
        // 清空登录信息
        this.setUser(null)
        // 跳转到登录页面重新登录
        this.$router.push('/LoginAndRegister')
      })
    }
  },
  computed: {
    ...mapState(['username'])
  }
}
</script>

<style scoped>
  .center {
    position: absolute;
    right: 50%;
    font-size: 22px;
    font-weight: bold;
    color: red;
  }
  .container {
    margin: 10px 7% 100px;
    text-align: center;
  }
  .form {
    margin: 30px auto 0;
    width: 550px;
  }
</style>
