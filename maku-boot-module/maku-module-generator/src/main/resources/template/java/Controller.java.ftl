package ${package}.${moduleName}.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import ${package}.framework.common.utils.PageResult;
import ${package}.framework.common.utils.Result;
import ${package}.framework.operatelog.annotations.OperateLog;
import ${package}.framework.operatelog.enums.OperateTypeEnum;
import ${package}.${moduleName}.service.${ClassName}Service;
import ${package}.${moduleName}.query.${ClassName}Query;
import ${package}.${moduleName}.vo.${ClassName}VO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
<#if tableOperation?seq_contains('import')>
import org.springframework.web.multipart.MultipartFile;
</#if>
import jakarta.validation.Valid;
import java.util.List;

/**
 * ${tableComment!}
 *
 * @author ${author} ${email!}
 * <a href="https://maku.net">MAKU</a>
 */
@RestController
@RequestMapping("${requestUrl}")
@Tag(name="${tableComment}")
@AllArgsConstructor
public class ${ClassName}Controller {
    private final ${ClassName}Service ${className}Service;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:page</#if>')")
    public Result<PageResult<${ClassName}VO>> page(@ParameterObject @Valid ${ClassName}Query query){
        PageResult<${ClassName}VO> page = ${className}Service.page(query);

        return Result.ok(page);
    }

    <#if hasTree>
    @GetMapping("list")
    @Operation(summary = "列表")
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:page</#if>')")
    public Result<List<${ClassName}VO>> list(Long ${treePid}) {
        List<${ClassName}VO> list = ${className}Service.list(${treePid});

        return Result.ok(list);
    }
    </#if>

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:info</#if>')")
    public Result<${ClassName}VO> get(@PathVariable("id") Long id){
        ${ClassName}VO data = ${className}Service.get(id);

        return Result.ok(data);
    }

    <#if tableOperation?seq_contains('insert')>
    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:save</#if>')")
    public Result<String> save(@RequestBody ${ClassName}VO vo){
        ${className}Service.save(vo);

        return Result.ok();
    }
    </#if>

    <#if tableOperation?seq_contains('update')>
    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:update</#if>')")
    public Result<String> update(@RequestBody @Valid ${ClassName}VO vo){
        ${className}Service.update(vo);

        return Result.ok();
    }
    </#if>

    <#if tableOperation?seq_contains('delete')>
    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:delete</#if>')")
    public Result<String> delete(@RequestBody List<Long> idList){
        ${className}Service.delete(idList);

        return Result.ok();
    }
    </#if>

    <#if tableOperation?seq_contains('import')>
    @PostMapping("import")
    @Operation(summary = "导入")
    @OperateLog(type = OperateTypeEnum.IMPORT)
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:import</#if>')")
    public Result<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请选择需要上传的文件");
        }
        ${className}Service.importByExcel(file);

        return Result.ok();
    }
    </#if>

    <#if tableOperation?seq_contains('export')>
    @GetMapping("export")
    @Operation(summary = "导出")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    @PreAuthorize("hasAuthority('${authority}<#if authLevel==1>:export</#if>')")
    public void export() {
        ${className}Service.export();
    }
    </#if>
}