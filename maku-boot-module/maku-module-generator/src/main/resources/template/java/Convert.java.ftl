package ${package}.${moduleName}.convert;

import ${package}.${moduleName}.entity.${ClassName}Entity;
import ${package}.${moduleName}.vo.${ClassName}VO;
<#if tableOperation?seq_contains('import') || tableOperation?seq_contains('export')>
import ${package}.${moduleName}.vo.${ClassName}ExcelVO;
</#if>
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * ${tableComment}
 *
 * @author ${author} ${email!}
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface ${ClassName}Convert {
    ${ClassName}Convert INSTANCE = Mappers.getMapper(${ClassName}Convert.class);

    ${ClassName}Entity convert(${ClassName}VO vo);

    ${ClassName}VO convert(${ClassName}Entity entity);

    List<${ClassName}VO> convertList(List<${ClassName}Entity> list);

    List<${ClassName}Entity> convertList2(List<${ClassName}VO> list);

    <#if tableOperation?seq_contains('import') || tableOperation?seq_contains('export')>
    List<${ClassName}ExcelVO> convertExcelList(List<${ClassName}Entity> list);

    List<${ClassName}Entity> convertExcelList2(List<${ClassName}ExcelVO> list);
    </#if>
}