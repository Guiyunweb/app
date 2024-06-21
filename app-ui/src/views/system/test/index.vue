<template>
  <div class="h-full">
    <n-card title="测试" class="h-full shadow-sm rounded-16px">
      <y-from v-model:form="from" :option="option"></y-from>
      {{ from }}
      <n-button type="primary" @click="submit"> Primary </n-button>
    </n-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue';
import type { From } from '@/typings/from';

const option: From.Option = reactive({
  inline: true,
  column: [
    {
      label: '姓名',
      name: 'name',
      type: 'text',
      notNull: true
    },
    {
      label: '年龄',
      name: 'age',
      type: 'number',
      placeholder: '请输入XXX'
    },
    {
      label: '性别',
      name: 'sex',
      type: 'select',
      select: [
        {
          label: '男',
          value: 1,
          checked: true
        },
        {
          label: '女',
          value: 0
        }
      ]
    }
  ]
});

const from = ref({});

function submit() {
  // eslint-disable-next-line no-console
  console.log(from.value);
}
</script>

<style scoped></style>
