package ${package}.${moduleName}.service;

import ${package}.framework.common.utils.PageResult;
import ${package}.framework.mybatis.service.BaseService;
import ${package}.${moduleName}.vo.${ClassName}VO;
import ${package}.${moduleName}.query.${ClassName}Query;
import ${package}.${moduleName}.entity.${ClassName}Entity;
<#if tableOperation?seq_contains('import')>
import org.springframework.web.multipart.MultipartFile;
</#if>
import java.util.List;

/**
 * ${tableComment}
 *
 * @author ${author} ${email!}
 * <a href="https://maku.net">MAKU</a>
 */
public interface ${ClassName}Service extends BaseService<${ClassName}Entity> {

    PageResult<${ClassName}VO> page(${ClassName}Query query);

    ${ClassName}VO get(Long id);

    <#if hasTree>
    List<${ClassName}VO> list(Long ${treePid});
    </#if>

    <#if tableOperation?seq_contains('insert')>
    void save(${ClassName}VO vo);
    </#if>

    <#if tableOperation?seq_contains('update')>
    void update(${ClassName}VO vo);
    </#if>

    <#if tableOperation?seq_contains('delete')>
    void delete(List<Long> idList);
    </#if>

    <#if tableOperation?seq_contains('import')>
    void importByExcel(MultipartFile file);
    </#if>

    <#if tableOperation?seq_contains('export')>
    void export();
    </#if>
}