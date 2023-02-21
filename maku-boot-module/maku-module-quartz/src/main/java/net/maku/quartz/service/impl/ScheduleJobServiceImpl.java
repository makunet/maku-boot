package net.maku.quartz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.quartz.convert.ScheduleJobConvert;
import net.maku.quartz.dao.ScheduleJobDao;
import net.maku.quartz.entity.ScheduleJobEntity;
import net.maku.quartz.enums.ScheduleStatusEnum;
import net.maku.quartz.query.ScheduleJobQuery;
import net.maku.quartz.service.ScheduleJobService;
import net.maku.quartz.utils.ScheduleUtils;
import net.maku.quartz.vo.ScheduleJobVO;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 定时任务
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {
    private final Scheduler scheduler;

    /**
     * 启动项目时，初始化定时器
     */
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
        List<ScheduleJobEntity> scheduleJobList = baseMapper.selectList(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        }
    }

    @Override
    public PageResult<ScheduleJobVO> page(ScheduleJobQuery query) {
        IPage<ScheduleJobEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ScheduleJobConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ScheduleJobEntity> getWrapper(ScheduleJobQuery query) {
        LambdaQueryWrapper<ScheduleJobEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(query.getJobName()), ScheduleJobEntity::getJobName, query.getJobName());
        wrapper.like(StrUtil.isNotBlank(query.getJobGroup()), ScheduleJobEntity::getJobGroup, query.getJobGroup());
        wrapper.eq(query.getStatus() != null, ScheduleJobEntity::getStatus, query.getStatus());

        return wrapper;
    }

    @Override
    public void save(ScheduleJobVO vo) {
        ScheduleJobEntity entity = ScheduleJobConvert.INSTANCE.convert(vo);

        entity.setStatus(ScheduleStatusEnum.PAUSE.getValue());
        if (baseMapper.insert(entity) > 0) {
            ScheduleUtils.createScheduleJob(scheduler, entity);
        }
    }

    @Override
    public void update(ScheduleJobVO vo) {
        ScheduleJobEntity entity = ScheduleJobConvert.INSTANCE.convert(vo);

        // 更新定时任务
        if (updateById(entity)) {
            ScheduleJobEntity scheduleJob = getById(entity.getId());
            ScheduleUtils.updateSchedulerJob(scheduler, scheduleJob);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        for (Long id : idList) {
            ScheduleJobEntity scheduleJob = getById(id);

            // 删除定时任务
            if (removeById(id)) {
                ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public void run(ScheduleJobVO vo) {
        ScheduleJobEntity scheduleJob = getById(vo.getId());
        if (scheduleJob == null) {
            return;
        }

        ScheduleUtils.run(scheduler, scheduleJob);
    }

    @Override
    public void changeStatus(ScheduleJobVO vo) {
        ScheduleJobEntity scheduleJob = getById(vo.getId());
        if (scheduleJob == null) {
            return;
        }

        // 更新数据
        scheduleJob.setStatus(vo.getStatus());
        updateById(scheduleJob);

        if (ScheduleStatusEnum.PAUSE.getValue() == vo.getStatus()) {
            ScheduleUtils.pauseJob(scheduler, scheduleJob);
        } else if (ScheduleStatusEnum.NORMAL.getValue() == vo.getStatus()) {
            ScheduleUtils.resumeJob(scheduler, scheduleJob);
        }
    }

}