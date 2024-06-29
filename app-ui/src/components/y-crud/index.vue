<template>
  <div>
    <y-search :option="option" />
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
// import { useVModel } from '@vueuse/core';
import type { From } from '@/typings/from';

const props = defineProps<{
  form: any;
  option: From.Option;
}>();

// const emit = defineEmits(['update:form']);

// const form = useVModel(props, 'form', emit);

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
