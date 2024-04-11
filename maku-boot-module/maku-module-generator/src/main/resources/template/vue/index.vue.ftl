<template>
	<el-card class="layout-query">
		<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList()">
		<#list queryList as field>
			<el-form-item prop="${field.attrName}">
			<#if field.formType == 'text' || field.formType == 'textarea' || field.formType == 'editor'>
			  <el-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}"></el-input>
			<#elseif field.queryFormType == 'select'>
			  <#if field.queryDict??>
			  <ma-dict-select v-model="state.queryForm.${field.attrName}" dict-type="${field.queryDict}" placeholder="${field.fieldComment!}" clearable></ma-dict-select>
			  <#else>
			  <el-select v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}">
				<el-option label="选择" value="0"></el-option>
			  </el-select>
			  </#if>
			<#elseif field.queryFormType == 'radio'>
			  <#if field.formDict??>
			  <ma-dict-radio v-model="state.queryForm.${field.attrName}" dict-type="${field.formDict}"></ma-dict-radio>
			  <#else>
			  <el-radio-group v-model="state.queryForm.${field.attrName}">
				<el-radio :label="0">单选</el-radio>
			  </el-radio-group>
			  </#if>
			<#elseif field.queryFormType == 'date'>
			  <el-date-picker
				v-model="daterange"
				type="daterange"
				value-format="yyyy-MM-dd">
			  </el-date-picker>
			<#elseif field.queryFormType == 'datetime'>
			  <el-date-picker
				v-model="datetimerange"
				type="datetimerange"
				value-format="yyyy-MM-dd HH:mm:ss">
			  </el-date-picker>
			<#else>
			  <el-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}"></el-input>
			</#if>
			</el-form-item>
		  </#list>
			<el-form-item>
				<el-button icon="Search" type="primary" @click="getDataList()">查询</el-button>
			</el-form-item>
			<el-form-item>
				<el-button icon="RefreshRight" @click="reset(queryRef)">重置</el-button>
			</el-form-item>
		</el-form>
	</el-card>

	<el-card>
		<el-space>
			<el-space>
				<el-button v-auth="'${moduleName}:${functionName}:save'" icon="Plus" type="primary" @click="addOrUpdateHandle()">新增</el-button>
			</el-space>
			<el-space>
				<el-button v-auth="'${moduleName}:${functionName}:delete'" icon="Delete" plain type="danger" @click="deleteBatchHandle()">批量删除</el-button>
			</el-space>
		</el-space>
		<el-table v-loading="state.dataListLoading" :data="state.dataList" border class="layout-table" @selection-change="selectionChangeHandle">
			<el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
	    <#list gridList as field>
		  <#if field.formDict??>
			<ma-dict-table prop="${field.attrName}" label="${field.fieldComment!}" dict-type="${field.formDict}"></ma-dict-table>
		  <#else>
			<el-table-column prop="${field.attrName}" label="${field.fieldComment!}" header-align="center" align="center"></el-table-column>
		  </#if>
        </#list>
			<el-table-column label="操作" fixed="right" header-align="center" align="center" width="150">
				<template #default="scope">
					<el-button v-auth="'${moduleName}:${functionName}:update'" type="primary" link @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
					<el-button v-auth="'${moduleName}:${functionName}:delete'" type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			:current-page="state.page"
			:page-sizes="state.pageSizes"
			:page-size="state.limit"
			:total="state.total"
			layout="total, sizes, prev, pager, next, jumper"
			@size-change="sizeChangeHandle"
			@current-change="currentChangeHandle"
		>
		</el-pagination>

		<!-- 弹窗, 新增 / 修改 -->
		<add-or-update ref="addOrUpdateRef" @refreshDataList="getDataList"></add-or-update>
	</el-card>
</template>

<script setup lang="ts" name="${ModuleName}${FunctionName}Index">
	import {useCrud} from '@/hooks'
	import {reactive, ref} from 'vue'
	import {IHooksOptions} from '@/hooks/interface'
	import AddOrUpdate from './add-or-update.vue'

	const state: IHooksOptions = reactive({
	dataListUrl: '/${moduleName}/${functionName}/page',
	deleteUrl: '/${moduleName}/${functionName}',
	queryForm: {
		<#list queryList as field>
		<#if field.formType == 'date'>
		startDate: '',
		endDate: ''<#sep>, </#sep>
		<#elseif field.formType == 'datetime'>
		startDateTime: '',
		endDateTime: ''<#sep>, </#sep>
		<#else>
		${field.attrName}: ''<#sep>, </#sep>
		</#if>
		</#list>
	}
})

const queryRef = ref()
const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: number) => {
	addOrUpdateRef.value.init(id)
}

const { getDataList, selectionChangeHandle, sizeChangeHandle, currentChangeHandle, deleteBatchHandle, reset } = useCrud(state)
</script>
