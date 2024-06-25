package net.maku.system.service;

import net.maku.email.config.EmailConfig;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.system.entity.SysMailConfigEntity;
import net.maku.system.query.SysMailConfigQuery;
import net.maku.system.vo.SysMailConfigVO;

import java.util.List;

/**
 * 邮件平台
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysMailConfigService extends BaseService<SysMailConfigEntity> {

    PageResult<SysMailConfigVO> page(SysMailConfigQuery query);

    List<SysMailConfigVO> list(Integer platform);

    /**
     * 启用的邮件平台列表
     */
    List<EmailConfig> listByEnable();

    void save(SysMailConfigVO vo);

    void update(SysMailConfigVO vo);

    void delete(List<Long> idList);
}