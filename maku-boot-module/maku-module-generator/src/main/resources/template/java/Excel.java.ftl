package ${package}.${moduleName}.vo;

import lombok.Data;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fhs.core.trans.vo.TransPojo;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
<#list importList as i>
import ${i!};
</#list>

/**
 * ${tableComment}
 *
 * @author ${author} ${email!}
 * <a href="https://maku.net">MAKU</a>
 */
@Data
public class ${ClassName}ExcelVO implements TransPojo {
<#list gridList as field>
	<#if field.attrName == 'id'>
	<#assign isPrimaryKey = 'true'>
	</#if>
</#list>
<#if !isPrimaryKey??>
	@ExcelIgnore
	private ${primaryType} ${primaryName};

</#if>
<#list gridList as field>
	<#if field.fieldComment!?length gt 0>
	@ExcelProperty("${field.fieldComment}")
	</#if>
	<#if field.attrType == 'LocalDateTime'>
	@ExcelProperty(value = "${field.fieldComment}", converter = LocalDateTimeConverter.class)
	</#if>
	<#if field.formDict??>
	private String ${field.attrName}Label;

	@ExcelIgnore
	@Trans(type = TransType.DICTIONARY, key = "${field.formDict}", ref = "${field.attrName}Label")
	private ${field.attrType} ${field.attrName};
	<#else>
	private ${field.attrType} ${field.attrName};
	</#if>

</#list>
}