<#import "/vue/form.vue.ftl" as form>
<template>
	<#if openType==0>
	<el-dialog v-model="visible" :title="!dataForm.id ? '新增' : '修改'" :close-on-click-modal="false" draggable>
	<#else>
	<el-drawer v-model="visible" :title="!dataForm.id ? '新增' : '修改'" :size="1000">
	</#if>
		<@form.maForm></@form.maForm>
		<#if subs?size gt 0>
		<el-tabs v-model="activeName">
			<#list subs as sub>
				<el-tab-pane label="${sub.tableTitle}" name="${sub.className}">
					<${sub.ClassName} ref="${sub.className}Ref" :value="dataForm.${sub.className}"/>
				</el-tab-pane>
			</#list>
		</el-tabs>
		</#if>
		<template #footer>
			<el-button @click="visible = false">取消</el-button>
			<el-button type="primary" @click="submitHandle()">确定</el-button>
		</template>
	<#if openType==0>
	</el-dialog>
	<#else>
	</el-drawer>
	</#if>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import { use${FunctionName}Api, use${FunctionName}SubmitApi } from '@/api/${moduleName}/${functionName}'
<#list subs as sub>
import ${sub.ClassName} from './${sub.className}.vue'
</#list>

const emit = defineEmits(['refreshDataList'])

const visible = defineModel<boolean>('visible')
const dataFormRef = ref()
<#if subs?size gt 0>
const activeName = ref("${subs[0].className}")
</#if>
<#list subs as sub>
const ${sub.className}Ref = ref()
</#list>

const dataForm = reactive({
	<#list subs as sub>
	<#if sub.mainRelation == 1>
	${sub.className}: {},
	<#else>
	${sub.className}: [],
	</#if>
	</#list>
	<#list fieldList as field>
	${field.attrName}: ''<#sep>,
	</#list>
})

const init = (id?: number) => {
	if (id) {
		get${FunctionName}(id)
	}
}

const get${FunctionName} = (id: number) => {
	use${FunctionName}Api(id).then(res => {
		Object.assign(dataForm, res.data)
	})
}

const dataRules = ref({
	<#list formList as field>
	<#if field.formRequired>
	${field.attrName}: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]<#sep>,
	</#if>
	</#list>
})

// 表单提交
const submitHandle = () => {
	dataFormRef.value.validate(async (valid: boolean) => {
		if (!valid) {
			return false
		}

		<#list subs as sub>
		dataForm.${sub.className} = await ${sub.className}Ref.value.getData()
		</#list>

		use${FunctionName}SubmitApi(dataForm).then(() => {
			ElMessage.success({
				message: '操作成功',
				duration: 500,
				onClose: () => {
					visible.value = false
					emit('refreshDataList')
				}
			})
		})
	})
}

defineExpose({
	init
})
</script>
