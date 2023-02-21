package net.maku.message.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.message.entity.SmsPlatformEntity;
import net.maku.message.query.SmsPlatformQuery;
import net.maku.message.sms.config.SmsConfig;
import net.maku.message.vo.SmsPlatformVO;

import java.util.List;

/**
 * 短信平台
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface SmsPlatformService extends BaseService<SmsPlatformEntity> {

    PageResult<SmsPlatformVO> page(SmsPlatformQuery query);

    /**
     * 启用的短信平台列表
     */
    List<SmsConfig> listByEnable();

    void save(SmsPlatformVO vo);

    void update(SmsPlatformVO vo);

    void delete(List<Long> idList);

}