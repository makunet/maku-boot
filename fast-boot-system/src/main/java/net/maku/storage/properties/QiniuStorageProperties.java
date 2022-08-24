package net.maku.storage.properties;

import lombok.Data;

/**
 * 七牛云存储配置项
 *
 * @author 阿沐 babamu@126.com
 */
@Data
public class QiniuStorageProperties {
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
