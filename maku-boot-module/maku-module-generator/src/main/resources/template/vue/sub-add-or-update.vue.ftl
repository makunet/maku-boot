<#import "/vue/form.vue.ftl" as form>
<template>
	<#if mainRelation == 1>
		<@form.maForm></@form.maForm>
	</#if>
	<#if mainRelation == 2>
		<el-button type="primary" plain icon="Plus" @click="openDialog">新增</el-button>
		<el-button type="danger" plain icon="Delete" @click="removeBatchEvent">删除</el-button>
		<vxe-table ref="xTable" max-height="500" style="margin-top: 10px" :data="value">
			<vxe-column type="checkbox" width="60"></vxe-column>
			<#list formList as field>
				<vxe-column field="${field.attrName}" title="${field.fieldComment!}"></vxe-column>
			</#list>
			<vxe-column title="操作" width="100" show-overflow>
				<template #default="{ row }">
					<vxe-button type="text" icon="vxe-icon-edit" @click="editEvent(row)"></vxe-button>
					<vxe-button type="text" icon="vxe-icon-delete" @click="removeEvent(row)"></vxe-button>
				</template>
			</vxe-column>
		</vxe-table>
		<el-dialog v-model="visible" :title="!selectRow ? '新增' : '修改'" draggable @close="closeDialog">
			<@form.maForm></@form.maForm>
			<template #footer>
				<el-button @click="closeDialog">取消</el-button>
				<el-button type="primary" @click="submitEvent">确定</el-button>
			</template>
		</el-dialog>
	</#if>
</template>

<script setup lang="ts">
<#if mainRelation == 1>
import { computed, PropType, ref } from 'vue'
<#else>
import { nextTick, reactive, ref } from 'vue'
import VXETable from 'vxe-table'
</#if>
import { ElMessage } from 'element-plus/es'

<#if mainRelation == 1>
const props = defineProps({
	value: {
		type: Object as PropType<any>,
		default: () => ({})
	}
})

const dataFormRef = ref()
const dataForm = computed(() => props.value)

const dataRules = ref({
	<#list formList as field>
	<#if field.formRequired>
	${field.attrName}: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]<#sep>,
	</#if>
	</#list>
})

const getData = async () => {
	return await new Promise((resolve, reject) => {
		dataFormRef.value
			.validate()
			.then((validate: boolean) => {
				if (validate) {
					resolve(dataForm)
				} else {
					ElMessage.error("表单效验失败")
				}
			})
			.catch((error: Error) => {
				reject(error)
			})
	})
}

defineExpose({
	getData
})
<#else>
const props = defineProps({
	value: {
		type: Array,
		default: () => []
	}
})

const dataFormRef = ref()
const visible = ref(false)
const xTable = ref()
const selectRow = ref()

const dataForm = reactive({
	<#list fieldList as field>
	${field.attrName}: ''<#sep>,
	</#list>
})

const dataRules = ref({
	<#list formList as field>
	<#if field.formRequired>
	${field.attrName}: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]<#sep>,
	</#if>
	</#list>
})

const openDialog = () => {
	dataForm.id = ''
	visible.value = true
	selectRow.value = null

	dataFormRef.value?.resetFields()
}

const closeDialog = () => {
	visible.value = false
	dataFormRef.value?.resetFields()
}

const editEvent = (row: any) => {
	visible.value = true

	dataFormRef.value?.resetFields()

	selectRow.value = row
	nextTick(() => {
		Object.assign(dataForm, { ...row })
	})
}

const removeEvent = async (row: any) => {
	const type = await VXETable.modal.confirm("您确定要删除该数据?")
	if (type === "confirm") {
		const $table = xTable.value
		if ($table) {
			$table.remove(row)
		}
	}
}

const removeBatchEvent = () => {
	const selectRecords = xTable.value.getCheckboxRecords()
	if (selectRecords.length) {
		xTable.value.removeCheckboxRow()
	} else {
		ElMessage.error("请至少选择一条数据")
	}
}

const submitEvent = () => {
	dataFormRef.value.validate(async (valid: boolean) => {
		if (!valid) {
			return false
		}

		if (selectRow.value) {
			Object.assign(selectRow.value, dataForm)
		} else {
			await xTable.value.insertAt({ ...dataForm }, -1)
		}

		// 关闭对话框
		closeDialog()
	})
}

const getData = async () => {
	const data = xTable.value.getTableData().tableData

	// 删除自带的属性
	data.map((e: any) => {
		delete e._X_ROW_KEY
	})

	return data
}

defineExpose({
	getData
})
</#if>


</script>
