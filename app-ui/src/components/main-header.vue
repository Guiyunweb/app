<template>
  <div class="main-breadcrumb">
    <el-breadcrumb :separator-icon="ArrowRight">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script setup>
import {ArrowRight} from '@element-plus/icons-vue'

</script>

<style scoped>
.main-breadcrumb{
  padding: 15px;
  border-bottom: 1px solid #e8eaec;
}
</style>
