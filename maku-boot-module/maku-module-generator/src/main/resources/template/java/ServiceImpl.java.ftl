package ${package}.${moduleName}.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import ${package}.framework.common.utils.PageResult;
import ${package}.framework.mybatis.service.impl.BaseServiceImpl;
import ${package}.${moduleName}.convert.${ClassName}Convert;
import ${package}.${moduleName}.entity.${ClassName}Entity;
import ${package}.${moduleName}.query.${ClassName}Query;
import ${package}.${moduleName}.vo.${ClassName}VO;
import ${package}.${moduleName}.dao.${ClassName}Dao;
import ${package}.${moduleName}.service.${ClassName}Service;
<#list subs as sub>
import ${package}.${moduleName}.convert.${sub.ClassName}Convert;
import ${package}.${moduleName}.entity.${sub.ClassName}Entity;
import ${package}.${moduleName}.dao.${sub.ClassName}Dao;
</#list>
<#if tableOperation?seq_contains('import') || tableOperation?seq_contains('export')>
import com.fhs.trans.service.impl.TransService;
import ${package}.framework.common.utils.ExcelUtils;
import ${package}.${moduleName}.vo.${ClassName}ExcelVO;
import ${package}.framework.common.excel.ExcelFinishCallBack;
import org.springframework.web.multipart.MultipartFile;
</#if>
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${tableComment}
 *
 * @author ${author} ${email!}
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class ${ClassName}ServiceImpl extends BaseServiceImpl<${ClassName}Dao, ${ClassName}Entity> implements ${ClassName}Service {
<#if tableOperation?seq_contains('import') || tableOperation?seq_contains('export')>
    private final TransService transService;
</#if>
<#list subs as sub>
    private final ${sub.ClassName}Dao ${sub.className}Dao;
</#list>

    @Override
    public PageResult<${ClassName}VO> page(${ClassName}Query query) {
        IPage<${ClassName}Entity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        <#if hasTree>
        return new PageResult<>(getHasChild(page.getRecords()), page.getTotal());
        <#else>
        return new PageResult<>(${ClassName}Convert.INSTANCE.convertList(page.getRecords()), page.getTotal());
        </#if>
    }

    <#if hasTree>
    @Override
    public List<${ClassName}VO> list(Long ${treePid}) {
        LambdaQueryWrapper<${ClassName}Entity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(${treePid} != null, ${ClassName}Entity::get${treePid?cap_first}, ${treePid});

        List<${ClassName}Entity> list = baseMapper.selectList(wrapper);

        <#if hasTree>
        return getHasChild(list);
        <#else>
        return ${ClassName}Convert.INSTANCE.convertList(list);
        </#if>
    }
    </#if>

    private LambdaQueryWrapper<${ClassName}Entity> getWrapper(${ClassName}Query query){
        LambdaQueryWrapper<${ClassName}Entity> wrapper = Wrappers.lambdaQuery();
        <#if hasTree>
        wrapper.isNull(${ClassName}Entity::get${treePid?cap_first});
        </#if>
        <#list queryList as field>
            <#if field.queryFormType == 'date' || field.queryFormType == 'datetime'>
        wrapper.between(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, ArrayUtils.isNotEmpty(query.get${field.attrName?cap_first}()) ? query.get${field.attrName?cap_first}()[0] : null, ArrayUtils.isNotEmpty(query.get${field.attrName?cap_first}()) ? query.get${field.attrName?cap_first}()[1] : null);
            <#elseif field.queryType == '='>
        wrapper.eq(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == '!='>
        wrapper.ne(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == '>'>
        wrapper.gt(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == '>='>
        wrapper.ge(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == '<'>
        wrapper.lt(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == '<='>
        wrapper.le(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == 'like'>
        wrapper.like(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == 'left like'>
        wrapper.likeLeft(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            <#elseif field.queryType == 'right like'>
        wrapper.likeRight(ObjectUtil.isNotEmpty(query.get${field.attrName?cap_first}()), ${ClassName}Entity::get${field.attrName?cap_first}, query.get${field.attrName?cap_first}());
            </#if>
        </#list>

        return wrapper;
    }

    <#if hasTree>
    private List<${ClassName}VO> getHasChild(List<${ClassName}Entity> entityList) {
        List<${ClassName}VO> list = ${ClassName}Convert.INSTANCE.convertList(entityList);
        list.forEach(vo -> {
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<${ClassName}Entity>().eq(${ClassName}Entity::get${treePid?cap_first}, vo.get${treeId?cap_first}()));
            vo.setHasChild(count > 0);
        });

        return list;
    }
    </#if>

    @Override
    public ${ClassName}VO get(Long id) {
        ${ClassName}Entity entity = baseMapper.selectById(id);
        ${ClassName}VO vo = ${ClassName}Convert.INSTANCE.convert(entity);

        <#list subs as sub>
        <#if sub.mainRelation ==1>
        ${sub.ClassName}Entity ${sub.className}Entity = ${sub.className}Dao.selectOne(Wrappers.lambdaQuery(${sub.ClassName}Entity.class).eq(${sub.ClassName}Entity::get${sub.ForeignKey}, id));
        vo.set${sub.ClassName}(${sub.ClassName}Convert.INSTANCE.convert(${sub.className}Entity));
        </#if>
        <#if sub.mainRelation ==2>
        List<${sub.ClassName}Entity> ${sub.className}List = ${sub.className}Dao.selectList(Wrappers.lambdaQuery(${sub.ClassName}Entity.class).eq(${sub.ClassName}Entity::get${sub.ForeignKey}, id));
        vo.set${sub.ClassName}(${sub.ClassName}Convert.INSTANCE.convertList(${sub.className}List));
        </#if>

        </#list>
        return vo;
    }

    <#if tableOperation?seq_contains('insert')>
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(${ClassName}VO vo) {
        ${ClassName}Entity entity = ${ClassName}Convert.INSTANCE.convert(vo);

        baseMapper.insert(entity);

        <#list subs as sub>
        ${sub.className}Dao.delete(Wrappers.lambdaQuery(${sub.ClassName}Entity.class).eq(${sub.ClassName}Entity::get${sub.ForeignKey}, entity.getId()));
        </#list>

        <#list subs as sub>
        <#if sub.mainRelation ==1>
        ${sub.ClassName}Entity ${sub.className}Entity = ${sub.ClassName}Convert.INSTANCE.convert(vo.get${sub.ClassName}());
        ${sub.className}Entity.set${sub.ForeignKey}(entity.getId());
        ${sub.className}Dao.insert(${sub.className}Entity);
        </#if>
        <#if sub.mainRelation ==2>
        List<${sub.ClassName}Entity> ${sub.className}List = ${sub.ClassName}Convert.INSTANCE.convertList2(vo.get${sub.ClassName}());
        ${sub.className}List.forEach(${sub.className}Entity -> {
            ${sub.className}Entity.set${sub.ForeignKey}(entity.getId());
            ${sub.className}Dao.insert(${sub.className}Entity);
        });
        </#if>

        </#list>
    }
    </#if>

    <#if tableOperation?seq_contains('update')>
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${ClassName}VO vo) {
        ${ClassName}Entity entity = ${ClassName}Convert.INSTANCE.convert(vo);

        updateById(entity);

        <#list subs as sub>
        ${sub.className}Dao.delete(Wrappers.lambdaQuery(${sub.ClassName}Entity.class).eq(${sub.ClassName}Entity::get${sub.ForeignKey}, entity.getId()));
        </#list>

        <#list subs as sub>
        <#if sub.mainRelation ==1>
        ${sub.ClassName}Entity ${sub.className}Entity = ${sub.ClassName}Convert.INSTANCE.convert(vo.get${sub.ClassName}());
        ${sub.className}Entity.set${sub.ForeignKey}(entity.getId());
        ${sub.className}Dao.insert(${sub.className}Entity);
        </#if>
        <#if sub.mainRelation ==2>
        List<${sub.ClassName}Entity> ${sub.className}List = ${sub.ClassName}Convert.INSTANCE.convertList2(vo.get${sub.ClassName}());
        ${sub.className}List.forEach(${sub.className}Entity -> {
            ${sub.className}Entity.set${sub.ForeignKey}(entity.getId());
            ${sub.className}Dao.insert(${sub.className}Entity);
        });
        </#if>

        </#list>
    }
    </#if>

    <#if tableOperation?seq_contains('delete')>
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);

        <#if subs?size gt 0>
        // 删除子表数据
        idList.forEach(id -> {
            <#list subs as sub>
            ${sub.className}Dao.delete(Wrappers.lambdaQuery(${sub.ClassName}Entity.class).eq(${sub.ClassName}Entity::get${sub.ForeignKey}, id));
            </#list>
        });
        </#if>
    }
    </#if>

    <#if tableOperation?seq_contains('import')>
    @Override
    public void importByExcel(MultipartFile file) {
        ExcelUtils.readAnalysis(file, ${ClassName}ExcelVO.class, new ExcelFinishCallBack<>() {
            @Override
            public void doSaveBatch(List<${ClassName}ExcelVO> resultList) {
                ExcelUtils.parseDict(resultList);
                saveBatch(${ClassName}Convert.INSTANCE.convertExcelList2(resultList));
            }
        });
    }
    </#if>

    <#if tableOperation?seq_contains('export')>
    @Override
    public void export() {
    List<${ClassName}ExcelVO> excelList = ${ClassName}Convert.INSTANCE.convertExcelList(list());
        transService.transBatch(excelList);
        ExcelUtils.excelExport(${ClassName}ExcelVO.class, "${tableComment}", null, excelList);
    }
    </#if>

}