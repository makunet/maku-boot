package net.maku.system.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.sms.config.SmsConfig;
import net.maku.system.entity.SysSmsConfigEntity;
import net.maku.system.query.SysSmsConfigQuery;
import net.maku.system.vo.SysSmsConfigVO;

import java.util.List;

/**
 * 短信配置
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface SysSmsConfigService extends BaseService<SysSmsConfigEntity> {

    PageResult<SysSmsConfigVO> page(SysSmsConfigQuery query);

    List<SysSmsConfigVO> list(Integer platform);

    /**
     * 启用的短信平台列表
     */
    List<SmsConfig> listByEnable();

    void save(SysSmsConfigVO vo);

    void update(SysSmsConfigVO vo);

    void delete(List<Long> idList);

}