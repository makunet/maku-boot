package net.maku.storage.properties;

import lombok.Data;

/**
 * 华为云存储配置项
 *
 * @author 阿沐 babamu@126.com
 */
@Data
public class HuaweiStorageProperties {
    private String endPoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
