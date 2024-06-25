package net.maku.system.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.system.entity.SysMailLogEntity;
import net.maku.system.query.SysMailLogQuery;
import net.maku.system.vo.SysMailLogVO;

import java.util.List;

/**
 * 邮件日志
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysMailLogService extends BaseService<SysMailLogEntity> {

    PageResult<SysMailLogVO> page(SysMailLogQuery query);

    void delete(List<Long> idList);
}