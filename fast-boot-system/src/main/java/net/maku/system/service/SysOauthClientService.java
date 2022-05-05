package net.maku.system.service;

import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.query.Query;
import net.maku.framework.common.service.BaseService;
import net.maku.system.entity.SysOauthClientEntity;
import net.maku.system.vo.SysOauthClientVO;

import java.util.List;

/**
 * 客户端管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysOauthClientService extends BaseService<SysOauthClientEntity> {

    PageResult<SysOauthClientVO> page(Query query);

    void save(SysOauthClientVO vo);

    void update(SysOauthClientVO vo);

    void delete(List<Long> idList);
}
