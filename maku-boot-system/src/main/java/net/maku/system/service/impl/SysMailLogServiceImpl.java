package net.maku.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.system.convert.SysMailLogConvert;
import net.maku.system.dao.SysMailLogDao;
import net.maku.system.entity.SysMailLogEntity;
import net.maku.system.query.SysMailLogQuery;
import net.maku.system.service.SysMailLogService;
import net.maku.system.vo.SysMailLogVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysMailLogServiceImpl extends BaseServiceImpl<SysMailLogDao, SysMailLogEntity> implements SysMailLogService {

    @Override
    public PageResult<SysMailLogVO> page(SysMailLogQuery query) {
        IPage<SysMailLogEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(SysMailLogConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<SysMailLogEntity> getWrapper(SysMailLogQuery query) {
        LambdaQueryWrapper<SysMailLogEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(query.getPlatform() != null, SysMailLogEntity::getPlatform, query.getPlatform());
        wrapper.like(StrUtil.isNotBlank(query.getMailFrom()), SysMailLogEntity::getMailFrom, query.getMailFrom());
        wrapper.orderByDesc(SysMailLogEntity::getId);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

}