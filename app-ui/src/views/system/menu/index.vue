<template>
  <n-form-item label="姓名" path="user.name" label-placement="top" label-align="left">
    <n-input v-model:value="formValue" placeholder="输入姓名" />
  </n-form-item>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const formValue = ref();
</script>
