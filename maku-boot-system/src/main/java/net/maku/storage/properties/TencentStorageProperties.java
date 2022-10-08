package net.maku.storage.properties;

import lombok.Data;

/**
 * 腾讯云存储配置项
 *
 * @author 阿沐 babamu@126.com
 */
@Data
public class TencentStorageProperties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
}
