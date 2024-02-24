package net.maku.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.*;
import net.maku.framework.common.exception.ServerException;
import net.maku.framework.common.query.Query;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.system.convert.SysThirdLoginConfigConvert;
import net.maku.system.dao.SysThirdLoginConfigDao;
import net.maku.system.entity.SysThirdLoginConfigEntity;
import net.maku.system.enums.ThirdLoginEnum;
import net.maku.system.service.SysThirdLoginConfigService;
import net.maku.system.vo.SysThirdLoginConfigVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 第三方登录配置
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class SysThirdLoginConfigServiceImpl extends BaseServiceImpl<SysThirdLoginConfigDao, SysThirdLoginConfigEntity> implements SysThirdLoginConfigService {

    @Override
    public PageResult<SysThirdLoginConfigVO> page(Query query) {
        IPage<SysThirdLoginConfigEntity> page = baseMapper.selectPage(getPage(query), Wrappers.lambdaQuery());

        return new PageResult<>(SysThirdLoginConfigConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    @Override
    public void save(SysThirdLoginConfigVO vo) {
        SysThirdLoginConfigEntity entity = SysThirdLoginConfigConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysThirdLoginConfigVO vo) {
        SysThirdLoginConfigEntity entity = SysThirdLoginConfigConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public AuthRequest getAuthRequest(String openType) {
        SysThirdLoginConfigEntity config = baseMapper.selectOne(new LambdaQueryWrapper<SysThirdLoginConfigEntity>()
                .eq(SysThirdLoginConfigEntity::getOpenType, openType));

        if (config == null) {
            throw new ServerException("未配置第三方登录，请配置后再尝试");
        }

        AuthRequest authRequest = switch (ThirdLoginEnum.toEnum(openType)) {
            case WECHAT_WORK -> new AuthWeChatEnterpriseQrcodeRequest(AuthConfig.builder()
                    .clientId(config.getClientId())
                    .clientSecret(config.getClientSecret())
                    .redirectUri(config.getRedirectUri())
                    .agentId(config.getAgentId())
                    .build());
            case DING_TALK -> new AuthDingTalkRequest(AuthConfig.builder()
                    .clientId(config.getClientId())
                    .clientSecret(config.getClientSecret())
                    .redirectUri(config.getRedirectUri())
                    .build());
            case FEI_SHU -> new AuthFeishuRequest(AuthConfig.builder()
                    .clientId(config.getClientId())
                    .clientSecret(config.getClientSecret())
                    .redirectUri(config.getRedirectUri())
                    .build());
            case WECHAT_OPEN -> new AuthWeChatOpenRequest(AuthConfig.builder()
                    .clientId(config.getClientId())
                    .clientSecret(config.getClientSecret())
                    .redirectUri(config.getRedirectUri())
                    .build());
        };

        return authRequest;
    }

}