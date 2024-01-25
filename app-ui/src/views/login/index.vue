<template>
  <div class="main">
    <el-card class="box-card">
      <h1 style="text-align: center">登录</h1>

      <el-form ref="loginForm" :model="form" label-width="0" size="large">
        <el-form-item prop="user">
          <el-input v-model="form.account" clearable placeholder="用户名"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" clearable show-password placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%;" round @click="login">
            登录
          </el-button>
        </el-form-item>
      </el-form>

    </el-card>
  </div>
</template>

<script setup>

import {ref} from "vue";
import {SYS_USER_LOGIN} from "@/api/system";
import {ElMessage} from "element-plus";
import util from "@/libs/util";
import {useRouter} from "vue-router";
import {GET_MENU_TREE} from "@/api/menu";
import config from "@/config";

const form = ref({
  account: null,
  password: null
})

const router = useRouter()


async function login() {
  util.data.clear()
  util.cookies.remove('token')
  // 登录
  let user = form.value
  let res = await SYS_USER_LOGIN(user)
  if (!res.success) {
    ElMessage.error(res.message)
    return
  }

  // 保存Token
  util.cookies.set('token', res.data.token)
  util.data.set('user', res.data.user)

  // 菜单查询
  let menu = await GET_MENU_TREE()
  if (!menu.success) {
    ElMessage.error("当前用户无任何菜单权限，请联系系统管理员\", \"无权限访问")
    return
  }

  // 保存Menu
  util.data.set('menu', menu.data)
  // await router.push(config.DASHBOARD_URL)
  window.location = config.DASHBOARD_URL
}

</script>

<style scoped>
.main {
  background-image: url("../../assets/images/background.webp");
  width: 100vw;
  height: 100vh;
  position: absolute;
}

.box-card {
  width: 20vw;
  margin: 15vh auto;
  padding: 10px;
}
</style>
