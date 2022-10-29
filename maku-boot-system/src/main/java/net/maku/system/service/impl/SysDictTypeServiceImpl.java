package net.maku.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.impl.BaseServiceImpl;
import net.maku.system.convert.SysDictTypeConvert;
import net.maku.system.dao.SysDictDataDao;
import net.maku.system.dao.SysDictTypeDao;
import net.maku.system.entity.SysDictDataEntity;
import net.maku.system.entity.SysDictTypeEntity;
import net.maku.system.service.SysDictTypeService;
import net.maku.system.vo.SysDictVO;
import net.maku.system.query.SysDictTypeQuery;
import net.maku.system.vo.SysDictTypeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
    private final SysDictDataDao sysDictDataDao;

    @Override
    public PageResult<SysDictTypeVO> page(SysDictTypeQuery query) {
        IPage<SysDictTypeEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));
        return new PageResult<>(SysDictTypeConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private Wrapper<SysDictTypeEntity> getWrapper(SysDictTypeQuery query){
        LambdaQueryWrapper<SysDictTypeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(query.getDictType()), SysDictTypeEntity::getDictType, query.getDictType());
        wrapper.like(StrUtil.isNotBlank(query.getDictName()), SysDictTypeEntity::getDictName, query.getDictName());
        wrapper.orderByAsc(SysDictTypeEntity::getSort);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictTypeVO vo) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictTypeVO vo) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public List<SysDictVO> getDictList() {
        // 全部字典类型列表
        List<SysDictTypeEntity> typeList = this.list(Wrappers.emptyWrapper());

        // 全部字典数据列表
        QueryWrapper<SysDictDataEntity> query = new QueryWrapper<SysDictDataEntity>().orderByAsc("sort");
        List<SysDictDataEntity> dataList = sysDictDataDao.selectList(query);

        // 全部字典列表
        List<SysDictVO> dictList = new ArrayList<>(typeList.size());
        for (SysDictTypeEntity type : typeList){
            SysDictVO dict = new SysDictVO();
            dict.setDictType(type.getDictType());

            for (SysDictDataEntity data : dataList){
                if(type.getId().equals(data.getDictTypeId())){
                    dict.getDataList().add(new SysDictVO.DictData(data.getDictLabel(), data.getDictValue()));
                }
            }

            if(type.getType() == 2){
                // 增加动态列表
                String sql = type.getSourceSql();
                if(StringUtils.isNotBlank(sql)){
                    try {
                        dict.setDataList(sysDictDataDao.selectListForSql(sql));
                    } catch (Exception e) {
                        log.error("增加动态字典异常: type=" + type, e);
                    }
                }
            }

            dictList.add(dict);
        }

        return dictList;
    }

}