<template>
  <el-container class="admin">
    <el-header class="admin-header">
      <div class="admin-header-left">
        <el-image style="width: 40px; height: 40px" src="https://lolicode.gitee.io/scui-doc/demo/img/logo.png"/>
        <h2 style="margin-left: 10px">logo</h2>
      </div>
      <div class="admin-header-right">
        <el-dropdown>
          <el-button type="primary" link color="#000">
            {{ user.username }}
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside class="admin-side-scroll">
        <el-scrollbar>
          <el-menu default-active="2" class="admin-side-scrollbar">
            <div v-for="item in menu" :key="`item_`+item.id">
              <el-menu-item v-if="!item.children.length>0" :index="item.id" @click="getPath(item.path)">
                {{ item.title }}
              </el-menu-item>
              <el-sub-menu :index="`item_`+item.id" v-else>
                <template #title>{{ item.title }}</template>
                <el-menu-item v-for="sub in item.children" :key="sub.id" :index="`sub_`+sub.id"
                              @click="getPath(sub.path)">
                  {{ sub.title }}
                </el-menu-item>
              </el-sub-menu>
            </div>
          </el-menu>
        </el-scrollbar>
      </el-aside>
      <el-container>
        <el-main class="admin-main">
          <main-header />
          <el-main>
            <router-view class="main"/>
          </el-main>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>


<script setup>
import {RouterView, useRouter} from 'vue-router'
import util from "@/libs/util";
import MainHeader from "@/components/main-header.vue";

let menu = util.data.get("menu")
let user = util.data.get("user")
const router = useRouter()


function logout() {
  util.data.clear()
  util.cookies.remove('token')
  router.push("/login")
}

function getPath(path) {
  router.push(path)
}

</script>


<style scoped>
.admin {
  min-height: 100vh;
}

.admin-header {
  background: #222b45;
  color: #FFF;
  display: flex;
  height: 58px;
  justify-content: space-between;
}

.admin-header-left {
  display: flex;
  align-items: center;
}

.admin-header-right {
  display: flex;
  align-items: center;
  text-align: right;
  margin-right: 20px;
}

.admin-side-scroll {
  overflow: auto;
  overflow-x: hidden;
  width: 210px;
}

.admin-main {
  background: #FFFFFF;
  flex: 1;
  margin: 0;
  padding: 0;
}

.main {
}

.admin-side-scrollbar {
  height: calc(100vh - 58px);
}
</style>
