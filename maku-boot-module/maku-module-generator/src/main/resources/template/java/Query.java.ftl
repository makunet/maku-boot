package ${package}.${moduleName}.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ${package}.framework.common.query.Query;

<#list importList as i>
import ${i!};
</#list>

/**
* ${tableComment}查询
*
* @author ${author} ${email}
* @since ${version} ${date}
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "${tableComment}查询")
public class ${ClassName}Query extends Query {
<#list queryList as field>
    <#if field.fieldComment!?length gt 0>
    @Schema(description = "${field.fieldComment}")
    </#if>
    private ${field.attrType} ${field.attrName};

</#list>
}