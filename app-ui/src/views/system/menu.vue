<template>
  <avue-crud :data="data" :option="option"></avue-crud>
</template>
<script>
export default {
  data () {
    return {
      data: [
        {
          name: '张三',
          sex: '男'
        }, {
          name: '李四',
          sex: '女'
        }, {
          name: '张三',
          sex: '男'
        }, {
          name: '李四',
          sex: '女'
        }, {
          name: '张三',
          sex: '男'
        }, {
          name: '李四',
          sex: '女'
        }, {
          name: '张三',
          sex: '男'
        }, {
          name: '李四',
          sex: '女'
        }
      ],
      option: {
        column: [
          {
            label: '姓名',
            prop: 'name',
            width: 200,
            fixed: true
          }, {
            label: '性别',
            width: 300,
            prop: 'sex'
          }, {
            label: '日期',
            width: 300,
            prop: 'datetime'
          }, {
            label: '地址',
            width: 300,
            prop: 'address'
          }
        ]
      },
    };
  },
  methods: {
  }
}
</script>
