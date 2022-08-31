package net.maku.framework.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取真实地址
 *
 * @author 阿沐 babamu@126.com
 */
@Slf4j
public class AddressUtils {
    // 实时查询
    public static final String ADDRESS_URL = "https://whois.pconline.com.cn/ipJson.jsp";
    public static final String UNKNOWN = "未知";

    public static String getAddressByIP(String ip) {
        // 内网
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }

        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("ip", ip);
            paramMap.put("json", true);
            String response = HttpUtil.get(ADDRESS_URL, paramMap);
            if (StringUtils.isBlank(response)) {
                log.error("根据IP获取地址异常 {}", ip);
                return UNKNOWN;
            }

            Address address = JSONUtil.toBean(response, Address.class);
            return String.format("%s %s", address.getPro(), address.getCity());
        } catch (Exception e) {
            log.error("根据IP获取地址异常 {}", ip);
        }

        return UNKNOWN;
    }

    @Data
    static class Address {
        /**
         * 省
         */
        private String pro;
        /**
         * 市
         */
        private String city;
    }
}
