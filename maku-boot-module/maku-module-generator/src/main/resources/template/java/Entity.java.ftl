package ${package}.${moduleName}.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
<#list importList as i>
import ${i!};
</#list>
<#if baseClass??>
import ${baseClass.packageName}.${baseClass.code};
</#if>

/**
 * ${tableComment}
 *
 * @author ${author} ${email!}
 * <a href="https://maku.net">MAKU</a>
 */
<#if baseClass??>@EqualsAndHashCode(callSuper=false)</#if>
@Data
@TableName("${tableName}")
public class ${ClassName}Entity<#if baseClass??> extends ${baseClass.code}</#if> {
<#list fieldList as field>
<#if !field.baseField>
	<#if field.fieldComment!?length gt 0>
	/**
	* ${field.fieldComment}
	*/
	</#if>
	<#if field.primaryPk>
	@TableId
	</#if>
    <#if field.autoFill == "INSERT">
	@TableField(value = "${field.fieldName}", fill = FieldFill.INSERT)
	<#elseif field.autoFill == "INSERT_UPDATE">
	@TableField(value = "${field.fieldName}", fill = FieldFill.INSERT_UPDATE)
	<#elseif field.autoFill == "UPDATE">
	@TableField(value = "${field.fieldName}", fill = FieldFill.UPDATE)
	<#elseif hasTree && field.fieldName == treePid>
	@TableField(value = "${field.fieldName}", updateStrategy = FieldStrategy.ALWAYS)
	<#else>
	@TableField(value = "${field.fieldName}")
	</#if>
	private ${field.attrType} ${field.attrName};
</#if>

</#list>
}