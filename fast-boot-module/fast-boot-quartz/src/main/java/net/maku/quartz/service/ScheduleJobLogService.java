package net.maku.quartz.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.quartz.entity.ScheduleJobLogEntity;
import net.maku.quartz.query.ScheduleJobLogQuery;
import net.maku.quartz.vo.ScheduleJobLogVO;

/**
 * 定时任务日志
 *
 * @author 阿沐 babamu@126.com
 */
public interface ScheduleJobLogService extends BaseService<ScheduleJobLogEntity> {

    PageResult<ScheduleJobLogVO> page(ScheduleJobLogQuery query);

}