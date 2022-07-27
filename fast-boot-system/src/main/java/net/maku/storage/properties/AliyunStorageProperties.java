package net.maku.storage.properties;

import lombok.Data;

/**
 * 阿里云存储配置项
 *
 * @author 阿沐 babamu@126.com
 */
@Data
public class AliyunStorageProperties {
    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
