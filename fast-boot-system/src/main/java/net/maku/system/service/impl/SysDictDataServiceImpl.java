package net.maku.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.impl.BaseServiceImpl;
import net.maku.system.convert.SysDictDataConvert;
import net.maku.system.dao.SysDictDataDao;
import net.maku.system.entity.SysDictDataEntity;
import net.maku.system.service.SysDictDataService;
import net.maku.system.query.SysDictDataQuery;
import net.maku.system.vo.SysDictDataVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {

    @Override
    public PageResult<SysDictDataVO> page(SysDictDataQuery query) {
        IPage<SysDictDataEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysDictDataConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private QueryWrapper<SysDictDataEntity> getWrapper(SysDictDataQuery query){
        QueryWrapper<SysDictDataEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_type_id", query.getDictTypeId());
        wrapper.orderByAsc("sort");

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictDataVO vo) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictDataVO vo) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}