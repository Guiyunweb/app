<template>
  <n-form
    :model="form"
    :inline="option.inline"
    style="flex-wrap: wrap"
    :label-placement="option.labelPlacement ? option.labelPlacement : `left`"
  >
    <n-form-item v-for="item in option.column" :key="item.name" :label="item.label" :path="item.name">
      <!--文本-->
      <n-input
        v-if="item.type === `text` || item.type === `password` || item.type === `textarea`"
        v-model="form[item.name]"
        :type="item.type"
        :placeholder="item.placeholder ? item.placeholder : `请输入` + item.name"
      />

      <!--数字-->
      <n-input-number
        v-else-if="item.type === `number`"
        v-model:value="form[item.name]"
        :placeholder="item.placeholder ? item.placeholder : `请输入` + item.name"
      />

      <!--单选-->
      <n-radio-group v-else-if="item.type === `radio`" v-model:value="form[item.name]">
        <n-radio v-for="song in item.radio" :key="song.value" :value="song.value">
          {{ song.label }}
        </n-radio>
      </n-radio-group>

      <!--选择框-->
      <n-select
        v-else-if="item.type === `select`"
        v-model:value="form[item.name]"
        :options="item.select"
        :style="{ width: item.labelWidth ? item.labelWidth + 'px' : '200px' }"
      />

      <!--时间-->
      <n-date-picker v-else-if="item.type === `date`" v-model:value="form[item.name]" type="date" />

      <n-date-picker v-else-if="item.type === `daterange`" v-model:value="form[item.name]" type="daterange" />
    </n-form-item>
    <n-form-item>
      <n-button type="primary"> 搜索 </n-button>
    </n-form-item>
  </n-form>
  {{ form }}
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import type { From } from '@/typings/from';

const form: any = ref({});
defineProps<{
  option: From.Option;
}>();
</script>
