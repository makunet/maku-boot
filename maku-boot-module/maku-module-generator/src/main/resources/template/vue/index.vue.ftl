<template>
	<#if hasLeftTree>
	<el-row :gutter="10">
	  <el-col :span="5">
		<#if hasLeftFormDs??>
		<ma-data-tree-left url="${leftRequestUrl}/list" title="${leftTitle}" manage @click="handleClick" @manage="treeVisible = true" />
		<#else>
		<ma-data-tree-left url="${leftRequestUrl}" title="${leftTitle}" @click="handleClick" />
		</#if>
	  </el-col>
	  <el-col :span="19">
	</#if>
	<#if tableOperation?seq_contains('query')>
	<el-card class="layout-query">
		<el-form ref="queryRef" :inline="true" :model="state.queryForm" @keyup.enter="getDataList()">
		<#list queryList as field>
			<el-form-item prop="${field.attrName}">
			<#if hasTree && field.attrName == treePid>
			  <ma-data-tree-select
				  v-model="state.queryForm.${field.attrName}"
				  url="${requestUrl}/list"
				  :props="{ label: '${treeLabel}', value: '${treeId}', pid: '${treePid}' }"
				  placeholder="${field.fieldComment!}" />
			<#elseif field.queryFormType == 'input'>
			  <el-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}"></el-input>
			<#elseif field.queryFormType == 'select'>
			  <#if field.formDict??>
			  <ma-dict-select v-model="state.queryForm.${field.attrName}" dict-type="${field.formDict}" placeholder="${field.fieldComment!}" clearable></ma-dict-select>
			  <#else>
			  <el-select v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}">
				<el-option label="选择" value="0"></el-option>
			  </el-select>
			  </#if>
			<#elseif field.queryFormType == 'radio'>
			  <#if field.formDict??>
			  <ma-dict-radio v-model="state.queryForm.${field.attrName}" dict-type="${field.formDict}" placeholder="${field.fieldComment!}"></ma-dict-radio>
			  <#else>
			  <el-radio-group v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}">
				<el-radio :label="0">单选</el-radio>
			  </el-radio-group>
			  </#if>
			<#elseif field.queryFormType == 'date'>
		      <el-date-picker v-model="state.queryForm.${field.attrName}" value-format="YYYY-MM-DD" type="daterange" start-placeholder="${field.fieldComment!}" end-placeholder="结束日期" clearable />
			<#elseif field.queryFormType == 'datetime'>
			  <el-date-picker v-model="state.queryForm.${field.attrName}" value-format="YYYY-MM-DD HH:mm:ss" type="datetimerange" start-placeholder="${field.fieldComment!}" end-placeholder="结束日期" clearable />
			<#elseif field.queryFormType == 'user'>
				<ma-user-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}"></ma-user-input>
			<#elseif field.queryFormType == 'org'>
				<ma-org-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}"></ma-org-input>
			<#elseif field.queryFormType == 'post'>
				<ma-post-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}"></ma-post-input>
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
	</#if>

	<el-card>
		<#if tableOperation?seq_contains('insert') || tableOperation?seq_contains('import') || tableOperation?seq_contains('export') || tableOperation?seq_contains('delete')>
		<el-space>
			<#if tableOperation?seq_contains('insert')>
			<el-space>
				<el-button <#if authLevel==1>v-auth="'${authority}:save'" </#if>icon="Plus" type="primary" @click="addOrUpdateHandle()">新增</el-button>
			</el-space>
			</#if>
			<#if tableOperation?seq_contains('import')>
			<el-space <#if authLevel==1>v-auth="'${authority}:import'"</#if>>
				<ma-upload-excel action="${requestUrl}/import">
					<el-button plain icon="Upload">导入</el-button>
				</ma-upload-excel>
			</el-space>
			</#if>
			<#if tableOperation?seq_contains('export')>
			<el-space>
				<el-button <#if authLevel==1>v-auth="'${authority}:export'" </#if>plain icon="Download" @click="downloadHandle('${requestUrl}/export')">导出</el-button>
			</el-space>
			</#if>
			<#if tableOperation?seq_contains('delete')>
			<el-space>
				<el-button <#if authLevel==1>v-auth="'${authority}:delete'" </#if>icon="Delete" plain type="danger" @click="deleteBatchHandle()">批量删除</el-button>
			</el-space>
			</#if>
		</el-space>
		</#if>
		<el-table v-loading="state.dataListLoading" :data="state.dataList" border class="layout-table" show-overflow-tooltip @selection-change="selectionChangeHandle" <#if hasTree>row-key="id" lazy :load="load" :tree-props="{ hasChildren: 'hasChild' }"</#if>>
			<el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
	    <#list gridList as field>
		  <#if field.formDict??>
			<ma-dict-column prop="${field.attrName}" label="${field.fieldComment!}" dict-type="${field.formDict}"></ma-dict-column>
		  <#elseif field.formType=='image'>
			<ma-image-column prop="${field.attrName}" label="${field.fieldComment!}" header-align="center" align="center"></ma-image-column>
		  <#else>
			<el-table-column prop="${field.attrName}" label="${field.fieldComment!}" header-align="center" align="center"></el-table-column>
		  </#if>
        </#list>
			<#if tableOperation?seq_contains('update') || tableOperation?seq_contains('delete')>
			<el-table-column label="操作" fixed="right" header-align="center" align="center" width="150">
				<template #default="scope">
					<#if tableOperation?seq_contains('update')>
					<el-button <#if authLevel==1>v-auth="'${authority}:update'" </#if>type="primary" link @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
					</#if>
					<#if tableOperation?seq_contains('delete')>
					<el-button <#if authLevel==1>v-auth="'${authority}:delete'" </#if>type="primary" link @click="deleteBatchHandle(scope.row.id)">删除</el-button>
					</#if>
				</template>
			</el-table-column>
			</#if>
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

		<#if tableOperation?seq_contains('insert') || tableOperation?seq_contains('update')>
		<!-- 弹窗, 新增 / 修改 -->
		<add-or-update v-if="addOrUpdateVisible" ref="addOrUpdateRef" v-model:visible="addOrUpdateVisible" @refreshDataList="getDataList"></add-or-update>
		</#if>
		<#if hasLeftTree && hasLeftFormDs??>
		<el-drawer v-if="treeVisible" v-model="treeVisible" title="${leftTitle}" :size="800" :before-close="handleClose">
		  <Left${FunctionNameLeft}></Left${FunctionNameLeft}>
		</el-drawer>
		</#if>
	</el-card>
