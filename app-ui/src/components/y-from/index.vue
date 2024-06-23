<template>
  <n-form
    :model="form"
    :inline="option.inline"
    :rules="rules"
    :label-placement="option.labelPlacement ? option.labelPlacement : `left`"
  >
    <n-form-item v-for="item in option.column" :key="item.name" :label="item.label" :path="item.name">
      <n-input
        v-if="item.type === `text` || item.type === `password` || item.type === `textarea`"
        v-model:value="form[item.name]"
        :type="item.type"
        :placeholder="item.placeholder ? item.placeholder : `请输入` + item.name"
      />
      <n-input-number
        v-else-if="item.type === `number`"
        v-model:value="form[item.name]"
        :placeholder="item.placeholder ? item.placeholder : `请输入` + item.name"
      />

<!--      <n-radio-group v-else-if="item.type === `radio`" v-model:value="form[item.name]">-->
<!--        <n-radio v-for="song in item.select" :key="song.value" :value="song.value">-->
<!--          {{ song.label }}-->
<!--        </n-radio>-->
<!--      </n-radio-group>-->

      <n-select
        v-else-if="item.type === `select`"
        v-model:value="form[item.name]"
        :placeholder="item.placeholder"
        :options="item.select"
        clearable
      ></n-select>
    </n-form-item>
  </n-form>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { useVModel } from '@vueuse/core';
import type { From } from '@/typings/from';

const props = defineProps<{
  form: any;
  option: From.Option;
}>();

const emit = defineEmits(['update:form']);

const form = useVModel(props, 'form', emit);

const rules = ref({});

init();

function init() {
  const columns = props.option.column;
  const rule: From.Rules = {};
  for (const column of columns) {
    if (column.notNull) {
      rule[column.name] = {
        required: true,
        trigger: ['input', 'blur'],
        message: `请输入${column.label}`
      };
    }
  }
  rules.value = rule;
}
</script>
