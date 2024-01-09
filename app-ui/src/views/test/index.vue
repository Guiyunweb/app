<template>
	<el-container>
		<el-main class="nopadding" style="padding: 13px 15px;">
			<avue-crud v-model="form"
					   :option="option"
					   :data="data"></avue-crud>
		</el-main>
	</el-container>
</template>

<script>
import '@smallwei/avue/lib/index.css';
export default {
	name: "index",
	data() {
		return {
			form: {},
			option: {},
			data: [{
				name: '张三',
				sex: 12
			}, {
				name: '李四',
				sex: 13
			}]
		}
	},
	created() {
		this.initOption()
	},
	methods: {
		initOption() {
			this.option = {
				index: true,
				size: 'mini',
				border:true,
				column: [{
					label: '姓名',
					prop: 'name'
				}, {
					label: '年龄',
					prop: 'sex'
				}]
			}
		}
	},

}
</script>

<style scoped>

</style>
