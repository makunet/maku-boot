<#macro maFormItem model field>
  <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
  <#if hasTree && field.fieldName == treePid>
      <ma-data-tree-select
          v-model="${model}.${field.attrName}"
          url="${requestUrl}/list"
          :props="{ label: '${treeLabel}', value: '${treeId}' }"
          pplaceholder="${field.fieldComment!}" />
  <#elseif field.formType == 'input'>
      <el-input v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></el-input>
  <#elseif field.formType == 'number'>
      <el-input-number v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></el-input-number>
  <#elseif field.formType == 'textarea'>
      <el-input type="textarea" v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></el-input>
  <#elseif field.formType == 'editor'>
      <ma-editor v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></ma-editor>
  <#elseif field.formType == 'select'>
    <#if field.formDict??>
        <ma-dict-select v-model="${model}.${field.attrName}" dict-type="${field.formDict}" placeholder="${field.fieldComment!}"></ma-dict-select>
    <#else>
        <el-select v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}">
          <el-option label="请选择" :value="0"></el-option>
        </el-select>
    </#if>
  <#elseif field.formType == 'radio'>
    <#if field.formDict??>
        <ma-dict-radio v-model="${model}.${field.attrName}" dict-type="${field.formDict}"></ma-dict-radio>
    <#else>
        <el-radio-group v-model="${model}.${field.attrName}">
          <el-radio :value="0">启用</el-radio>
          <el-radio :value="1">禁用</el-radio>
        </el-radio-group>
    </#if>
  <#elseif field.formType == 'checkbox'>
      <el-checkbox-group v-model="${model}.${field.attrName}">
          <el-checkbox :value="1">启用</el-checkbox>
          <el-checkbox :value="0">禁用</el-checkbox>
      </el-checkbox-group>
  <#elseif field.formType == 'address'>
      <ma-address v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></ma-address>
  <#elseif field.formType == 'date'>
      <el-date-picker type="date" placeholder="${field.fieldComment!}" v-model="${model}.${field.attrName}"></el-date-picker>
  <#elseif field.formType == 'datetime'>
      <el-date-picker type="datetime" placeholder="${field.fieldComment!}" v-model="${model}.${field.attrName}"></el-date-picker>
  <#elseif field.formType == 'treeselect'>
      <el-tree-select v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></el-tree-select>
  <#elseif field.formType == 'user'>
      <ma-user-input v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></ma-user-input>
  <#elseif field.formType == 'org'>
      <ma-org-select v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></ma-org-select>
  <#elseif field.formType == 'post'>
      <ma-post-input v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></ma-post-input>
  <#else>
      <el-input v-model="${model}.${field.attrName}" placeholder="${field.fieldComment!}"></el-input>
  </#if>
  </el-form-item>
</#macro>
