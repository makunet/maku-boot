<#import "/vue/form-item.vue.ftl" as formItem>

<#macro maForm>
  <el-form ref="dataFormRef" :model="dataForm" :rules="dataRules" :label-width="100">
  <#list formList as field>
    <#if (formLayout > 1)>
    <#if field_index % 2 == 0>
    <el-row>
    </#if>
      <el-col :span="12">
        <@formItem.maFormItem model='dataForm' field=field></@formItem.maFormItem>
      </el-col><#nt>
    <#if field_index % 2 != 0 || formList?size == field_index + 1>
    </el-row>
    </#if>
    <#else>
    <@formItem.maFormItem model='dataForm' field=field ></@formItem.maFormItem>
    </#if>
  </#list>
  <#nested>
  </el-form>
</#macro>