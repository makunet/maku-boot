package net.maku.storage.properties;

import lombok.Data;
import net.maku.storage.enums.StorageTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 存储配置项
 *
 * @author 阿沐 babamu@126.com
 */
@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    /**
     * 是否开启存储
     */
    private boolean enabled;
    /**
     * 通用配置项
     */
    private StorageConfig config;
    /**
     * 本地配置项
     */
    private LocalStorageProperties local;
    /**
     * 阿里云配置项
     */
    private AliyunStorageProperties aliyun;

    @Data
    public static class StorageConfig{
        /**
         * 访问域名
         */
        private String domain;
        /**
         * 配置路径前缀
         */
        private String prefix;
        /**
         * 存储类型
         */
        private StorageTypeEnum type;
    }
}
