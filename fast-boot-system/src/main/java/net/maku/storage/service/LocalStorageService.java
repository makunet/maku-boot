package net.maku.storage.service;

import net.maku.api.module.storage.StorageService;
import net.maku.framework.common.exception.FastException;
import net.maku.storage.properties.StorageProperties;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * 本地存储
 *
 * @author 阿沐 babamu@126.com
 */
public class LocalStorageService implements StorageService {
    private final StorageProperties properties;

    public LocalStorageService(StorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(properties.getConfig().getPrefix(), suffix));
    }

    @Override
    public String upload(InputStream inputStream, String path) {

        try {
            File file = new File(properties.getLocal().getPath() + File.separator + path);

            // 没有目录，则自动创建目录
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("Directory '" + parent + "' could not be created");
            }

            FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
        } catch (Exception e) {
            throw new FastException("上传文件失败：", e);
        }

        return properties.getConfig().getDomain() + "/" + properties.getLocal().getUrl() + "/" + path;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(properties.getConfig().getPrefix(), suffix));
    }
}
