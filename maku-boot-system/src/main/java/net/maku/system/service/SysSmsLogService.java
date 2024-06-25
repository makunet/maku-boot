package net.maku.system.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.system.entity.SysSmsLogEntity;
import net.maku.system.query.SysSmsLogQuery;
import net.maku.system.vo.SysSmsLogVO;

import java.util.List;

/**
 * 短信日志
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface SysSmsLogService extends BaseService<SysSmsLogEntity> {

    PageResult<SysSmsLogVO> page(SysSmsLogQuery query);

    void delete(List<Long> idList);
}