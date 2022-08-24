package net.maku.storage.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import net.maku.framework.common.exception.FastException;
import net.maku.storage.properties.StorageProperties;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 腾讯云存储
 *
 * @author 阿沐 babamu@126.com
 */
public class TencentStorageService extends StorageService {
    private final COSCredentials cred;
    private final ClientConfig clientConfig;

    public TencentStorageService(StorageProperties properties) {
        this.properties = properties;

        cred = new BasicCOSCredentials(properties.getTencent().getAccessKey(), properties.getTencent().getSecretKey());

        clientConfig = new ClientConfig(new Region(properties.getTencent().getRegion()));
        clientConfig.setHttpProtocol(HttpProtocol.https);
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            COSClient cosClient = new COSClient(cred, clientConfig);

            // 存储桶名称，格式：BucketName-APPID
            String bucketName = properties.getTencent().getBucketName() + "-" + properties.getTencent().getAppId();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());

            PutObjectRequest request = new PutObjectRequest(bucketName, path, inputStream, metadata);
            PutObjectResult result = cosClient.putObject(request);

            cosClient.shutdown();
            if (result.getETag() == null) {
                throw new FastException("上传文件失败，请检查配置信息");
            }
        } catch (Exception e) {
            throw new FastException("上传文件失败：", e);
        }

        return properties.getConfig().getDomain() + "/" + path;
    }

}
