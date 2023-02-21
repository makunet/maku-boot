package net.maku.quartz.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.quartz.entity.ScheduleJobEntity;
import net.maku.quartz.query.ScheduleJobQuery;
import net.maku.quartz.vo.ScheduleJobVO;

import java.util.List;

/**
 * 定时任务
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface ScheduleJobService extends BaseService<ScheduleJobEntity> {

    PageResult<ScheduleJobVO> page(ScheduleJobQuery query);

    void save(ScheduleJobVO vo);

    void update(ScheduleJobVO vo);

    void delete(List<Long> idList);

    void run(ScheduleJobVO vo);

    void changeStatus(ScheduleJobVO vo);
}