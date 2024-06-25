package net.maku.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.system.convert.SysSmsLogConvert;
import net.maku.system.dao.SysSmsLogDao;
import net.maku.system.entity.SysSmsLogEntity;
import net.maku.system.query.SysSmsLogQuery;
import net.maku.system.service.SysSmsLogService;
import net.maku.system.vo.SysSmsLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 短信日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class SysSmsLogServiceImpl extends BaseServiceImpl<SysSmsLogDao, SysSmsLogEntity> implements SysSmsLogService {

    @Override
    public PageResult<SysSmsLogVO> page(SysSmsLogQuery query) {
        IPage<SysSmsLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysSmsLogConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }


    private LambdaQueryWrapper<SysSmsLogEntity> getWrapper(SysSmsLogQuery query) {
        LambdaQueryWrapper<SysSmsLogEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(query.getPlatform() != null, SysSmsLogEntity::getPlatform, query.getPlatform());
        wrapper.like(StrUtil.isNotBlank(query.getMobile()), SysSmsLogEntity::getMobile, query.getMobile());
        wrapper.orderByDesc(SysSmsLogEntity::getId);
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}