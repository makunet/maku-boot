package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysLogLoginEntity;
import net.maku.system.query.SysLogLoginQuery;
import net.maku.system.vo.SysLogLoginVO;

/**
 * 登录日志
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);
}