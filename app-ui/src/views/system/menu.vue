<template>
  <avue-crud
      @row-save="rowSave"
      @row-del="rowDel"
      @on-load="getList"
      @row-update="rowUpdate"
      :data="data" :option="option">
  </avue-crud>
</template>
<script>
import {ADD, DEL, GET_MENU_TREE, UPDATE_ID} from "@/api/menu";
import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      data: [],
      option: {
        rowKey: 'id',
        rowParentKey: 'parentId',
        dialogDirection: 'rtl',
        dialogType: 'drawer',
        border: true,
        column: [
          {
            label: '菜单名称',
            prop: 'title',
            width: 200
          },
          {
            label: '排序',
            prop: 'sortBy',
            type: 'number',
            width: 60
          },
          {
            label: '组件路径',
            prop: 'component'
          },
          {
            label: '父类',
            prop: 'parentId',
            type: 'select',
            hide: true,
            dicData: []
          },
          {
            label: '创建时间',
            prop: 'createTime',
            editDisplay: false,
            addDisplay: false
          },
        ],
        dicData: []
      },
    };
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      GET_MENU_TREE().then(res => {
        let data = res.data
        let dicData = []
        for (const datum of data) {
          dicData.push({
            label: datum.title,
            value: datum.id
          })
        }
        this.data = data
        this.option.column[3].dicData = dicData
      })
    },
    rowSave(row, done, loading) {
      ADD(row).then(res => {
        ElMessage({
          message: "新增成功",
          type: 'success',
          duration: 5 * 1000
        })
        done();
        this.getList()
      })
    },
    rowDel(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return DEL(row.id)
      }).then(() => {
        ElMessage({
          message: "删除成功",
          type: 'success',
          duration: 5 * 1000
        })
        this.getList();
      })
    },
    rowUpdate(row, index, done, loading) {
      let param = row
      param.meta = JSON.stringify(row.meta)
      UPDATE_ID(row.id, param).then(res => {
        ElMessage({
          message: "更新成功",
          type: 'success',
          duration: 5 * 1000
        })
        this.getList();
        done()
      })
    }
  }
}
</script>