<#if hasLeftTree>
	</el-col>
  </el-row>
</#if>
</template>

<script setup lang="ts" name="${ModuleName}${FunctionName}Index">
	import {useCrud} from '@/hooks'
	import {reactive, nextTick, ref} from 'vue'
	import {IHooksOptions} from '@/hooks/interface'
	<#if tableOperation?seq_contains('insert') || tableOperation?seq_contains('update')>
	import AddOrUpdate from './add-or-update.vue'
	</#if>
	<#if hasTree>import { use${FunctionName}TreeLoadApi } from '@/api/${moduleName}/${functionName}'</#if>
	<#if hasLeftTree>
	<#if hasLeftFormDs??>
	import Left${FunctionNameLeft} from '../${functionNameLeft}/index.vue'
	import emit, { MittEvent } from '@/utils/emits'

	const treeVisible = ref(false)
	const handleClose = () => {
		treeVisible.value = false
		emit.emit(MittEvent.LeftTree)
	}
	</#if>
	const handleClick = (idList: any[]) => {
		state.queryForm.${leftRelationField} = idList
		getDataList()
	}

	</#if>
	const state: IHooksOptions = reactive({
	dataListUrl: '${requestUrl}/page',
	<#if tableOperation?seq_contains('query')>
	deleteUrl: '${requestUrl}',
	</#if>
	queryForm: {
		<#if hasLeftTree>${leftRelationField}: [],</#if>
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
<#if tableOperation?seq_contains('insert') || tableOperation?seq_contains('update')>
const addOrUpdateVisible = ref(false)
const addOrUpdateRef = ref()
const addOrUpdateHandle = (id?: number) => {
	addOrUpdateVisible.value = true
	nextTick(() => addOrUpdateRef.value.init(id))
}
</#if>

<#if hasTree>
// 树数据加载
const load = (tree: any, treeNode: unknown, resolve: (data: any[]) => void) => {
	use${FunctionName}TreeLoadApi(tree.${treeId}).then((res: any) => {
		resolve(res.data)
	})
}
</#if>

const { getDataList, selectionChangeHandle, sizeChangeHandle, currentChangeHandle, deleteBatchHandle, downloadHandle, reset } = useCrud(state)
</script>
