package ${package}.${moduleName}.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import lombok.Data;
import java.io.Serializable;
import ${package}.framework.common.utils.DateUtils;
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
@Schema(description = "${tableComment}")
public class ${ClassName}VO implements Serializable {
	private static final long serialVersionUID = 1L;

<#list fieldList as field>
	<#if field.fieldComment!?length gt 0>
	@Schema(description = "${field.fieldComment}")
	</#if>
	<#if field.attrType == 'LocalDateTime'>
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	</#if>
	private ${field.attrType} ${field.attrName};

</#list>
<#list subs as sub>
	@Schema(description = "${sub.tableTitle}")
	<#if sub.mainRelation ==1>
	private ${sub.ClassName}VO ${sub.className};
	<#else>
	private List<${sub.ClassName}VO> ${sub.className};
	</#if>

</#list>
	<#if hasTree>
	@Schema(description = "是否有子节点")
	private Boolean hasChild;
	</#if>
}