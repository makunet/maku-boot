package net.maku.storage.service;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import net.maku.framework.common.exception.FastException;
import net.maku.storage.properties.StorageProperties;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 *
 * @author 阿沐 babamu@126.com
 */
public class QiniuStorageService extends StorageService {
    private final UploadManager uploadManager;
    private final String token;

    public QiniuStorageService(StorageProperties properties) {
        this.properties = properties;

        uploadManager = new UploadManager(new Configuration(Region.autoRegion()));
        token = Auth.create(properties.getQiniu().getAccessKey(), properties.getQiniu().getSecretKey()).
                uploadToken(properties.getQiniu().getBucketName());
    }

    @Override
    public String upload(byte[] data, String path) {
        try {
            Response res = uploadManager.put(data, path, token);
            if (!res.isOK()) {
                throw new FastException(res.toString());
            }

            return properties.getConfig().getDomain() + "/" + path;
        } catch (Exception e) {
            throw new FastException("上传文件失败：", e);
        }
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new FastException("上传文件失败：", e);
        }
    }

}
