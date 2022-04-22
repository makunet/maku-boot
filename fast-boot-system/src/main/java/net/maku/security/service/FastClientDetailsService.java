package net.maku.security.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.JsonUtils;
import net.maku.system.dao.SysOauthClientDao;
import net.maku.system.entity.SysOauthClientEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * ClientDetailsService
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class FastClientDetailsService implements ClientDetailsService {
    private final SysOauthClientDao sysOauthClientDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        SysOauthClientEntity oauthClient = sysOauthClientDao.getByClientId(clientId);

        return clientDetailsMapper(oauthClient);
    }

    private ClientDetails clientDetailsMapper(SysOauthClientEntity entity) {
        BaseClientDetails client = new BaseClientDetails();
        client.setClientId(entity.getClientId());
        // 密码前追加 {noop} 前缀，表示密码是明文
        client.setClientSecret(String.format("{noop}%s", entity.getClientSecret()));

        if (ArrayUtil.isNotEmpty(entity.getAuthorizedGrantTypes())) {
            client.setAuthorizedGrantTypes(CollUtil.newArrayList(entity.getAuthorizedGrantTypes()));
        }

        if (StrUtil.isNotBlank(entity.getAuthorities())) {
            client.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(entity.getAuthorities()));
        }

        if (StrUtil.isNotBlank(entity.getResourceIds())) {
            client.setResourceIds(StringUtils.commaDelimitedListToSet(entity.getResourceIds()));
        }

        if (StrUtil.isNotBlank(entity.getWebServerRedirectUri())) {
            client.setRegisteredRedirectUri(StringUtils.commaDelimitedListToSet(entity.getWebServerRedirectUri()));
        }

        if (StrUtil.isNotBlank(entity.getScope())) {
            client.setScope(StringUtils.commaDelimitedListToSet(entity.getScope()));
        }

        if (StrUtil.isNotBlank(entity.getAutoapprove())) {
            client.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(entity.getAutoapprove()));
        }

        if (entity.getAccessTokenValidity() != null) {
            client.setAccessTokenValiditySeconds(entity.getAccessTokenValidity());
        }

        if (entity.getRefreshTokenValidity() != null) {
            client.setRefreshTokenValiditySeconds(entity.getRefreshTokenValidity());
        }

        if (StrUtil.isNotBlank(entity.getAdditionalInformation())) {
            Map<String, Object> map = JsonUtils.parseObject(entity.getAdditionalInformation(), Map.class);
            client.setAdditionalInformation(map);
        }

        return client;
    }
}
